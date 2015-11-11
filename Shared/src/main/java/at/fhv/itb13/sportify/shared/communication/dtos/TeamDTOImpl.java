package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public class TeamDTOImpl implements TeamDTO {
    private String _name;
    private DepartmentDTO _department;
    private List<PersonDTO> _persons;
    private SportDTO _sport;

    public TeamDTOImpl(String name, DepartmentDTO department, List<PersonDTO> persons, SportDTO sportDTO) {
        _name = name;
        _department = department;
        _persons = persons;
        _sport = sportDTO;
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
    public List<PersonDTO> getPersons() {
        return _persons;
    }

    @Override
    public void setPersons(List<PersonDTO> persons) {
        _persons = persons;
    }

    @Override
    public void setSport(SportDTO sportDTO) {

    }

    @Override
    public SportDTO getSport() {
        return null;
    }

}
