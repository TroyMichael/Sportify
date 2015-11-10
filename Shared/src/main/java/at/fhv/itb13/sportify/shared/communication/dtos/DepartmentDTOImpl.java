package at.fhv.itb13.sportify.shared.communication.dtos;


import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Caroline on 10.11.2015.
 */
public class DepartmentDTOImpl extends DTOImpl implements DepartmentDTO {

    private String _name;
    private String _description;
    private Collection<SportDTO> _sports;


    public DepartmentDTOImpl(String name){
        _name = name;
        _sports = new HashSet<>();
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
    public Collection<SportDTO> getSports() {
        return _sports;
    }

    @Override
    public void addSport(SportDTO sport) {
        _sports.add(sport);
    }

    @Override
    public void removeSport(SportDTO sport) {
        _sports.remove(sport);
    }
}
