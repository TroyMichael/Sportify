package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.util.HibernateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class DBFacadeImpl implements DBFacade {

    private Map<Class<?>, GenericDAO> _daoMap;

    private GenericDAOGenerator _gdaofactory;

    /**
     * TODO: Factory für das erstellen von DAO´s
     */
    public DBFacadeImpl() {
        _daoMap = new HashMap<>();
        _gdaofactory = GenericDAOGenerator.getInstance();
    }

    @Override
    public void beginTransaction() {
        HibernateUtil.getCurrentSession().beginTransaction();
    }

    @Override
    public void commitTransaction() {
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void rollbackTransaction() {
        HibernateUtil.getCurrentSession().getTransaction().rollback();
    }

    @Override
    public <T extends PersistentObject> T get(Class<T> type, String id) {
        GenericDAO genericDAO = _daoMap.get(type);
        if (genericDAO == null) {
            genericDAO = _gdaofactory.getDAO(type);
            _daoMap.put(type, genericDAO);
        }
        return (T) _daoMap.get(type).get(id);
    }

    @Override
    public <T extends PersistentObject> List<T> getAll(Class<T> type) {
        GenericDAO genericDAO = _daoMap.get(type);
        if (genericDAO == null) {
            genericDAO = _gdaofactory.getDAO(type);
            _daoMap.put(type, genericDAO);
        }
        return (List<T>) _daoMap.get(type).getAll();
    }

    @Override
    public <T extends PersistentObject> String create(T object) {
        GenericDAO genericDAO = _daoMap.get(object.getClass());
        if (genericDAO == null) {
            genericDAO = _gdaofactory.getDAO(object.getClass());
            _daoMap.put(object.getClass(), genericDAO);
        }
        return (String) _daoMap.get(object.getClass()).create(object);
    }

    @Override
    public <T extends PersistentObject> void createOrUpdate(T object) {
        GenericDAO genericDAO = _daoMap.get(object.getClass());
        if (genericDAO == null) {
            genericDAO = _gdaofactory.getDAO(object.getClass());
            _daoMap.put(object.getClass(), genericDAO);
        }
        _daoMap.get(object.getClass()).createOrUpdate(object);
    }

    @Override
    public <T extends PersistentObject> void update(T object) {
        GenericDAO genericDAO = _daoMap.get(object.getClass());
        if (genericDAO == null) {
            genericDAO = _gdaofactory.getDAO(object.getClass());
            _daoMap.put(object.getClass(), genericDAO);
        }
        _daoMap.get(object.getClass()).update(object);
    }

    @Override
    public <T extends PersistentObject> void delete(T object) {
        GenericDAO genericDAO = _daoMap.get(object.getClass());
        if (genericDAO == null) {
            genericDAO = _gdaofactory.getDAO(object.getClass());
            _daoMap.put(object.getClass(), genericDAO);
        }
        _daoMap.get(object.getClass()).delete(object);
    }
}
