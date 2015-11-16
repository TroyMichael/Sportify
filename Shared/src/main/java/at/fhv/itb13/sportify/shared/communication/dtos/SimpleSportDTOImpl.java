package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class SimpleSportDTOImpl extends DTOImpl implements SimpleSportDTO {

    private String _name;

    public SimpleSportDTOImpl(String name) {
        _name = name;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public String toString() {
        return _name;
    }
}
