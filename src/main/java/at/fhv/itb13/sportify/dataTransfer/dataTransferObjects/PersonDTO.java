package at.fhv.itb13.sportify.dataTransfer.dataTransferObjects;

import at.fhv.itb13.sportify.dataTransfer.DTOObject;
import at.fhv.itb13.sportify.database.PersistentObjectImpl;
import at.fhv.itb13.sportify.model.entities.Person;

/**
 * Created by KYUSS on 27.10.2015.
 */
public class PersonDTO extends DTOObject {
    private String _fname = "";
    private String _lname = "";
    private String _street = "";
    private String _houseNumber = "";
    private String _postalCode = "";
    private String _city = "";
    private String _email = "";
    private String _birthdate = "";

    public String getFName() {
        return _fname;
    }

    public void setFName(String fname) {
        _fname = fname;
    }

    public String getLName() {
        return _lname;
    }

    public void setLName(String lname) {
        _lname = lname;
    }

    public String getStreet() {
        return _street;
    }

    public void setStreet(String street) {
        _street = street;
    }

    public String getHouseNumber() {
        return _houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        _houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return _postalCode;
    }

    public void setPostalCode(String postalCode) {
        _postalCode = postalCode;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city = city;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getBirthdate() {
        return _birthdate;
    }

    public void setBirthdate(String birthdate) {
        _birthdate = birthdate;
    }

    @Override
    public DTOObject build(PersistentObjectImpl entity) {
        //will return new PersonDTO as DTOObject
        Person person = (Person) entity;

        _fname = person.getFName();
        _lname = person.getLName();
        _street = person.getStreet();
        _houseNumber = person.getHouseNumber();
        _postalCode = person.getPostalCode();
        _city = person.getCity();
        _email = person.getEmail();
        _birthdate = person.getBirthdate();

        return this;
    }
}
