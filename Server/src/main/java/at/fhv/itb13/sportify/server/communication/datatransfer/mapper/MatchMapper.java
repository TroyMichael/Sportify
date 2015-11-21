package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;
import org.hibernate.HibernateException;

import static at.fhv.itb13.sportify.server.model.MatchStatus.FINISHED;
import static at.fhv.itb13.sportify.server.model.MatchStatus.PLANNED;

/**
 * Created by Caroline on 21.11.2015.
 */
public class MatchMapper extends Mapper<MatchDTO, Match> {
    DBFacade dbFacade = new DBFacadeImpl();

    public MatchMapper(){}
    public MatchMapper(DBFacade facade){
        dbFacade = facade;
    }

    @Override
    public Match toDomainObject(MatchDTO matchDTO) {

        if (matchDTO != null) {
            Match match = new Match();
            match.setId(matchDTO.getId());
            match.setStart(matchDTO.getStart());
            match.setDuration(matchDTO.getDuration());
            switch (matchDTO.getMatchStatus()) {
                case "PLANNED":
                    match.setMatchStatus(PLANNED);
                    break;
                case "FINNISHED":
                    match.setMatchStatus(FINISHED);
                    break;
                default:
                    match.setMatchStatus(PLANNED);
            }
            try {
                dbFacade.beginTransaction();
                for (String matchTeamId : matchDTO.getMatchTeamIds()) {
                    match.addMatchTeam(dbFacade.get(MatchTeam.class, matchTeamId));
                }
                match.setTournament(dbFacade.get(Tournament.class, matchDTO.getTournamentId()));
                dbFacade.commitTransaction();
            } catch (HibernateException e) {
                dbFacade.rollbackTransaction();
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
            matchDTO.setStart(domainObject.getStart());
            matchDTO.setDuration(domainObject.getDuration());
            if(domainObject.getTournament() != null){
                matchDTO.setTorunamentId(domainObject.getTournament().getId());
            }
            matchDTO.setMatchStatus(domainObject.getMatchStatus().name());
//            switch (domainObject.getMatchStatus()) {
//                case PLANNED:
//                    matchDTO.setMatchStatus("PLANNED");
//                    break;
//                case FINISHED:
//                    matchDTO.setMatchStatus("FINISHED");
//                    break;
//                default:
//                    matchDTO.setMatchStatus("PLANNED");
//            }
            for (MatchTeam matchTeam : domainObject.getMatchTeams()) {
                matchDTO.addMatchTeamId(matchTeam.getId());
            }
            return matchDTO;
        }

        return null;
    }
}
