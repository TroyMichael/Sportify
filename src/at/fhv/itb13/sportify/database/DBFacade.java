package at.fhv.itb13.sportify.database;

import java.util.List;

public interface DBFacade {

    /**
     * Begins a new transaction
     */
    void beginTransaction();

    /**
     * Commits the current transaction
     */
    void commitTransaction();

    /**
     * Rollbacks the current transaction
     */
    void rollbackTransaction();

    /**
     * Gets a persistent object by its associated id
     *
     * @param type of the persistent object
     * @param id   of the persistent object
     * @return the persistent object or null, if the object does not exist
     */
    <T extends PersistentObject> T get(Class<T> type, String id);

    /**
     * Gets all persistent object of a certain class
     *
     * @param type of the persistent objects
     * @return a list of persistent objects of the specified class
     */
    <T extends PersistentObject> List<T> getAll(Class<T> type);

    /**
     * Creates the passed persistent object
     *
     * @param object to create
     * @return the id of the persistent object
     */
    <T extends PersistentObject> String create(T object);

    /**
     * Creates or updates the passed persistent object
     *
     * @param object to create or update
     */
    <T extends PersistentObject> void createOrUpdate(T object);

    /**
     * Updates the passed persistent object
     *
     * @param object to update
     */
    <T extends PersistentObject> void update(T object);

    /**
     * Deletes the passed persistent object
     *
     * @param object to delete
     */
    <T extends PersistentObject> void delete(T object);
}
