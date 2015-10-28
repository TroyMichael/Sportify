package at.fhv.itb13.sportify.dataTransfer.dtoInterfaces;

import at.fhv.itb13.sportify.dataTransfer.DTOObject;

import java.io.Serializable;

/**
 * Created by KYUSS on 28.10.2015.
 */
public interface PersonDTO extends Serializable, DTOObject {
    String getFName();

    void setFName(String fname);

    String getLName();

    void setLName(String lname);

    String getStreet();

    void setStreet(String street);

    String getHouseNumber();

    void setHouseNumber(String houseNumber);

    String getPostalCode();

    void setPostalCode(String postalCode);

    String getCity();

    void setCity(String city);

    String getEmail();

    void setEmail(String email);

    String getBirthdate();

    void setBirthdate(String birthdate);
}
