package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.PersistentObject;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by mod on 11/18/15.
 */
public class DBFaceImplTest implements DBFacade {
    @Override
    public void beginTransaction() {

    }

    @Override
    public void commitTransaction() {

    }

    @Override
    public void rollbackTransaction() {

    }

    @Override
    public <T extends PersistentObject> T get(Class<T> type, String id) {
        return null;
    }

    @Override
    public <T extends PersistentObject> List<T> getAll(Class<T> type) {
        return null;
    }

    @Override
    public <T extends PersistentObject> String create(T object) {
        return null;
    }

    @Override
    public <T extends PersistentObject> void createOrUpdate(T object) {

    }

    @Override
    public <T extends PersistentObject> void update(T object) {

    }

    @Override
    public <T extends PersistentObject> void delete(T object) {

    }

    @Override
    public <T extends PersistentObject> List<T> findByCriteria(Class<T> type, Criterion criterion) {
        return null;
    }
}
