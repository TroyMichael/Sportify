package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by mod on 11/10/15.
 *
 */
public interface SportDTO extends Serializable, DTO {
    String getName();

    void setName(String name);

    String getDepartmentId();

    void setDepartment(String departmentId);

    HashSet<String> getTeamIds();

    void addTeam(String teamId);

    void removeTeam(String teamId);
}
