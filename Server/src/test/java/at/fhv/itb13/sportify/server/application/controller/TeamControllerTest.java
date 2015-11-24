package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TeamMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.InternalTeamMother;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTOImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TeamController.class)
public class TeamControllerTest {

    @Mock
    private DBFacade _facade;
    @Mock
    private TeamMapper _teamMapper;

    private TeamController _teamController;

    @Before
    public void setUp() {
        _teamController = new TeamController(_facade, _teamMapper);
    }

    @Test
    public void createTeam() throws Exception {
        // arrange
        InternalTeam team = new InternalTeamMother().instance();
        TeamDTO teamDTO = new TeamDTOImpl();
        teamDTO.setName(team.getName());

        when(_teamMapper.toDomainObject(teamDTO)).thenReturn(team);

        // act
        _teamController.create(teamDTO);

        // assert
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).create(team);
        verify(_facade, times(1)).commitTransaction();
    }
}