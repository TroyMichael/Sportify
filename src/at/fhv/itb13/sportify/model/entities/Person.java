package at.fhv.itb13.sportify.model.entities;

/**
 * Created by Niklas Fessler on 10/22/15.
 */

public class Person implements at.fhv.itb13.sportify.model.interfaces.PersonRestricted {
    private String _fname = "";
    private String _lname = "";
    private String _street = "";
    private String _houseNumber = "";
    private String _postalCode = "";
    private String _city = "";
    private String _telephoneNumber = "";
    private String _birthdate = "";
    private boolean _payed = false;

    public Person (String fname, String lname, String street, String houseNumber, String postalCode, String city, String telephoneNumber, String birthdate) {
        _fname = fname;
        _lname = lname;
        _street = street;
        _houseNumber = houseNumber;
        _postalCode = postalCode;
        _city = city;
        _telephoneNumber = telephoneNumber;
        _birthdate = birthdate;
    }

    @Override
    public String getFName() {
        return _fname;
    }

    public void setFName (String fname) {
        _fname = fname;
    }

    @Override
    public String getLName() {
        return _lname;
    }

    public void setLName (String lname) {
        _lname = lname;
    }

    @Override
    public String getStreet() {
        return _street;
    }

    public void setStreet (String street) {
        _street = street;
    }

    @Override
    public String getHouseNumber() {
        return _houseNumber;
    }

    public void setHouseNumber (String houseNumber) {
        _houseNumber = houseNumber;
    }

    @Override
    public String getPostalCode() {
        return _postalCode;
    }

    public void setPostalCode (String postalCode) {
        _postalCode = postalCode;
    }

    @Override
    public String getCity() {
        return _city;
    }

    public void setCity (String city) {
        _city = city;
    }

    @Override
    public String getTelephoneNumber() {
        return _telephoneNumber;
    }

    public void setTelephoneNumber (String telephoneNumber) {
        _telephoneNumber = telephoneNumber;
    }

    @Override
    public String getBirthdate() {
        return _birthdate;
    }

    public void setBirthdate (String birthdate) {
        _birthdate = birthdate;
    }

    @Override
    public boolean isPayed() {
        return _payed;
    }
    public void isPayed(boolean _payed) {
        this._payed = _payed;
    }
}
