package at.fhv.itb13.sportify.server.database;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * generic DAO (Data Access Object) interface
 *
 * @param <T>  type of persistent object
 * @param <PK> type of primary key
 */
interface GenericDAO<T extends PersistentObject, PK extends Serializable> {

    void setSession(Session session);

    PK create(T object);

    T get(PK id);

    List<T> getByCriterion(Criterion... criterions);

    List<T> getByCriterion(String orderBy, Boolean asc, Criterion... criterions);

    List<T> getByCriterion(String orderBy, Boolean asc, Integer maxResults, Criterion... criterions);

    List<T> getAll();

    void createOrUpdate(T object);

    void update(T object);

    void delete(T object);

    T merge (T object);
}
