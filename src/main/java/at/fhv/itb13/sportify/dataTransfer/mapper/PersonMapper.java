package at.fhv.itb13.sportify.dataTransfer.mapper;

import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;
import at.fhv.itb13.sportify.dataTransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.dataTransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.model.entities.Person;

/**
 * Created by KYUSS on 28.10.2015.
 */
public class PersonMapper extends Mapper<PersonDTO, Person> {

    @Override
    public Person toDomainObject(PersonDTO personDTO) throws DTOIsNullException {
        if (personDTO != null) {
            Person person = new Person(
                    personDTO.getFName(),
                    personDTO.getLName(),
                    personDTO.getStreet(),
                    personDTO.getHouseNumber(),
                    personDTO.getPostalCode(),
                    personDTO.getCity(),
                    personDTO.getEmail(),
                    personDTO.getBirthdate()
            );
            person.setId(personDTO.getId());
            person.setVersion(personDTO.getVersion());
            return person;
        } else {
            throw new DTOIsNullException();
        }
    }

    @Override
    public PersonDTO toDTOObject(Person person) throws DomainObjectIsNullException {
        if (person != null){
            PersonDTO personDTO = new PersonDTOImplImpl();
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
            return personDTO;
        } else {
            throw new DomainObjectIsNullException();
        }
    }
}
