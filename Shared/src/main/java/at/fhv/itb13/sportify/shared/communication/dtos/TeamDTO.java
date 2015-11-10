package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Sport;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamDTO {
    String getName();
    void setName(String name);
    List<PersonDTO> getPersons();
    void setPersons(List<PersonDTO> persons);
    void setSport(SportDTO sportDTO);
    SportDTO getSport();
}
