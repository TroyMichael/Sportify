package at.fhv.itb13.sportify.database;

import org.hibernate.Session;

public abstract class ObjectMother<T> {

    private Session _session;

    public ObjectMother() {
    }

    public ObjectMother(Session s) {
        _session = s;
    }

    /**
     * returns an instance based on the configuration of this object mother
     */
    public T instance() {
        T instance = null;
        if ((_session != null) && (_session.isConnected())) {
            instance = loadInstance(_session);
        }
        if (instance == null) {
            instance = createInstance();
        }
        configureInstance(instance);
        if ((_session != null) && (_session.isConnected())) {
            _session.save(instance);
        }
        return instance;
    }

    /**
     * configure the instance according to the configuration of this ObjectMother
     */
    abstract protected void configureInstance(T t);

    /**
     * try to load an instance based on the alternate key. Returns null if no such instance exists
     */
    abstract protected T loadInstance(Session session);

    /**
     * create a fresh instance with the alternate key set according to the configuration of this ObjectMother
     */
    abstract protected T createInstance();
}


