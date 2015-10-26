package at.fhv.itb13.sportify.database;

/**
 * Interface for PersistentObject
 */
public interface PersistentObject {

    String getId();

    void setId(String id);

    Integer getVersion();

    void setVersion(Integer version);

    boolean isCreation();
}
