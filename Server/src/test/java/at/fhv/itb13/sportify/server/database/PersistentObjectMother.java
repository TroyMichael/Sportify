package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.hibernate.Session;

public abstract class PersistentObjectMother<T extends PersistentObject, S extends PersistentObjectMother> extends ObjectMother<T> {

    private Session _session;
    private Class<T> _cls;
    private String _id;

    public PersistentObjectMother(Class<T> cls) {
        super();
        _cls = cls;
        _id = IdGenerator.createId();
    }

    public PersistentObjectMother(Session session, Class<T> cls) {
        super();
        _session = session;
        _cls = cls;
        _id = IdGenerator.createId();
    }

    public PersistentObjectMother(Session session, Class<T> cls, String defaultId) {
        super();
        _session = session;
        _cls = cls;
        _id = defaultId;
    }

    @Override
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
     * try to load an instance based on the alternate key. Returns null if no such instance exists
     */
    final protected T loadInstance(Session session) {
        return (T) session.get(_cls, _id);
    }

    public S setId(String id) {
        _id = id;
        return (S) this;
    }

    public String getId() {
        return _id;
    }
}


