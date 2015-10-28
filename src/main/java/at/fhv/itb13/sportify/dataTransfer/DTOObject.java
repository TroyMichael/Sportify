package at.fhv.itb13.sportify.dataTransfer;

import java.io.Serializable;

/**
 * Created by KYUSS on 28.10.2015.
 */
public interface DTOObject extends Serializable {
    String getId();

    void setId(String id);

    int getVersion();

    void setVersion(int version);
}
