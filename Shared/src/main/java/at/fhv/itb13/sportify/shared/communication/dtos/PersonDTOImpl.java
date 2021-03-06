package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.HashSet;

/**
 * Created by KYUSS on 27.10.2015.
 *
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
    private boolean _paid = false;
    private String _userName;
    private HashSet<String> _teamIds = new HashSet<>();
    private HashSet<String> _rosterIds = new HashSet<>();
    private HashSet<String> _trainedTeamIds = new HashSet<>();
    private HashSet<String> _sports = new HashSet<>();

    public PersonDTOImpl() {
    }

    public PersonDTOImpl(String fname, String lname, String street, String housenumber, String postalcode, String city, String email, String birthdate, boolean paid) {
        _fname = fname;
        _lname = lname;
        _street = street;
        _houseNumber = housenumber;
        _postalCode = postalcode;
        _city = city;
        _email = email;
        _birthdate = birthdate;
        _paid = paid;
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

    @Override
    public String getUserName() {
        return _userName;
    }

    @Override
    public void setUserName(String User) {
        _userName = User;
    }

    @Override
    public boolean isPaid() {
        return _paid;
    }

    @Override
    public void setPaid(boolean paid) {
        _paid = paid;
    }

    @Override
    public HashSet<String> getTeamIds() {
        return _teamIds;
    }

    @Override
    public void addTeam(String teamId) {
        _teamIds.add(teamId);
    }

    @Override
    public void removeTeam(String teamId) {
        _teamIds.remove(teamId);
    }

    @Override
    public HashSet<String> getTrainedTeamIds() {
        return _trainedTeamIds;
    }

    @Override
    public void addTrainedTeam(String trainedTeamId) {
        _trainedTeamIds.add(trainedTeamId);
    }

    @Override
    public void removeTrainedTeam(String trainedTeamId) {
        _trainedTeamIds.remove(trainedTeamId);
    }

    @Override
    public HashSet<String> getRosters() {
        return _rosterIds;
    }

    @Override
    public void addRoster(String rosterId) {
        _rosterIds.add(rosterId);
    }

    @Override
    public void removeRoster(String rosterId) {
        _rosterIds.remove(rosterId);
    }

    @Override
    public HashSet<String> getSportIDs () {
        return _sports;
    }

    @Override
    public void addSport(String sportID) {
        _sports.add(sportID);
    }

    @Override
    public void removeSport(String sportID) {
        _sports.remove(sportID);
    }
}