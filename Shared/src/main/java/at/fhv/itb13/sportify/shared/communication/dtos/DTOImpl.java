package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.shared.util.IdGenerator;

/**
 * Created by KYUSS on 27.10.2015.
 */
public abstract class DTOImpl implements DTO {

    private String _id = IdGenerator.createId();
    private Integer _version = 0;

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public void setId(String id) {
        _id = id;
    }

    @Override
    public Integer getVersion() {
        return _version;
    }

    @Override
    public void setVersion(Integer version) {
        _version = version;
    }
}
