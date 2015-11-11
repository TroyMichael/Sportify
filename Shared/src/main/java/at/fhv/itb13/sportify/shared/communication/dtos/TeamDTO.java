package at.fhv.itb13.sportify.shared.communication.dtos;


import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamDTO {
    String getName();
    void setName(String name);
    DepartmentDTO getDepartment();
    void setDepartment(DepartmentDTO department);
    List<PersonDTO> getPersons();
    void setPersons(List<PersonDTO> persons);
    void setSport(SportDTO sportDTO);
    SportDTO getSport();
}
