package at.fhv.itb13.sportify.mapper;

import at.fhv.itb13.sportify.database.PersonMother;
import at.fhv.itb13.sportify.database.TeamMother;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTOImpl;
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

/**
 * Created by KYUSS on 28.10.2015.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonMapper.class)
public class PersonMapperTest {

    @Mock
    private DBFacade _facade;

    private PersonMapper _personMapper;

    @Before
    public void setUp(){
        _personMapper = new PersonMapper(_facade);

    }

    @Test
    public void toDomainObjectReturnPerson(){

        // arrange
        PersonDTO personDTO = new PersonDTOImpl("firstname", "lastname", "street", "number", "postalcode","5844354", "01.01.1994","city", true);

        TeamMother teamMother = new TeamMother();
        InternalTeam team1 = teamMother.setId(IdGenerator.createId()).instance();
        InternalTeam team2 = teamMother.setId(IdGenerator.createId()).instance();

        personDTO.addTeam(team1.getId());
        personDTO.addTeam(team2.getId());

        when(_facade.get(InternalTeam.class,team1.getId())).thenReturn(team1);
        when(_facade.get(InternalTeam.class,team2.getId())).thenReturn(team2);

        //act
        Person person = _personMapper.toDomainObject(personDTO);

        //assert
        assertEquals(personDTO.getId(),person.getId());
        assertEquals(personDTO.getVersion(), person.getVersion());
        assertEquals(personDTO.getFName(), person.getFName());
        assertEquals(personDTO.getLName(), person.getLName());
        assertEquals(personDTO.getBirthdate(), person.getBirthdate());
        assertEquals(personDTO.getCity(),person.getCity());
        assertEquals(personDTO.getEmail(),person.getEmail());
        assertEquals(personDTO.getHouseNumber(),person.getHouseNumber());
        assertEquals(personDTO.getPostalCode(), person.getPostalCode());
        assertEquals(personDTO.getStreet(), person.getStreet());
        assertEquals(personDTO.isPaid(), person.isPaid());
        assertEquals(personDTO.getTeamIds().size(),person.getTeams().size());
        verify(_facade, times(1)).beginTransaction();
        verify(_facade,times(1)).get(InternalTeam.class, team1.getId());
        verify(_facade,times(1)).get(InternalTeam.class,team2.getId());
        verify(_facade, times(1)).commitTransaction();

    }

    @Test
    public void toDomainObjectReturnNull(){

        // arrange
        PersonDTO personDTO = null;

        TeamMother teamMother = new TeamMother();
        InternalTeam team1 = teamMother.setId(IdGenerator.createId()).instance();
        InternalTeam team2 = teamMother.setId(IdGenerator.createId()).instance();


        when(_facade.get(InternalTeam.class,team1.getId())).thenReturn(team1);
        when(_facade.get(InternalTeam.class,team2.getId())).thenReturn(team2);

        //act
        Person person = _personMapper.toDomainObject(personDTO);

        //assert
        verify(_facade, times(0)).beginTransaction();
        verify(_facade,times(0)).get(InternalTeam.class, team1.getId());
        verify(_facade,times(0)).get(InternalTeam.class,team2.getId());
        verify(_facade, times(0)).commitTransaction();
        assertEquals(person,null);

    }

    @Test
    public void toDTOObjectReturnPersonDTO(){

        // arrange
        PersonMother personMother = new PersonMother();
        Person person = personMother.setId(IdGenerator.createId()).instance();

        TeamMother teamMother = new TeamMother();
        InternalTeam team1 = teamMother.setId(IdGenerator.createId()).instance();
        InternalTeam team2 = teamMother.setId(IdGenerator.createId()).instance();

        person.addTeam(team1);
        person.addTeam(team2);

        //act
        PersonDTO personDTO = _personMapper.toDTOObject(person);

        //assert
        assertEquals(personDTO.getId(),person.getId());
        assertEquals(personDTO.getVersion(), person.getVersion());
        assertEquals(personDTO.getFName(), person.getFName());
        assertEquals(personDTO.getLName(), person.getLName());
        assertEquals(personDTO.getBirthdate(), person.getBirthdate());
        assertEquals(personDTO.getCity(),person.getCity());
        assertEquals(personDTO.getEmail(),person.getEmail());
        assertEquals(personDTO.getHouseNumber(),person.getHouseNumber());
        assertEquals(personDTO.getPostalCode(), person.getPostalCode());
        assertEquals(personDTO.getStreet(), person.getStreet());
        assertEquals(personDTO.isPaid(), person.isPaid());
        assertEquals(personDTO.getTeamIds().size(),person.getTeams().size());

    }

    @Test
    public void toDTOObjectReturnNull(){

        // arrange
        Person person = null;


        TeamMother teamMother = new TeamMother();
        InternalTeam team1 = teamMother.setId(IdGenerator.createId()).instance();
        InternalTeam team2 = teamMother.setId(IdGenerator.createId()).instance();


        //act
        PersonDTO personDTO = _personMapper.toDTOObject(person);

        //assert
        assertEquals(person,null);

    }

    @Test
    public void listToDTOReturnListDTO(){
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
        List<PersonDTO> personDTOs = _personMapper.listToDTO(persons);

        //asserts
        assertEquals(persons.size(), personDTOs.size());

    }

}