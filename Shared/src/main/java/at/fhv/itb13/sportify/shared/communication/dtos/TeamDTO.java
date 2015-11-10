package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Person;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamDTO {
    String getName();
    void setName(String name);
    Department getDepartment();
    void setDepartment(Department department);
    List<PersonDTO> getPersons();
    void setPersons(List<PersonDTO> persons);
    void setSport(SportDTO sportDTO);
    SportDTO getSport();
}
