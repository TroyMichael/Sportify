package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.hibernate.Session;

public abstract class PersistentObjectMother<T extends PersistentObject, S extends PersistentObjectMother> extends ObjectMother<T> {

    private Class<T> _cls;
    private String _id;

    public PersistentObjectMother(Class<T> cls) {
        super();
        _cls = cls;
        _id = IdGenerator.createId();
    }

    public PersistentObjectMother(Session session, Class<T> cls) {
        super(session);
        _cls = cls;
        _id = IdGenerator.createId();
    }

    public PersistentObjectMother(Session session, Class<T> cls, String defaultId) {
        super(session);
        _cls = cls;
        _id = defaultId;
    }

    @Override
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


