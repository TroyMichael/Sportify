package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;

/**
 * Created by Michael on 16.11.2015.
 */
public interface SimplePersonDTO extends Serializable, DTO {

    String getFName();
    void setFName(String fName);

    String getLName();
    void setLName(String lName);



}
