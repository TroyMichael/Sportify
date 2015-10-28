package at.fhv.itb13.sportify.communication.dtos;

import java.io.Serializable;

/**
 * Created by KYUSS on 28.10.2015.
 */
public interface DTO extends Serializable {
    String getId();

    void setId(String id);

    Integer getVersion();

    void setVersion(Integer version);
}
