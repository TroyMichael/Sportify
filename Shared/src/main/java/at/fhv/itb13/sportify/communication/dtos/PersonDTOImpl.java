package at.fhv.itb13.sportify.communication.dtos;

/**
 * Created by KYUSS on 27.10.2015.
 */
public class PersonDTOImpl extends DTOImpl implements PersonDTO {
    private String _fname = "";
    private String _lname = "";
    private String _street = "";
    private String _houseNumber = "";
    private String _postalCode = "";
    private String _city = "";
    private String _email = "";
    private String _birthdate = "";

    public PersonDTOImpl(){}
    public PersonDTOImpl (String fname, String lname, String street, String housenumber, String postalcode, String city, String email, String birthdate){
        _fname = fname;
        _lname = lname;
        _street = street;
        _houseNumber = housenumber;
        _postalCode = postalcode;
        _city = city;
        _email = email;
        _birthdate = birthdate;
    }

    @Override
    public String getFName() {
        return _fname;
    }

    @Override
    public void setFName(String fname) {
        _fname = fname;
    }

    @Override
    public String getLName() {
        return _lname;
    }

    @Override
    public void setLName(String lname) {
        _lname = lname;
    }

    @Override
    public String getStreet() {
        return _street;
    }

    @Override
    public void setStreet(String street) {
        _street = street;
    }

    @Override
    public String getHouseNumber() {
        return _houseNumber;
    }

    @Override
    public void setHouseNumber(String houseNumber) {
        _houseNumber = houseNumber;
    }

    @Override
    public String getPostalCode() {
        return _postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        _postalCode = postalCode;
    }

    @Override
    public String getCity() {
        return _city;
    }

    @Override
    public void setCity(String city) {
        _city = city;
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public void setEmail(String email) {
        _email = email;
    }

    @Override
    public String getBirthdate() {
        return _birthdate;
    }

    @Override
    public void setBirthdate(String birthdate) {
        _birthdate = birthdate;
    }
}
