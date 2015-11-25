package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.database.dao.*;
import at.fhv.itb13.sportify.server.model.*;
import at.fhv.itb13.sportify.server.util.HibernateUtil;
import org.hibernate.criterion.Criterion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class DBFacadeImpl implements DBFacade {

    private Map<Class<? extends PersistentObject>, GenericDAO> _daoMap;

    public DBFacadeImpl() {
        _daoMap = new HashMap<>();

        _daoMap.put(Department.class, new DepartmentDAO());
        _daoMap.put(Person.class, new PersonDAO());
        _daoMap.put(Roster.class, new RosterDAO());
        _daoMap.put(Sport.class, new SportDAO());
        _daoMap.put(Team.class, new TeamDAO());
        _daoMap.put(InternalTeam.class, new InternalTeamDAO());
        _daoMap.put(ExternalTeam.class, new ExternalTeamDAO());
        _daoMap.put(Tournament.class, new TournamentDAO());
        _daoMap.put(Match.class, new MatchDAO());
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
        return (T) getDAO(type).get(id);
    }

    @Override
    public <T extends PersistentObject> List<T> getAll(Class<T> type) {
        return (List<T>) getDAO(type).getAll();
    }

    @Override
    public <T extends PersistentObject> String create(T object) {
        return (String) getDAO(object.getClass()).create(object);
    }

    @Override
    public <T extends PersistentObject> void createOrUpdate(T object) {
        getDAO(object.getClass()).createOrUpdate(object);
    }

    @Override
    public <T extends PersistentObject> void update(T object) {
        getDAO(object.getClass()).update(object);
    }

    @Override
    public <T extends PersistentObject> void delete(T object) {
        getDAO(object.getClass()).delete(object);
    }

    @Override
    public <T extends PersistentObject> List<T> findByCriteria(Class<T> type, Criterion criterion) {
        return (List<T>) getDAO(type).getByCriterion(criterion);
    }

    @Override
    public <T extends PersistentObject> T merge(T object) {
        return (T) getDAO(object.getClass()).merge(object);
    }

    private <T extends PersistentObject> GenericDAO getDAO(Class<T> type) {
        GenericDAO dao = _daoMap.get(type);
        dao.setSession(HibernateUtil.getCurrentSession());
        return dao;
    }
}
