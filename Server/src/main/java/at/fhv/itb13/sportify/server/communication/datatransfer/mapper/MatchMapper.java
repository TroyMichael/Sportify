package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.*;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import org.hibernate.HibernateException;

import java.util.HashSet;
import java.util.Set;

import static at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus.FINISHED;
import static at.fhv.itb13.sportify.shared.communication.dtos.MatchStatus.PLANNED;

/**
 * Created by Caroline on 21.11.2015.
 *
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
            match.setId(matchDTO.getId());
            match.setVersion(matchDTO.getVersion());
            match.setStart(matchDTO.getStart());
            match.setDuration(matchDTO.getDuration());
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

                MatchDTOImpl.SimpleMatchTeamDTO matchTeam1 = matchDTO.getTeam1();
                MatchTeam mMatchTeam1 = new MatchTeam();
                mMatchTeam1.setMatch(match);
                Team team1 = _dbFacade.get(Team.class, matchTeam1.getId());

                if (team1 != null) {
                    mMatchTeam1.setTeam(team1);
                    team1.addMatchTeam(mMatchTeam1);
                    match.addMatchTeam(mMatchTeam1);
                }

                MatchDTOImpl.SimpleMatchTeamDTO matchTeam2 = matchDTO.getTeam2();
                MatchTeam mMatchTeam2 = new MatchTeam();
                mMatchTeam2.setMatch(match);
                Team team2 = _dbFacade.get(Team.class, matchTeam2.getId());

                if (team2 != null) {
                    mMatchTeam2.setTeam(team2);
                    team2.addMatchTeam(mMatchTeam2);
                    match.addMatchTeam(mMatchTeam2);
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
//

            if (domainObject.getMatchTeams().iterator().hasNext()) {
                MatchTeam mteam = domainObject.getMatchTeams().iterator().next();
                domainObject.getMatchTeams().remove(0);
                MatchDTOImpl.SimpleMatchTeamDTO team1 = new MatchDTOImpl.SimpleMatchTeamDTO();
                team1.setId(mteam.getId());
                team1.setName(mteam.getTeam().getName());
                matchDTO.setTeam1(team1);
            }

            if (domainObject.getMatchTeams().iterator().hasNext()) {
                MatchTeam mteam2 = domainObject.getMatchTeams().iterator().next();
                domainObject.getMatchTeams().remove(0);
                MatchDTOImpl.SimpleMatchTeamDTO team2 = new MatchDTOImpl.SimpleMatchTeamDTO();
                team2.setId(mteam2.getId());
                team2.setName(mteam2.getTeam().getName());
                matchDTO.setTeam2(team2);
            }
            return matchDTO;
        }
        return null;
    }

    public Set<MatchDTO> toDTOSet(Set<Match> matches) {
        Set<MatchDTO> tempMatches = new HashSet<>();
        matches.forEach(match -> tempMatches.add(toDTOObject(match)));
        return tempMatches;
    }

    public Match toExistingDomainObject(MatchDTO matchDTO) {
        if (matchDTO.getId() != null){
            try {
                _dbFacade.beginTransaction();
                Match match = _dbFacade.get(Match.class, matchDTO.getId());
                //set version?
                match.setVersion(matchDTO.getVersion());
                match.setStart(matchDTO.getStart());
                match.setDuration(matchDTO.getDuration());
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
                MatchDTOImpl.SimpleMatchTeamDTO matchTeam = matchDTO.getTeam1();
                MatchTeam mMatchTeam = new MatchTeam();
                mMatchTeam.setMatch(match);
                mMatchTeam.setTeam(_dbFacade.get(Team.class, matchTeam.getId()));
                match.addMatchTeam(mMatchTeam);

                MatchDTOImpl.SimpleMatchTeamDTO matchTeam2 = matchDTO.getTeam1();
                MatchTeam mMatchTeam2 = new MatchTeam();
                mMatchTeam2.setMatch(match);
                mMatchTeam2.setTeam(_dbFacade.get(Team.class, matchTeam2.getId()));
                match.addMatchTeam(mMatchTeam2);

                match.setTournament(_dbFacade.get(Tournament.class, matchDTO.getTournamentId()));
                _dbFacade.commitTransaction();
                return match;
            } catch (Exception e){
                _dbFacade.rollbackTransaction();
                e.printStackTrace();
            }
        }
        return null;
    }
}
