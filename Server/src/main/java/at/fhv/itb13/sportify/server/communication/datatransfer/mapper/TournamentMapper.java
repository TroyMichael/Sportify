package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTOImpl;
import org.hibernate.HibernateException;

import java.util.HashSet;

/**
 * Created by KYUSS on 19.11.2015.
 *
 */
public class TournamentMapper extends Mapper <TournamentDTO, Tournament> {

    private DBFacade dbFacade;

    public TournamentMapper (){
        dbFacade = new DBFacadeImpl();
    }

    public TournamentMapper (DBFacade facade){
        dbFacade = facade;
    }

    public Tournament toExistingDomainObject (TournamentDTO tournamentDTO){
        if (tournamentDTO.getId() != null){
            try {
                dbFacade.beginTransaction();
                Tournament tournament = dbFacade.get(Tournament.class, tournamentDTO.getId());
                tournament.setDescription(tournamentDTO.getDescription());
                tournament.setSport(dbFacade.get(Sport.class, tournamentDTO.getSportID()));
                tournament.setLocation(tournamentDTO.getLocation());
                tournament.setStart(tournamentDTO.getStartDate());
                if (tournamentDTO.getMatchIDs().size() > 0){
                    tournament.setMatches(new HashSet<>());
                    for (String matchID : tournamentDTO.getMatchIDs()){
                        tournament.addMatch(dbFacade.get(Match.class, matchID));
                    }
                }
                if (tournamentDTO.getTeamIDs().size() > 0){
                    tournament.setTeams(new HashSet<>());
                    for (String teamID : tournamentDTO.getTeamIDs()){
                        tournament.addTeam(dbFacade.get(Team.class, teamID));
                    }
                }
                dbFacade.commitTransaction();
                return tournament;
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
            tournament.setDescription(tournamentDTO.getDescription());
            tournament.setStart(tournamentDTO.getStartDate());
            tournament.setLocation(tournamentDTO.getLocation());
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
                if (tournamentDTO.getTeamIDs().size() > 0){
                    for (String teamID : tournamentDTO.getTeamIDs()){
                        tournament.addTeam(dbFacade.get(Team.class, teamID));
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
            if (domainObject.getSport() != null){
                tournamentDTO.setSportID(domainObject.getSport().getId());
            }
            for (Match match : domainObject.getMatches()){
                tournamentDTO.addMatchID(match.getId());
            }
            for (Team team : domainObject.getTeams()){
                tournamentDTO.addTeamID(team.getId());
            }
            tournamentDTO.setStartDate(domainObject.getStart());
            tournamentDTO.setLocation(domainObject.getLocation());
            return tournamentDTO;
        }
        return null;
    }
}
