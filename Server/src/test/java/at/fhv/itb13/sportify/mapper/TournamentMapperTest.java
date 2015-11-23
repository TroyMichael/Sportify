package at.fhv.itb13.sportify.mapper;


import at.fhv.itb13.sportify.database.MatchMother;
import at.fhv.itb13.sportify.database.SportMother;
import at.fhv.itb13.sportify.database.TeamMother;
import at.fhv.itb13.sportify.database.TournamentMother;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TournamentMapper;
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
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by KYUSS on 22.11.2015.
 *
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

    @Test
    public void testToExistingDomainObjectReturnTournament() {
        //arrange
        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();

        Tournament tournament = new Tournament("tournament from database", sport);
        tournament.setId("id_from_database");
        tournament.setLocation("database location");
        tournament.setStart(new Date());

        MatchMother matchMother = new MatchMother();
        Match match1 = matchMother.setId(IdGenerator.createId()).instance();
        Match match2 = matchMother.setId(IdGenerator.createId()).instance();
        Match match3 = matchMother.setId(IdGenerator.createId()).instance();

        tournament.addMatch(match1);
        tournament.addMatch(match2);
        tournament.addMatch(match3);

        Sport sportForDTO = sportMother.setId(IdGenerator.createId()).instance();

        Match matchForDTO1 = matchMother.setId(IdGenerator.createId()).instance();
        Match matchForDTO2 = matchMother.setId(IdGenerator.createId()).instance();
        Match matchForDTO3 = matchMother.setId(IdGenerator.createId()).instance();

        HashSet<String> matchIDs = new HashSet<>();
        matchIDs.add(matchForDTO1.getId());
        matchIDs.add(matchForDTO2.getId());
        matchIDs.add(matchForDTO3.getId());

        TournamentDTO tournamentDTO = new TournamentDTOImpl("new object description", sportForDTO.getId(), matchIDs);

        when(_facade.get(Tournament.class, tournamentDTO.getId())).thenReturn(tournament);
        when(_facade.get(Sport.class, sport.getId())).thenReturn(sport);
        when(_facade.get(Match.class, match1.getId())).thenReturn(match1);
        when(_facade.get(Match.class, match2.getId())).thenReturn(match2);
        when(_facade.get(Match.class, match3.getId())).thenReturn(match3);
        when(_facade.get(Match.class, matchForDTO1.getId())).thenReturn(matchForDTO1);
        when(_facade.get(Match.class, matchForDTO2.getId())).thenReturn(matchForDTO2);
        when(_facade.get(Match.class, matchForDTO3.getId())).thenReturn(matchForDTO3);

        //act
        Tournament existingTournament = _tournamentMapper.toExistingDomainObject(tournamentDTO);

        //assert
        assertEquals(existingTournament.getLocation(), tournament.getLocation());
        assertEquals(existingTournament.getDescription(), tournamentDTO.getDescription());
        assertEquals(tournament.getId(), existingTournament.getId());

        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).get(Tournament.class, tournamentDTO.getId());
        verify(_facade, times(1)).commitTransaction();
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

    @Test
    public void testToDomainObjectReturnNull() {
        //arrange
        TournamentDTO tournamentDTO = null;

        MatchMother matchMother = new MatchMother();
        Match match1 = matchMother.setId(IdGenerator.createId()).instance();
        Match match2 = matchMother.setId(IdGenerator.createId()).instance();

        when(_facade.get(Match.class,match1.getId())).thenReturn(match1);
        when(_facade.get(Match.class,match2.getId())).thenReturn(match2);

        //act
        Tournament tournament = _tournamentMapper.toDomainObject(tournamentDTO);

        //assert
        verify(_facade, times(0)).beginTransaction();
        verify(_facade, times(0)).get(Match.class, match1.getId());
        verify(_facade, times(0)).get(Match.class, match2.getId());
        verify(_facade, times(0)).commitTransaction();
    }

    @Test
    public void testToDTOObjectReturnTournamentDTO() {
        //arrange
        TournamentMother tournamentMother = new TournamentMother();
        Tournament tournament = tournamentMother.setId(IdGenerator.createId()).instance();

        TeamMother teamMother = new TeamMother();
        Team team1 = teamMother.setId(IdGenerator.createId()).instance();
        Team team2 = teamMother.setId(IdGenerator.createId()).instance();
        Team team3 = teamMother.setId(IdGenerator.createId()).instance();

        tournament.addTeam(team1);
        tournament.addTeam(team2);
        tournament.addTeam(team3);

        MatchMother matchMother = new MatchMother();
        Match match1 = matchMother.setId(IdGenerator.createId()).instance();
        Match match2 = matchMother.setId(IdGenerator.createId()).instance();
        Match match3 = matchMother.setId(IdGenerator.createId()).instance();

        tournament.addMatch(match1);
        tournament.addMatch(match2);
        tournament.addMatch(match3);

        SportMother sportMother = new SportMother();
        Sport sport = sportMother.setId(IdGenerator.createId()).instance();

        tournament.setSport(sport);

        //act
        TournamentDTO tournamentDTO = _tournamentMapper.toDTOObject(tournament);

        //assert
        assertEquals(tournament.getDescription(), tournamentDTO.getDescription());
        assertEquals(tournament.getLocation(), tournamentDTO.getLocation());
        assertEquals(tournament.getSport().getId(), tournamentDTO.getSportID());
        assertEquals(tournament.getStart(), tournamentDTO.getStartDate());
        assertEquals(tournament.getMatches().size(), tournamentDTO.getMatchIDs().size());
        assertEquals(tournament.getTeams().size(), tournamentDTO.getTeamIDs().size());

    }
}