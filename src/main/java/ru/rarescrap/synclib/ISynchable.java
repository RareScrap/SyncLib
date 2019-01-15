package ru.rarescrap.synclib;

public interface ISynchable<T extends ISynchable, V extends IChange> {

    /**
     * Создает копию объекта, которая будет хранить состояние последней синхронизации (будущий объект-клиент).
     * @return
     */
    T createEmptyClientCopy();

    /**
     * Находит изменения между актуальным объектом (серверным) и сохраненным (клиентским).
     * @param clientState Состояние объекта на клиенте
     * @return Различия (изменения) между серверным и клиентским состоянием
     */
    /*IChange*/V[] getChanges(T clientState);

    /**
     * Вызывается для объекта-клиента, для которого нужно применить пришедшие изменения
     * @param changes Изменения, пришедшие от серверного объекта
     */
    void applyChanges(/*IChange*/V[] changes);

    // из-за java6
//    Class getChangeClass();
}
