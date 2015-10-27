package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.model.entities.Person;
import at.fhv.itb13.sportify.util.HibernateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class DBFacadeImpl implements DBFacade {

    private Map<Class<?>, GenericDAO> _daoMap;

    private GenericDAO<Person, String> _personDAO;

    public DBFacadeImpl(PersonDAO personDAO) {
        _personDAO = personDAO;

        // add DAO objects to hash map
        _daoMap = new HashMap();
        _daoMap.put(Person.class, _personDAO);
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
        return (T) _daoMap.get(type).get(id);
    }

    @Override
    public <T extends PersistentObject> List<T> getAll(Class<T> type) {
        return (List<T>) _daoMap.get(type).getAll();
    }

    @Override
    public <T extends PersistentObject> String create(T object) {
        return (String) _daoMap.get(object.getClass()).create(object);
    }

    @Override
    public <T extends PersistentObject> void createOrUpdate(T object) {
        _daoMap.get(object.getClass()).createOrUpdate(object);
    }

    @Override
    public <T extends PersistentObject> void update(T object) {
        _daoMap.get(object.getClass()).update(object);
    }

    @Override
    public <T extends PersistentObject> void delete(T object) {
        _daoMap.get(object.getClass()).delete(object);
    }


}
