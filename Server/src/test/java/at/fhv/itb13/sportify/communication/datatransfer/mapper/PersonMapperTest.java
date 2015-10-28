package at.fhv.itb13.sportify.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.dtos.PersonDTOImpl;
import at.fhv.itb13.sportify.model.Person;
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