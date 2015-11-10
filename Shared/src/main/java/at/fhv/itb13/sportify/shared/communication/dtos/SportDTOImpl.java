package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.Collection;

/**
 * Created by mod on 11/10/15.
 */
public class SportDTOImpl implements SportDTO {
    private String _name;
    private DepartmentDTO _department;
    private Collection<TeamDTO> _teams;

    public SportDTOImpl(String name) {
        _name = name;
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
    public DepartmentDTO getDepartment() {
        return _department;
    }

    @Override
    public void setDepartment(DepartmentDTO department) {
        _department = department;
    }

    @Override
    public Collection<TeamDTO> getTeams() {
        return _teams;
    }

    @Override
    public void addTeam(TeamDTO team) {
        _teams.add(team);
    }

    @Override
    public void removeTeam(TeamDTO team) {
        _teams.remove(team);
    }
}
