package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.*;
import at.fhv.itb13.sportify.server.model.*;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTOImpl;
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
@PrepareForTest(TeamMapper.class)
public class TeamMapperTest {

    @Mock
    private DBFacade _facade;

    private TeamMapper _teamMapper;

    @Before
    public void setUp() {
        _teamMapper = new TeamMapper(_facade);
    }

    @Test
    public void toDomainObjectReturnTeam() {

        // arrange
        TeamDTO teamDTO = new TeamDTOImpl();
        teamDTO.setName("name");
        teamDTO.setVersion(0);

        PersonMother personMother = new PersonMother();
        Person trainer = personMother.setId(IdGenerator.createId()).instance();
        teamDTO.setTrainerId(trainer.getId());
        Person p1 = personMother.setId(IdGenerator.createId()).instance();
        Person p2 = personMother.setId(IdGenerator.createId()).instance();
        teamDTO.addPersonId(p1.getId());
        teamDTO.addPersonId(p2.getId());

        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();
        teamDTO.setSportId(sport.getId());

        when(_facade.get(Person.class,trainer.getId())).thenReturn(trainer);
        when(_facade.get(Sport.class,sport.getId())).thenReturn(sport);
        when(_facade.get(Person.class,p1.getId())).thenReturn(p1);
        when(_facade.get(Person.class,p2.getId())).thenReturn(p2);


        //act
        InternalTeam team = _teamMapper.toDomainObject(teamDTO);

        //assert
        assertEquals(teamDTO.getId(), team.getId());
        assertEquals(teamDTO.getVersion(), team.getVersion());
        assertEquals(teamDTO.getName(), team.getName());
        assertEquals(teamDTO.getTrainerId(), team.getTrainer().getId());
        assertEquals(teamDTO.getSportId(), team.getSport().getId());
        assertEquals(teamDTO.getPersonIds().size(), team.getPersons().size());
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).get(Person.class,p1.getId());
        verify(_facade, times(1)).get(Person.class,p2.getId());
        verify(_facade, times(1)).get(Person.class, trainer.getId());
        verify(_facade,times(1)).get(Sport.class, sport.getId());
        verify(_facade, times(1)).commitTransaction();
    }

    @Test
    public void toDomainObjectReturnNull() {

        // arrange
        TeamDTO teamDTO = null;


        PersonMother personMother = new PersonMother();
        Person trainer = personMother.setId(IdGenerator.createId()).instance();

        Person p1 = personMother.setId(IdGenerator.createId()).instance();
        Person p2 = personMother.setId(IdGenerator.createId()).instance();


        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();


        when(_facade.get(Person.class,trainer.getId())).thenReturn(trainer);
        when(_facade.get(Sport.class,sport.getId())).thenReturn(sport);
        when(_facade.get(Person.class,p1.getId())).thenReturn(p1);
        when(_facade.get(Person.class,p2.getId())).thenReturn(p2);

        //act
        InternalTeam team = _teamMapper.toDomainObject(teamDTO);

        //assert
        verify(_facade, times(0)).beginTransaction();
        verify(_facade, times(0)).get(Person.class, p1.getId());
        verify(_facade, times(0)).get(Person.class,p2.getId());
        verify(_facade, times(0)).get(Person.class, trainer.getId());
        verify(_facade,times(0)).get(Sport.class, sport.getId());
        verify(_facade, times(0)).commitTransaction();
        assertEquals(team, null);

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


        //act
        TeamDTO teamDTO = _teamMapper.toDTOObject(team);


        //assert
        assertEquals(teamDTO.getId(), team.getId());
        assertEquals(teamDTO.getVersion(), team.getVersion());
        assertEquals(teamDTO.getName(), team.getName());
        assertEquals(teamDTO.getTrainerId(), team.getTrainer().getId());
        assertEquals(teamDTO.getSportId(), team.getSport().getId());
        assertEquals(teamDTO.getPersonIds().size(), team.getPersons().size());
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
        TeamDTO teamDTO = _teamMapper.toDTOObject(team);


        //assert
        assertEquals(teamDTO, null);

    }
}
