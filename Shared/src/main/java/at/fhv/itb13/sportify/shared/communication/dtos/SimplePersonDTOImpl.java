package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class SimplePersonDTOImpl extends DTOImpl implements SimplePersonDTO {

    private String _fName;
    private String _lName;

    public SimplePersonDTOImpl(String fName, String lName) {
        _fName = fName;
        _lName = lName;
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
    public String toString() {
        return _fName + " " + _lName;
    }
}
