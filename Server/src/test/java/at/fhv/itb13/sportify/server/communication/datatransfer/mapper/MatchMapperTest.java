package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.MatchMother;
import at.fhv.itb13.sportify.server.database.MatchTeamMother;
import at.fhv.itb13.sportify.server.database.TournamentMother;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Caroline on 21.11.2015.
 */
/*
@RunWith(PowerMockRunner.class)
@PrepareForTest(PersonMapper.class)
public class MatchMapperTest {

    @Mock
    private DBFacade _facade;

    private MatchMapper _matchMapper;

    @Before
    public void setUp() {
        _matchMapper = new MatchMapper(_facade);

    }

    @Test
    public void toDomainObjectReturnMatch() {

        // arrange
        TournamentMother tournamentMother = new TournamentMother();
        Tournament t = tournamentMother.setId(IdGenerator.createId()).instance();
        MatchDTO matchDTO = new MatchDTOImpl(3, new Date(Calendar.getInstance().getTime().getTime()), t.getId(), "PLANNED");

        MatchTeamMother matchTeamMother = new MatchTeamMother();
        MatchTeam mt1 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt2 = matchTeamMother.setId(IdGenerator.createId()).instance();

        matchDTO.setTeam1();
        matchDTO.setTeam2();

        when(_facade.get(Team.class, mt1.getId())).thenReturn(mt1.getTeam());
        when(_facade.get(Team.class, mt2.getId())).thenReturn(mt2.getTeam());
        when(_facade.get(Tournament.class, t.getId())).thenReturn(t);

        //act
        Match match = _matchMapper.toDomainObject(matchDTO);

        //assert
        assertEquals(matchDTO.getId(), match.getId());
        assertEquals(matchDTO.getVersion(), match.getVersion());
        assertEquals(matchDTO.getDuration(), match.getDuration());
        assertEquals(matchDTO.getMatchStatus(), match.getMatchStatus().name());
        assertEquals(matchDTO.getStart(), match.getStart());
        assertEquals(matchDTO.getTournamentId(), match.getTournament().getId());
        verify(_facade, times(1)).beginTransaction();
        verify(_facade, times(1)).get(Team.class, mt1.getId());
        verify(_facade, times(1)).get(Team.class, mt2.getId());
        verify(_facade, times(1)).get(Team.class, mt3.getId());
        verify(_facade, times(1)).get(Tournament.class, t.getId());
        verify(_facade, times(1)).commitTransaction();
    }

    @Test
    public void toDomainObjectReturnNull() {

        // arrange
        MatchDTO matchnDTO = null;
        TournamentMother tournamentMother = new TournamentMother();
        Tournament t = tournamentMother.setId(IdGenerator.createId()).instance();

        MatchTeamMother matchTeamMother = new MatchTeamMother();
        MatchTeam mt1 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt2 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt3 = matchTeamMother.setId(IdGenerator.createId()).instance();

        when(_facade.get(MatchTeam.class, mt1.getId())).thenReturn(mt1);
        when(_facade.get(MatchTeam.class, mt2.getId())).thenReturn(mt2);
        when(_facade.get(MatchTeam.class, mt3.getId())).thenReturn(mt3);
        when(_facade.get(Tournament.class, t.getId())).thenReturn(t);

        //act
        Match match = _matchMapper.toDomainObject(matchnDTO);

        //assert
        verify(_facade, times(0)).beginTransaction();
        verify(_facade, times(0)).get(MatchTeam.class, mt1.getId());
        verify(_facade, times(0)).get(MatchTeam.class, mt2.getId());
        verify(_facade, times(0)).get(MatchTeam.class, mt3.getId());
        verify(_facade, times(0)).get(Tournament.class, t.getId());
        verify(_facade, times(0)).commitTransaction();
        assertEquals(match, null);

    }

    @Test
    public void toDTOObjectReturnMatchDTO() {

        // arrange
        MatchMother matchMother = new MatchMother();
        Match match = matchMother.setId(IdGenerator.createId()).instance();

        TournamentMother tournamentMother = new TournamentMother();
        Tournament t = tournamentMother.setId(IdGenerator.createId()).instance();

        MatchTeamMother matchTeamMother = new MatchTeamMother();
        MatchTeam mt1 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt2 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt3 = matchTeamMother.setId(IdGenerator.createId()).instance();

        match.addMatchTeam(mt1);
        match.addMatchTeam(mt2);
        match.addMatchTeam(mt3);
        match.setTournament(t);


        //act
        MatchDTO matchDTO = _matchMapper.toDTOObject(match);


        //assert
        assertEquals(matchDTO.getId(), match.getId());
        assertEquals(matchDTO.getVersion(), match.getVersion());
        assertEquals(matchDTO.getDuration(), match.getDuration());
        assertEquals(matchDTO.getMatchStatus(), match.getMatchStatus().name());
        assertEquals(matchDTO.getStart(), match.getStart());
        assertEquals(matchDTO.getTournamentId(), match.getTournament().getId());
        assertEquals(matchDTO.getMatchTeamIds().size(), match.getMatchTeams().size());

    }

    @Test
    public void toDTOObjectReturnNull() {

        // arrange
        Match match = null;

        TournamentMother tournamentMother = new TournamentMother();
        Tournament t = tournamentMother.setId(IdGenerator.createId()).instance();

        MatchTeamMother matchTeamMother = new MatchTeamMother();
        MatchTeam mt1 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt2 = matchTeamMother.setId(IdGenerator.createId()).instance();
        MatchTeam mt3 = matchTeamMother.setId(IdGenerator.createId()).instance();


        //act
        MatchDTO matchDTO = _matchMapper.toDTOObject(match);


        //assert
        assertEquals(matchDTO, null);

    }
}
*/