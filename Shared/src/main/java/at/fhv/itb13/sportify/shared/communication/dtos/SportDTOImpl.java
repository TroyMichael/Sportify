package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by mod on 11/10/15.
 */
public class SportDTOImpl implements SportDTO {
    private String _name;

    public SportDTOImpl(String name) {
        this._name = name;
    }

    @Override
    public String getName() {
        return _name;
    }
}
