package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Person;

import java.util.LinkedList;

/**
 * Created by mod on 11/10/15.
 */
public class TeamDTOImpl implements TeamDTO {
    private String _name;
    private Department _department;
    private LinkedList<Person> _persons;

    public TeamDTOImpl(String name, Department department, LinkedList<Person> persons) {
        _name = name;
        _department = department;
        _persons = persons;
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
    public Department getDepartment() {
        return _department;
    }

    @Override
    public void setDepartment(Department department) {
        _department = department;
    }

    @Override
    public LinkedList<Person> getPersons() {
        return _persons;
    }

    @Override
    public void setPersons(LinkedList<Person> persons) {
        _persons = persons;
    }
}
