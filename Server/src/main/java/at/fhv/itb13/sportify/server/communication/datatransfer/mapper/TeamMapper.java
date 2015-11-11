package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTOImpl;

/**
 * Created by Caroline on 10.11.2015.
 */
public class TeamMapper extends Mapper<TeamDTO, Team> {
    DBFacade dbFacade = new DBFacadeImpl();

    @Override
    public Team toDomainObject(TeamDTO teamDTO) throws DTOIsNullException {

        if (teamDTO != null) {
            Team team = new Team();
            team.setName(teamDTO.getName());
            try {
                dbFacade.beginTransaction();
                for (String personId : teamDTO.getPersonIds()) {
                    team.addPerson(dbFacade.get(Person.class, personId));
                }
                team.setSport(dbFacade.get(Sport.class, teamDTO.getSportId()));
                for (String rosterId : teamDTO.getRosterIds()) {
                    team.addRoster(dbFacade.get(Roster.class, rosterId));
                }
            } catch (Exception e) {
                dbFacade.rollbackTransaction();
            }

            return team;
        }
        return null;
    }

    @Override
    public TeamDTO toDTOObject(Team domainObject) throws DomainObjectIsNullException {

        if (domainObject != null) {
            TeamDTO teamDTO = new TeamDTOImpl();
            teamDTO.setName(domainObject.getName());
            for(Person p: domainObject.getPersons()){
                teamDTO.addPersonId(p.getId());
            }
            for(Roster r: domainObject.getRosters()){
                teamDTO.addRosterId(r.getId());
            }
            teamDTO.setSportId(domainObject.getSport().getId());
            return teamDTO;
        }

        return null;
    }
}
