package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTOImpl;
import org.hibernate.HibernateException;

import java.util.HashSet;

/**
 * Created by KYUSS on 19.11.2015.
 */
public class TournamentMapper extends Mapper <TournamentDTO, Tournament> {

    private DBFacade dbFacade;

    public TournamentMapper (){
        dbFacade = new DBFacadeImpl();
    }

    public TournamentDTO toExistingDomainObject (TournamentDTO tournamentDTO){
        if (tournamentDTO.getId() != null){
            try {
                dbFacade.beginTransaction();
                Tournament tournament = dbFacade.get(Tournament.class, tournamentDTO.getId());
                tournament.setDescription(tournament.getDescription());
                tournament.setSport(dbFacade.get(Sport.class, tournamentDTO.getSportID()));
                if (tournamentDTO.getMatchIDs().size() > 0){
                    tournament.setMatches(new HashSet<>());
                    for (String matchID : tournamentDTO.getMatchIDs()){
                        tournament.addMatch(dbFacade.get(Match.class, matchID));
                    }
                }
                dbFacade.commitTransaction();
                return tournamentDTO;
            } catch (HibernateException e){
                dbFacade.rollbackTransaction();
            }
        }
        return null;
    }

    @Override
    public Tournament toDomainObject(TournamentDTO tournamentDTO) {
        if (tournamentDTO != null) {
            Tournament tournament = new Tournament();
            tournament.setDescription(tournament.getDescription());
            if (tournamentDTO.getId() != null){
                tournament.setId(tournamentDTO.getId());
            }
            if (tournamentDTO.getVersion() != null){
                tournament.setVersion(tournamentDTO.getVersion());
            }
            try {
                dbFacade.beginTransaction();
                if (tournamentDTO.getMatchIDs().size() > 0){
                    for (String matchID : tournamentDTO.getMatchIDs()){
                        tournament.addMatch(dbFacade.get(Match.class, matchID));
                    }
                }
                if (tournamentDTO.getSportID().length() > 0){
                    tournament.setSport(dbFacade.get(Sport.class, tournamentDTO.getSportID()));
                }
                dbFacade.commitTransaction();
            } catch (HibernateException e) {
                dbFacade.rollbackTransaction();
            }
            return tournament;
        }
        return null;
    }

    @Override
    public TournamentDTO toDTOObject(Tournament domainObject) {
        if (domainObject != null){
            TournamentDTO tournamentDTO = new TournamentDTOImpl();
            tournamentDTO.setId(domainObject.getId());
            tournamentDTO.setVersion(domainObject.getVersion());
            tournamentDTO.setDescription(domainObject.getDescription());
            tournamentDTO.setSportID(domainObject.getSport().getId());
            for (Match match : domainObject.getMatches()){
                tournamentDTO.addMatchID(match.getId());
            }
            return tournamentDTO;
        }
        return null;
    }
}
