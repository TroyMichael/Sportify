package at.fhv.itb13.sportify.dataTransfer;

import at.fhv.itb13.sportify.util.IdGenerator;

/**
 * Created by KYUSS on 27.10.2015.
 */
public abstract class DTOObjectImpl implements DTOObject {

    private String _id = IdGenerator.createId();
    private int _version;

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String id) {
        _id = id;
    }

    @Override
    public int getVersion() {
        return _version;
    }

    @Override
    public void setVersion(int version) {
        _version = version;
    }
}
