package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.database.PersistentObject;
import org.hibernate.Session;

public abstract class PersistentObjectMother<T extends PersistentObject> extends ObjectMother<T> {

    private Class<T> _cls;
    private String _id;

    public PersistentObjectMother(Session session, Class<T> cls, String defaultId) {
        super(session);
        _cls = cls;
        _id = defaultId;
    }

    @Override
    final protected T loadInstance(Session session) {
        return (T) session.get(_cls, _id);
    }

    public void setId(String id) {
        _id = id;
    }

    public String getId() {
        return _id;
    }
}


