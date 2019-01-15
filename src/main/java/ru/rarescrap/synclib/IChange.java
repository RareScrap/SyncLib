package ru.rarescrap.synclib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public /*interface*/ abstract class IChange {
    // reflection needed constructor
    public IChange() {}

    public abstract byte[] toBytes();

    public abstract void fromBytes(byte[] bytes);

    public static IChange createChange(String changeClassName) {
        Class<?> changeClass;
        Constructor<?> ctor;
        try {
            changeClass = Class.forName(changeClassName);
            ctor = changeClass.getConstructor();
            return (IChange) ctor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
