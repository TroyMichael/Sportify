package at.fhv.itb13.sportify.dataTransfer.mapper;

import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;
import at.fhv.itb13.sportify.model.entities.Person;
import junit.framework.TestCase;

/**
 * Created by KYUSS on 28.10.2015.
 */
public class PersonMapperTest extends TestCase {

    PersonMapper _mapper = new PersonMapper();

    public void testToDomainObject() throws Exception {
        PersonDTO personDTO = new PersonDTOImpl(
                "Frodo",
                "Beutlin",
                "Beutlinhausen",
                "1",
                "Beutelsend",
                "1234",
                "frodo.beutlin@hobbit.com",
                "11.12.1991"
        );
        Person person = _mapper.toDomainObject(personDTO);
        assertTrue(person.getBirthdate().equals(personDTO.getBirthdate()));
        assertTrue(person.getCity().equals(personDTO.getCity()));
    }

    public void testToDTOObject() throws Exception {

    }
}