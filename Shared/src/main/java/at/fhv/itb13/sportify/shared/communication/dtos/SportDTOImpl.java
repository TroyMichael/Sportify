package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by mod on 11/10/15.
 */
public class SportDTOImpl implements SportDTO {
    private String _name;
    private String _departmentId;
    private HashSet<String> _teamIds = new HashSet<>();

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
    public String getDepartmentId() {
        return _departmentId;
    }

    @Override
    public void setDepartment(String departmentId) {
        _departmentId = departmentId;
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


}
