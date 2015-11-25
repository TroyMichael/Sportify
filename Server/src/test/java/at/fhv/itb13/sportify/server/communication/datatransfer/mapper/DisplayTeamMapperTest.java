package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.InternalTeamMother;
import at.fhv.itb13.sportify.server.database.PersonMother;
import at.fhv.itb13.sportify.server.database.SportMother;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.communication.dtos.*;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DisplayTeamMapper.class)
public class DisplayTeamMapperTest {

    @Mock
    private SimplePersonMapper _simplePersonMapper;


    private DisplayTeamMapper _displayTeamMapper;

    @Before
    public void setUp() {
        _displayTeamMapper = new DisplayTeamMapper(_simplePersonMapper);

    }

    @Test
    public void toDTOObjectReturnTeamDTO() {

        // arrange
        InternalTeamMother teamMother = new InternalTeamMother();
        InternalTeam team = teamMother.setId(IdGenerator.createId()).instance();

        PersonMother personMother = new PersonMother();
        Person trainer = personMother.setId(IdGenerator.createId()).instance();
        team.setTrainer(trainer);
        Person p1 = personMother.setId(IdGenerator.createId()).instance();
        Person p2 = personMother.setId(IdGenerator.createId()).instance();
        team.addPerson(p1);
        team.addPerson(p2);

        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();
        team.setSport(sport);

        SimplePersonDTO simplePersonDTO1 = new SimplePersonDTOImpl();
        simplePersonDTO1.setLName(p1.getLName());
        simplePersonDTO1.setFName(p1.getFName());
        SimplePersonDTO simplePersonDTO2 = new SimplePersonDTOImpl();
        simplePersonDTO1.setLName(p2.getLName());
        simplePersonDTO1.setFName(p2.getFName());
        SimplePersonDTO simpleTrainer = new SimplePersonDTOImpl();
        simplePersonDTO1.setLName(trainer.getLName());
        simplePersonDTO1.setFName(trainer.getFName());

        when(_simplePersonMapper.toDTOObject(p1)).thenReturn(simplePersonDTO1);
        when(_simplePersonMapper.toDTOObject(p2)).thenReturn(simplePersonDTO2);
        when(_simplePersonMapper.toDTOObject(trainer)).thenReturn(simpleTrainer);

        //act
        DisplayTeamDTO teamDTO = _displayTeamMapper.toDTOObject(team);


        //assert
        assertEquals(teamDTO.getId(), team.getId());
        assertEquals(teamDTO.getVersion(), team.getVersion());
        assertEquals(teamDTO.getName(), team.getName());
    }

    @Test
    public void toDTOObjectReturnNull() {

        // arrange
        InternalTeam team = null;

        PersonMother personMother = new PersonMother();
        Person trainer = personMother.setId(IdGenerator.createId()).instance();

        Person p1 = personMother.setId(IdGenerator.createId()).instance();
        Person p2 = personMother.setId(IdGenerator.createId()).instance();


        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();


        //act
        DisplayTeamDTO teamDTO = _displayTeamMapper.toDTOObject(team);


        //assert
        assertEquals(teamDTO, null);

    }
}
