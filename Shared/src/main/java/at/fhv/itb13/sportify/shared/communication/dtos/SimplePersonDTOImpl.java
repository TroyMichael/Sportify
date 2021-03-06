package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class SimplePersonDTOImpl extends DTOImpl implements SimplePersonDTO {

    private String _fName;
    private String _lName;
    private String _username;


    public SimplePersonDTOImpl() {

    }

    public SimplePersonDTOImpl(String fName, String lName, String id) {
        _fName = fName;
        _lName = lName;
        setId(id);
    }

    @Override
    public String getFName() {
        return _fName;
    }

    @Override
    public void setFName(String fName) {
        _fName = fName;
    }

    @Override
    public String getLName() {
        return _lName;
    }

    @Override
    public void setLName(String lName) {
        _lName = lName;
    }

    @Override
    public String getUserName() {
        return _username;
    }

    @Override
    public void setUserName(String username) {
        _username = username;
    }

    @Override
    public String toString() {
        return _fName + " " + _lName;
    }


}
