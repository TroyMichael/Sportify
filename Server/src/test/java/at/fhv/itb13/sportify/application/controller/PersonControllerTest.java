package at.fhv.itb13.sportify.application.controller;

import at.fhv.itb13.sportify.database.PersonMother;
import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SimplePersonMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTOImpl;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonController.class)
public class PersonControllerTest {

    @Mock
    private DBFacade _facade;
    @Mock
    private PersonMapper _personMapper;
    @Mock
    private SimplePersonMapper _simplePersonMapper;

    private PersonController _personController;

    @Before
    public void setUp() {
        _personController = new PersonController(_facade, _personMapper, _simplePersonMapper);
    }

    @Test
    public void getAllSimplePersonsReturnsList() {
        // arrange
        PersonMother personMother = new PersonMother();
        Person p1 = personMother.setId(IdGenerator.createId()).instance();
        Person p2 = personMother.setId(IdGenerator.createId()).instance();
        List<Person> persons1 = new ArrayList<>();
        persons1.add(p1);
        persons1.add(p2);
        when(_facade.getAll(Person.class)).thenReturn(persons1);

        SimplePersonDTO sp1 = new SimplePersonDTOImpl(p1.getFName(), p1.getLName(), p1.getId());
        SimplePersonDTO sp2 = new SimplePersonDTOImpl(p2.getFName(), p2.getLName(), p2.getId());
        List<SimplePersonDTO> simplePersons1 = new ArrayList<>();
        simplePersons1.add(sp1);
        simplePersons1.add(sp2);
        when(_simplePersonMapper.toDTOList(anyListOf(Person.class))).thenReturn(simplePersons1);
        //when(_simplePersonMapper.toDTOList(anyListOf(Person.class))).thenThrow(new Exception());

        // act
        List<SimplePersonDTO> persons2 = _personController.getAllSimplePersons();

        // assert
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).getAll(Person.class);
        verify(_facade, times(1)).commitTransaction();
        verify(_simplePersonMapper, times(1)).toDTOList(anyListOf(Person.class));
        assertEquals(persons1.size(), persons2.size());
    }

    @Test
    public void getAllSimplePersonsReturnsNull() {
        // arrange
        when(_facade.getAll(Person.class)).thenReturn(null);

        // act
        List<SimplePersonDTO> persons = _personController.getAllSimplePersons();

        // assert
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).getAll(Person.class);
        verify(_facade, times(1)).commitTransaction();
        verify(_simplePersonMapper, never()).toDTOList(anyListOf(Person.class));
        assertEquals(persons, null);
    }


//    public void testCreate() throws Exception {
//
//    }
//
//    public void testSaveOrupdate() throws Exception {
//
//    }
//
//    public void testSearchPerson() throws Exception {
//        PersonController personController = new PersonController();
//        PersonDTO personDTO = new PersonDTOImpl();
//        //personDTO.setFName("Diane");
//        //personDTO.setLName("Harrison");
//        personDTO.setStreet("black");
//        personDTO.setCity("death");
//        SportDAO sportDAO = new SportDAO();
//        List<PersonDTO> persons = personController.searchPerson(personDTO);
//        /*
//        for (PersonDTO personDTO1 : persons) {
//            System.out.println(personDTO1.getFName() + " " + personDTO1.getLName());
//        }*/
//        assertTrue(persons.size() > 0);
//    }
//
//    public void testGetCommonElements() throws Exception {
//
//    }
//
//    public void testGetAllPersons() throws Exception {
//        PersonController personController = new PersonController();
//        List<PersonDTO> personDTOList = personController.getAllPersons();
//        /*
//        for (PersonDTO personDTO : personDTOList) {
//            System.out.println(personDTO.getFName() + " " + personDTO.getLName());
//        }*/
//        assertTrue(personDTOList.size() > 0);
//    }
//
//    public void testGetPayedPersons() throws Exception {
//        PersonController personController = new PersonController();
//        List<PersonDTO> payedPersons = personController.getPayedPersons();
//        for (PersonDTO personDTO : payedPersons) {
//            System.out.println(personDTO.getFName() + " " + personDTO.getLName());
//        }
//        assertTrue(payedPersons.size() > 0);
//    }
}
