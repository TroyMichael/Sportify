package at.fhv.itb13.sportify.shared.communication.dtos;

/**
 * Created by mod on 11/23/15.
 */
public class SimpleTeamDTOImpl extends DTOImpl implements SimpleTeamDTO {
    private String _name;


    public SimpleTeamDTOImpl(String name){
        _name = name;
    }
    @Override
    public String getName() {
        return _name;
    }

}
