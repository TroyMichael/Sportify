package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.Collection;

/**
 * Created by mod on 11/10/15.
 */
public interface SportDTO {
    String getName();

    void setName(String name);

    DepartmentDTO getDepartment();

    void setDepartment(DepartmentDTO department);

    Collection<TeamDTO> getTeams();

    void addTeam(TeamDTO team);

    void removeTeam(TeamDTO team);
}
