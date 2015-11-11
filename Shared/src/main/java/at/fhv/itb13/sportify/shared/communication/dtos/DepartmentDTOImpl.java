package at.fhv.itb13.sportify.shared.communication.dtos;


import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Caroline on 10.11.2015.
 */
public class DepartmentDTOImpl extends DTOImpl implements DepartmentDTO {

    private String _name;
    private String _description;
    private HashSet<String> _sportsId;


    public DepartmentDTOImpl(String name){
        _name = name;
        _sportsId = new HashSet<>();
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;
    }

    @Override
    public HashSet<String> getSportIds() {
        return _sportsId;
    }

    @Override
    public void addSport(String sportId) {
        _sportsId.add(sportId);
    }

    @Override
    public void removeSport(String sportId) {
        _sportsId.remove(sportId);
    }
}
