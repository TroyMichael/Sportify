package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Person;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public class TeamDTOImpl implements TeamDTO {
    private String _name;
     private List<PersonDTO> _persons;
    private SportDTO _sport;

    public TeamDTOImpl(String name, List<PersonDTO> persons, SportDTO sportDTO) {
        _name = name;
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
    public List<PersonDTO> getPersons() {
        return _persons;
    }

    @Override
    public void setPersons(List<PersonDTO> persons) {
        _persons = persons;
    }

    @Override
    public void setSport(SportDTO sportDTO) {
        _sport = sportDTO;
    }

    @Override
    public SportDTO getSport() {
        return _sport;
    }

}
