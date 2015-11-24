package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.PersonMother;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SimplePersonMapper.class)
public class SimplePersonMapperTest {


    private SimplePersonMapper _simplePersonMapper;

    @Before
    public void setUp() {
        _simplePersonMapper = new SimplePersonMapper();

    }


    @Test
    public void toDTOObjectReturnSimplePersonDTO() {

        // arrange
        PersonMother personMother = new PersonMother();
        Person person = personMother.setId(IdGenerator.createId()).instance();


        //act
        SimplePersonDTO simplePersonDTO = _simplePersonMapper.toDTOObject(person);

        //assert
        assertEquals(simplePersonDTO.getId(), person.getId());
        assertEquals(simplePersonDTO.getVersion(), person.getVersion());
        assertEquals(simplePersonDTO.getFName(), person.getFName());
        assertEquals(simplePersonDTO.getLName(), person.getLName());

    }

    @Test
    public void toDTOObjectReturnNull() {

        // arrange
        Person person = null;


        //act
        SimplePersonDTO simplePersonDTO = _simplePersonMapper.toDTOObject(person);

        //assert
        assertEquals(person, null);

    }

    @Test
    public void listToDTOReturnListDTO() {
        //arrange
        PersonMother personMother = new PersonMother();
        Person p1 = personMother.setId(IdGenerator.createId()).instance();
        Person p2 = personMother.setId(IdGenerator.createId()).instance();
        Person p3 = personMother.setId(IdGenerator.createId()).instance();
        List<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        //act
        List<SimplePersonDTO> simplePersonDTOs = _simplePersonMapper.toDTOList(persons);

        //asserts
        assertEquals(persons.size(), simplePersonDTOs.size());

    }

}