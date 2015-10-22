package at.fhv.itb13.sportify.model.entities;

import at.fhv.itb13.sportify.model.interfaces.MemberRestricted;

/**
 * Created by Niklas Fessler on 10/22/15.
 */

public class Member implements MemberRestricted{
    private String _fname = "";
    private String _lname = "";
    private String _address = "";
    private String _birthdate = "";

    public Member(String _fname, String _lname, String _address, String _birthdate) {
        this._fname = _fname;
        this._lname = _lname;
        this._address = _address;
        this._birthdate = _birthdate;
    }

    public String getFName() {
        return _fname;
    }

    public void setFName(String _fname) {
        this._fname = _fname;
    }

    public String getLName() {
        return _lname;
    }

    public void setLName(String _lname) {
        this._lname = _lname;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public String getBirthdate() {
        return _birthdate;
    }

    public void setBirthdate(String _birthdate) {
        this._birthdate = _birthdate;
    }
}
