package at.fhv.itb13.sportify.server.database;

public abstract class ObjectMother<T> {

    public ObjectMother() {
    }

    /**
     * returns an instance based on the configuration of this object mother
     */
    public T instance() {
        T instance = createInstance();
        configureInstance(instance);
        return instance;
    }

    /**
     * configure the instance according to the configuration of this ObjectMother
     */
    abstract protected void configureInstance(T t);

    /**
     * create a fresh instance with the alternate key set according to the configuration of this ObjectMother
     */
    abstract protected T createInstance();
}


