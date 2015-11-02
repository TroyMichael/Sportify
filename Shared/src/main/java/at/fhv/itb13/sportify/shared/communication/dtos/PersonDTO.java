package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;

/**
 * Created by KYUSS on 28.10.2015.
 */
public interface PersonDTO extends Serializable, DTO {
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
