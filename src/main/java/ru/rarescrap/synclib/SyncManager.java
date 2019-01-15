package ru.rarescrap.synclib;

import java.util.HashMap;
import java.util.Map;

public abstract class SyncManager {
    private HashMap<ISynchable, ISynchable> syncState = new HashMap<ISynchable, ISynchable>();

    public void add(ISynchable serverSynchable) {
        syncState.put(serverSynchable, serverSynchable.createEmptyClientCopy());
    }

    public void remove(ISynchable serverSyncable) {
        syncState.remove(serverSyncable);
    }

    public IChange[] getChanges(ISynchable serverSynchable) {
        ISynchable clientState = syncState.get(serverSynchable);
        if (clientState == null) throw new IllegalArgumentException("Argument is not add to sync");

        return serverSynchable.getChanges(clientState);
    }

    public void detectAndSendChanges() {
        for (Map.Entry<ISynchable, ISynchable> entry : syncState.entrySet()) {
            ISynchable serverState = entry.getKey();
            ISynchable clientState = entry.getValue();

            IChange[] changes = getChanges(serverState);
            if (changes.length != 0) {
                sendChanges(changes);
                clientState.applyChanges(changes);
            }
        }
    }

    public abstract void sendChanges(IChange[] changes);
}
