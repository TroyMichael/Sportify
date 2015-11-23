package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;


import at.fhv.itb13.sportify.database.MatchMother;
import at.fhv.itb13.sportify.database.SportMother;
import at.fhv.itb13.sportify.database.TeamMother;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTOImpl;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by KYUSS on 22.11.2015.
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(TournamentMapper.class)
public class TournamentMapperTest {

    @Mock
    private DBFacade _facade;

    private TournamentMapper _tournamentMapper;

    @Before
    public void setUp (){
        _tournamentMapper = new TournamentMapper(_facade);
    }

    public void testToExistingDomainObjectReturnTournament() {
        //todo
    }

    public void testToExistingDomainObjectReturnNull() {
        //todo
    }

    @Test
    public void testToDomainObjectReturnTournament() {
        //arrange
        TournamentDTO tournamentDTO = new TournamentDTOImpl();

        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();

        tournamentDTO.setSportID(sport.getId());
        tournamentDTO.setStartDate(new Date());
        tournamentDTO.setLocation("Muntlix!");
        tournamentDTO.setDescription("description of a new tournament");

        MatchMother matchMother = new MatchMother();
        Match match1 = matchMother.setId(IdGenerator.createId()).instance();
        Match match2 = matchMother.setId(IdGenerator.createId()).instance();
        Match match3 = matchMother.setId(IdGenerator.createId()).instance();

        tournamentDTO.addMatchID(match1.getId());
        tournamentDTO.addMatchID(match2.getId());
        tournamentDTO.addMatchID(match3.getId());

        TeamMother teamMother = new TeamMother();
        Team team1 = teamMother.setId(IdGenerator.createId()).instance();
        Team team2 = teamMother.setId(IdGenerator.createId()).instance();
        Team team3 = teamMother.setId(IdGenerator.createId()).instance();

        tournamentDTO.addTeamID(team1.getId());
        tournamentDTO.addTeamID(team2.getId());
        tournamentDTO.addTeamID(team3.getId());

        when(_facade.get(Sport.class, sport.getId())).thenReturn(sport);
        when(_facade.get(Match.class, match1.getId())).thenReturn(match1);
        when(_facade.get(Match.class,match2.getId())).thenReturn(match2);
        when(_facade.get(Match.class,match3.getId())).thenReturn(match3);
        when(_facade.get(Team.class,team1.getId())).thenReturn(team1);
        when(_facade.get(Team.class,team2.getId())).thenReturn(team2);
        when(_facade.get(Team.class,team3.getId())).thenReturn(team3);

        //act
        Tournament tournament = _tournamentMapper.toDomainObject(tournamentDTO);

        //assert
        assertEquals(tournament.getDescription(), tournamentDTO.getDescription());
        assertEquals(tournament.getLocation(), tournamentDTO.getLocation());
        assertEquals(tournament.getSport().getId(), tournamentDTO.getSportID());
        assertEquals(tournament.getStart(), tournamentDTO.getStartDate());
        assertEquals(tournament.getMatches().size(), tournamentDTO.getMatchIDs().size());
        assertEquals(tournament.getTeams().size(), tournamentDTO.getTeamIDs().size());
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).get(Match.class, match1.getId());
        verify(_facade, times(1)).get(Match.class, match2.getId());
        verify(_facade, times(1)).get(Match.class, match3.getId());
        verify(_facade, times(1)).commitTransaction();
    }

    public void testToDomainObjectReturnNull() {

    }

    public void testToDTOObjectReturnTournamentDTO() {

    }

    public void testToDTOObjectReturnNull() {

    }
}