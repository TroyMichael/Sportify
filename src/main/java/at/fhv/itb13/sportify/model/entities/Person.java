package at.fhv.itb13.sportify.model.entities;

import at.fhv.itb13.sportify.database.PersistentObjectImpl;
import at.fhv.itb13.sportify.model.interfaces.PersonRestricted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Niklas Fessler on 10/22/15.
 */

@Entity
@Table(name = "person")
public class Person extends PersistentObjectImpl implements PersonRestricted {
    private String _fname = "";
    private String _lname = "";
    private String _street = "";
    private String _houseNumber = "";
    private String _postalCode = "";
    private String _city = "";
    private String _email = "";
    private String _birthdate = "";

    public Person() {
    }

    public Person(String fname, String lname, String street, String houseNumber, String postalCode, String city, String email, String birthdate) {
        _fname = fname;
        _lname = lname;
        _street = street;
        _houseNumber = houseNumber;
        _postalCode = postalCode;
        _city = city;
        _email = email;
        _birthdate = birthdate;
    }

    @Column(name = "firstname")
    @Override
    public String getFName() {
        return _fname;
    }

    public void setFName(String fname) {
        _fname = fname;
    }

    @Column(name = "lastname")
    @Override
    public String getLName() {
        return _lname;
    }

    public void setLName(String lname) {
        _lname = lname;
    }

    @Column(name = "street")
    @Override
    public String getStreet() {
        return _street;
    }

    public void setStreet(String street) {
        _street = street;
    }

    @Column(name = "housenumber")
    @Override
    public String getHouseNumber() {
        return _houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        _houseNumber = houseNumber;
    }

    @Column(name = "postalcode")
    @Override
    public String getPostalCode() {
        return _postalCode;
    }

    public void setPostalCode(String postalCode) {
        _postalCode = postalCode;
    }

    @Column(name = "city")
    @Override
    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city = city;
    }

    @Column(name = "email")
    @Override
    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    @Column(name = "birthdate")
    @Override
    public String getBirthdate() {
        return _birthdate;
    }

    public void setBirthdate(String birthdate) {
        _birthdate = birthdate;
    }
}
