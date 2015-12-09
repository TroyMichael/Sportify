package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import org.hibernate.HibernateException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus.FINISHED;
import static at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus.PLANNED;

/**
 * Created by Caroline on 21.11.2015.
 */
public class MatchMapper extends Mapper<MatchDTO, Match> {
    private DBFacade _dbFacade = new DBFacadeImpl();

    public MatchMapper() {
    }

    public MatchMapper(DBFacade facade) {
        _dbFacade = facade;
    }

    @Override
    public Match toDomainObject(MatchDTO matchDTO) {
        if (matchDTO != null) {
            Match match = new Match();
            if (matchDTO.getId() != null) {
                match.setId(matchDTO.getId());
            }
            if (matchDTO.getVersion() != null) {
                match.setVersion(matchDTO.getVersion());
            }
            match.setDuration(matchDTO.getDuration());
            match.setStart(matchDTO.getStart());

            switch (matchDTO.getMatchStatus()) {
                case "PLANNED":
                    match.setMatchStatus(PLANNED);
                    break;
                case "FINISHED":
                    match.setMatchStatus(FINISHED);
                    break;
                default:
                    match.setMatchStatus(PLANNED);
            }
            try {
                _dbFacade.beginTransaction();

                MatchTeam matchTeam1 = new MatchTeam();
                matchTeam1.setMatch(match);
                Team team1;
                if (matchDTO.getTeam1().getTeamID() != null) {
                    team1 = _dbFacade.get(Team.class, matchDTO.getTeam1().getTeamID());
                } else {
                    //old items from DB were saved wrong
                    //should not be necessary any more - needs testing
                    team1 = _dbFacade.get(Team.class, matchDTO.getTeam1().getId());
                }
                if (team1 != null) {
                    matchTeam1.setTeam(team1);
                    team1.addMatchTeam(matchTeam1);
                    match.addMatchTeam(matchTeam1);
                }

                MatchTeam matchTeam2 = new MatchTeam();
                matchTeam2.setMatch(match);
                Team team2;
                if (matchDTO.getTeam2().getTeamID() != null) {
                    team2 = _dbFacade.get(Team.class, matchDTO.getTeam2().getTeamID());
                } else {
                    //old items from DB were saved wrong
                    //should not be necessary any more - needs testing
                    team2 = _dbFacade.get(Team.class, matchDTO.getTeam2().getId());
                }
                if (team2 != null) {
                    matchTeam2.setTeam(team2);
                    team2.addMatchTeam(matchTeam2);
                    match.addMatchTeam(matchTeam2);
                }

                Tournament tournament = _dbFacade.get(Tournament.class, matchDTO.getTournamentId());
                if (tournament != null) {
                    match.setTournament(tournament);
                    tournament.addMatch(match);
                }
                _dbFacade.commitTransaction();
            } catch (HibernateException e) {
                _dbFacade.rollbackTransaction();
            }
            return match;
        }
        return null;
    }

    @Override
    public MatchDTO toDTOObject(Match domainObject) {
        if (domainObject != null) {
            MatchDTO matchDTO = new MatchDTOImpl();
            matchDTO.setId(domainObject.getId());
            matchDTO.setVersion(domainObject.getVersion());
            matchDTO.setStart(domainObject.getStart());
            matchDTO.setDuration(domainObject.getDuration());

            if (domainObject.getTournament() != null) {
                matchDTO.setTournamentId(domainObject.getTournament().getId());
            }

            if (domainObject.getMatchStatus() != null) {
                matchDTO.setMatchStatus(domainObject.getMatchStatus().name());
            }


            //load correct matchTeams
            Iterator iterator = domainObject.getMatchTeams().iterator();
            if (iterator.hasNext()) {
                MatchTeam matchTeam1 = (MatchTeam) iterator.next();
                iterator.remove();
                MatchDTOImpl.SimpleMatchTeamDTO simpleMatchTeamDTO1 = new MatchDTOImpl.SimpleMatchTeamDTO(matchTeam1.getTeam().getId());
                simpleMatchTeamDTO1.setId(matchTeam1.getId());
                //simpleMatchTeamDTO1.setTeamID(matchTeam1.getId());
                simpleMatchTeamDTO1.setName(matchTeam1.getTeam().getName());
                if (matchTeam1.getPoints() != null) {
                    simpleMatchTeamDTO1.setPoints(matchTeam1.getPoints());
                }
                matchDTO.setTeam1(simpleMatchTeamDTO1);
            }

            if (iterator.hasNext()) {
                MatchTeam matchTeam2 = (MatchTeam) iterator.next();
                iterator.remove();
                MatchDTOImpl.SimpleMatchTeamDTO simpleMatchTeamDTO2 = new MatchDTOImpl.SimpleMatchTeamDTO(matchTeam2.getTeam().getId());
                simpleMatchTeamDTO2.setId(matchTeam2.getId());
                //simpleMatchTeamDTO2.setTeamID(matchTeam2.getId());
                simpleMatchTeamDTO2.setName(matchTeam2.getTeam().getName());
                if (matchTeam2.getPoints() != null) {
                    simpleMatchTeamDTO2.setPoints(matchTeam2.getPoints());
                }
                matchDTO.setTeam2(simpleMatchTeamDTO2);
            }
            return matchDTO;
        }
        return null;
    }

    public Set<MatchDTO> toDTOSet(Set<Match> matches) {
        Set<MatchDTO> tempMatches = new HashSet<>();
        for (Match match : matches) {
            tempMatches.add(toDTOObject(match));
        }
        return tempMatches;
    }

    public Match toExistingDomainObject(MatchDTO matchDTO) {
        if (matchDTO.getId() != null) {
            try {
                _dbFacade.beginTransaction();
                Match match = _dbFacade.get(Match.class, matchDTO.getId());
                if (matchDTO.getVersion() != null) {
                    match.setVersion(matchDTO.getVersion());
                }
                match.setDuration(matchDTO.getDuration());
                match.setStart(matchDTO.getStart());
                match.setTournament(_dbFacade.get(Tournament.class, matchDTO.getTournamentId()));
                switch (matchDTO.getMatchStatus()) {
                    case "PLANNED":
                        match.setMatchStatus(PLANNED);
                        break;
                    case "FINISHED":
                        match.setMatchStatus(FINISHED);
                        break;
                    default:
                        match.setMatchStatus(PLANNED);
                }

                MatchDTOImpl.SimpleMatchTeamDTO simpleMatchTeamDTO1 = matchDTO.getTeam1();
                MatchTeam matchTeam1;
                if (_dbFacade.get(MatchTeam.class, simpleMatchTeamDTO1.getId()) != null) {
                    matchTeam1 = _dbFacade.get(MatchTeam.class, simpleMatchTeamDTO1.getId());
                } else {
                    matchTeam1 = new MatchTeam();
                }
                matchTeam1.setMatch(match);
                matchTeam1.setTeam(_dbFacade.get(Team.class, simpleMatchTeamDTO1.getTeamID()));
                matchTeam1.setPoints(matchDTO.getTeam1().getPoints());
                match.addMatchTeam(matchTeam1);

                MatchDTOImpl.SimpleMatchTeamDTO simpleMatchTeamDTO2 = matchDTO.getTeam2();
                MatchTeam matchTeam2;
                if (_dbFacade.get(MatchTeam.class, simpleMatchTeamDTO2.getId()) != null) {
                    matchTeam2 = _dbFacade.get(MatchTeam.class, simpleMatchTeamDTO2.getId());
                } else {
                    matchTeam2 = new MatchTeam();
                }
                matchTeam2.setMatch(match);
                matchTeam2.setTeam(_dbFacade.get(Team.class, simpleMatchTeamDTO2.getTeamID()));
                matchTeam2.setPoints(matchDTO.getTeam2().getPoints());
                match.addMatchTeam(matchTeam2);

                _dbFacade.commitTransaction();
                return match;
            } catch (Exception e) {
                _dbFacade.rollbackTransaction();
                e.printStackTrace();
            }
        }
        return null;
    }
}
