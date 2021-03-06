package at.fhv.itb13.sportify.server.database;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Property;

import java.io.Serializable;
import java.util.List;

/**
 * generic DAO (Data Access Object) superclass for all DAO classes
 * implements general functions for all DAO classes
 *
 * @param <T>  type of persistent object
 * @param <PK> type of primary key
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T extends PersistentObject, PK extends Serializable> implements GenericDAO<T, PK> {

    private Session _session;
    private Class<T> _type;

    public GenericDAOImpl(Class<T> type) {
        _type = type;
    }

    @Override
    public void setSession(Session session) {
        _session = session;
    }

    @Override
    public PK create(T object) {
        return (PK) _session.save(object);
    }

    @Override
    public T get(PK id) {
        return (T) _session.get(_type, id);
    }

    @Override
    public List<T> getByCriterion(Criterion... criterions) {
        return getByCriterion(null, null, null, criterions);
    }

    @Override
    public List<T> getByCriterion(String orderBy, Boolean asc, Criterion... criterions) {
        return getByCriterion(orderBy, asc, null, criterions);
    }

    @Override
    public List<T> getByCriterion(String orderBy, Boolean asc, Integer maxResults, Criterion... criterions) {
        Criteria crit = _session.createCriteria(_type);
        for (Criterion criterion : criterions) {
            crit.add(criterion);
        }
        if ((orderBy != null) && (orderBy.length()) > 0 && (asc != null)) {
            Property prop = Property.forName(orderBy);
            if (asc) {
                crit.addOrder(prop.asc());
            } else {
                crit.addOrder(prop.desc());
            }
        }
        if ((maxResults != null) && (maxResults > 0)) {
            crit.setMaxResults(maxResults);
        }
        return crit.list();
    }

    @Override
    public List<T> getAll() {
        return _session.createCriteria(_type).list();
    }

    /**
     * @see GenericDAO#createOrUpdate(PersistentObject)
     * creates or updates object by hibernate into database
     */
    @Override
    public void createOrUpdate(T object) {
        _session.saveOrUpdate(object);
    }

    /**
     * @see GenericDAO#update(PersistentObject)
     * updates object by hibernate into database
     */
    @Override
    public void update(T object) {
        _session.update(object);
    }

    /**
     * @see GenericDAO#delete(PersistentObject)
     * deletes object by hibernate from database
     */
    @Override
    public void delete(T object) {
        _session.delete(object);
    }

    public T merge (T object){
        return (T) _session.merge(object);
    }
}
