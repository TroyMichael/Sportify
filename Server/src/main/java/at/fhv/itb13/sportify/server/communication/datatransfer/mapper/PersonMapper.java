package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KYUSS on 28.10.2015.
 */
public class PersonMapper extends Mapper<PersonDTO, Person> {

    DBFacade dbFacade = new DBFacadeImpl();

    public PersonMapper(){}
    public PersonMapper(DBFacade facade){
        dbFacade = facade;
    }
    @Override
    public Person toDomainObject(PersonDTO personDTO) {
        if (personDTO != null) {
            Person person = new Person(
                    personDTO.getFName(),
                    personDTO.getLName(),
                    personDTO.getStreet(),
                    personDTO.getHouseNumber(),
                    personDTO.getPostalCode(),
                    personDTO.getCity(),
                    personDTO.getEmail(),
                    personDTO.getBirthdate(),
                    personDTO.isPaid()
            );
            person.setId(personDTO.getId());
            person.setVersion(personDTO.getVersion());

            if(personDTO.getUserName() != null){
                person.setUser(new User(personDTO.getUserName(), null));
            }


            try {
                dbFacade.beginTransaction();
                for (String teamId : personDTO.getTeamIds()) {
                    person.addTeam(dbFacade.get(InternalTeam.class, teamId));
                }

                dbFacade.commitTransaction();
            } catch (HibernateException e) {
                dbFacade.rollbackTransaction();
            }

            return person;
        }
        return null;
    }

    @Override
    public PersonDTO toDTOObject(Person person) {
        if (person != null) {
            PersonDTO personDTO = new PersonDTOImpl();
            personDTO.setFName(person.getFName());
            personDTO.setLName(person.getLName());
            personDTO.setStreet(person.getStreet());
            personDTO.setHouseNumber(person.getHouseNumber());
            personDTO.setPostalCode(person.getPostalCode());
            personDTO.setCity(person.getCity());
            personDTO.setEmail(person.getEmail());
            personDTO.setBirthdate(person.getBirthdate());
            personDTO.setVersion(person.getVersion());
            personDTO.setId(person.getId());
            personDTO.setPaid(person.isPaid());
            if(personDTO.getUserName() != null) {
                personDTO.setUserName(person.getUser().getUsername());
            }
            for (InternalTeam team : person.getTeams()) {
                personDTO.addTeam(team.getId());
            }
            return personDTO;
        }
        return null;
    }

    public List<PersonDTO> listToDTO(List<Person> persons) {
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (Person person : persons) {
            personDTOs.add(toDTOObject(person));
        }
        return personDTOs;
    }
}
