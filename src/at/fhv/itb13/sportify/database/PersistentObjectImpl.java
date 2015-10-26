package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.util.IdGenerator;

import javax.persistence.*;

/**
 * implements {@link PersistentObject} and describes the search functionality and search algorithms
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersistentObjectImpl implements PersistentObject {

    private String _id = IdGenerator.createId();
    private Integer _version = null;

    @Override
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String id) {
        _id = id;
    }

    @Override
    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return _version;
    }

    @Override
    public void setVersion(Integer version) {
        _version = version;
    }

    @Override
    @Transient
    public boolean isCreation() {
        return (_version == null);
    }

    @Override
    public boolean equals(Object object) {
        // if it's the same reference, return true
        if (this == object) {
            return true;
        }

        // if it's null or not a persistence object, return false
        if (object == null || !(object instanceof PersistentObjectImpl)) {
            return false;
        }

        PersistentObjectImpl other = (PersistentObjectImpl) object;
        // if the UUID is missing, return false
        if (_id == null) {
            return false;
        }

        // equivalence by UUID
        return _id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        if (_id != null) {
            return _id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "[id=" + _id + "]";
    }
}
