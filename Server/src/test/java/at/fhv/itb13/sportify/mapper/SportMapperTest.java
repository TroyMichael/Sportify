package at.fhv.itb13.sportify.mapper;

import at.fhv.itb13.sportify.database.*;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.MatchMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SportMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.model.*;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTOImpl;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Caroline on 21.11.2015.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonMapper.class)
public class SportMapperTest {

    @Mock
    private DBFacade _facade;

    private SportMapper _sportMapper;

    @Before
    public void setUp(){
        _sportMapper = new SportMapper(_facade);

    }

    @Test
    public void toDomainObjectReturnSport(){

        // arrange
        SportDTO sportDTO = new SportDTOImpl("sport");
        sportDTO.setId(IdGenerator.createId());

        DepartmentMother departmentMother = new DepartmentMother();
        Department dept = departmentMother.setId(IdGenerator.createId()).instance();
        sportDTO.setDepartment(dept.getId());

        TeamMother teamMother = new TeamMother();
        Team t1 = teamMother.setId(IdGenerator.createId()).instance();
        Team t2 = teamMother.setId(IdGenerator.createId()).instance();

        sportDTO.addTeam(t1.getId());
        sportDTO.addTeam(t2.getId());

        when(_facade.get(Team.class,t1.getId())).thenReturn(t1);
        when(_facade.get(Team.class,t2.getId())).thenReturn(t2);
        when(_facade.get(Department.class,dept.getId())).thenReturn(dept);

        //act
        Sport sport = _sportMapper.toDomainObject(sportDTO);

        //assert
        assertEquals(sportDTO.getId(), sport.getId());
        assertEquals(sportDTO.getVersion(), sport.getVersion());
        assertEquals(sportDTO.getDepartmentId(), sport.getDepartment().getId());
        assertEquals(sportDTO.getTeamIds().size(), sport.getTeams().size());
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).get(Team.class, t1.getId());
        verify(_facade, times(1)).get(Team.class, t2.getId());
        verify(_facade, times(1)).get(Department.class, dept.getId());
        verify(_facade, times(1)).commitTransaction();
    }

    @Test
    public void toDomainObjectReturnNull(){

        // arrange
        SportDTO sportDTO = null;

        DepartmentMother departmentMother = new DepartmentMother();
        Department dept = departmentMother.setId(IdGenerator.createId()).instance();


        TeamMother teamMother = new TeamMother();
        Team t1 = teamMother.setId(IdGenerator.createId()).instance();
        Team t2 = teamMother.setId(IdGenerator.createId()).instance();


        when(_facade.get(Team.class,t1.getId())).thenReturn(t1);
        when(_facade.get(Team.class,t2.getId())).thenReturn(t2);

        //act

        Sport sport = _sportMapper.toDomainObject(sportDTO);

        //assert
        verify(_facade, times(0)).beginTransaction();
        verify(_facade, times(0)).get(Team.class, t1.getId());
        verify(_facade, times(0)).get(Team.class, t2.getId());
        verify(_facade, times(0)).get(Department.class, dept.getId());
        verify(_facade, times(0)).commitTransaction();
        assertEquals(sport,null);

    }

    @Test
    public void toDTOObjectReturnMatchDTO(){

        // arrange
        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();

        DepartmentMother departmentMother = new DepartmentMother();
        Department dept = departmentMother.setId(IdGenerator.createId()).instance();
        sport.setDepartment(dept);

        TeamMother teamMother = new TeamMother();
        Team t1 = teamMother.setId(IdGenerator.createId()).instance();
        Team t2 = teamMother.setId(IdGenerator.createId()).instance();
        sport.addTeam(t1);
        sport.addTeam(t2);


        //act
        SportDTO sportDTO = _sportMapper.toDTOObject(sport);


        //assert
        assertEquals(sportDTO.getId(), sport.getId());
        assertEquals(sportDTO.getVersion(), sport.getVersion());
        assertEquals(sportDTO.getDepartmentId(), sport.getDepartment().getId());
        assertEquals(sportDTO.getTeamIds().size(), sport.getTeams().size());
    }

    @Test
    public void toDTOObjectReturnNull(){

        // arrange
        Sport sport = null;

        DepartmentMother departmentMother = new DepartmentMother();
        Department dept = departmentMother.setId(IdGenerator.createId()).instance();


        TeamMother teamMother = new TeamMother();
        Team t1 = teamMother.setId(IdGenerator.createId()).instance();
        Team t2 = teamMother.setId(IdGenerator.createId()).instance();


        //act
        SportDTO sportDTO = _sportMapper.toDTOObject(sport);


        //assert
        assertEquals(sportDTO,null);

    }
}
