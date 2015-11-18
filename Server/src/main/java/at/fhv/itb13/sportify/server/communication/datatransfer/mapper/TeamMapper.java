package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTOImpl;
import org.hibernate.HibernateException;

/**
 * Created by Caroline on 10.11.2015.
 *
 */
public class TeamMapper extends Mapper <TeamDTO, Team> {
    DBFacade dbFacade = new DBFacadeImpl();

    @Override
    public Team toDomainObject(TeamDTO teamDTO) {
        if (teamDTO != null) {
            Team team = new Team();
            team.setName(teamDTO.getName());
            if (teamDTO.getId() != null){
                team.setId(teamDTO.getId());
            }
            try {
                dbFacade.beginTransaction();
                if (teamDTO.getPersonIds().size() >  0){
                    for (String personId : teamDTO.getPersonIds()) {
                        team.addPerson(dbFacade.get(Person.class, personId));
                    }
                }
                if (teamDTO.getRosterIds().size() > 0){
                    for (String rosterId : teamDTO.getRosterIds()) {
                        team.addRoster(dbFacade.get(Roster.class, rosterId));
                    }
                }
                if (teamDTO.getSportId().length() > 0){
                    team.setSport(dbFacade.get(Sport.class, teamDTO.getSportId()));
                }
                if (teamDTO.getTrainerId().length() > 0){
                    team.setTrainer(dbFacade.get(Person.class, teamDTO.getTrainerId()));
                }
                dbFacade.commitTransaction();
            } catch (HibernateException e) {
                dbFacade.rollbackTransaction();
            }
            return team;
        }
        return null;
    }

    @Override
    public TeamDTO toDTOObject(Team domainObject) {
        if (domainObject != null) {
            TeamDTO teamDTO = new TeamDTOImpl();
            teamDTO.setName(domainObject.getName());
            for (Person person : domainObject.getPersons()) {
                teamDTO.addPersonId(person.getId());
            }
            for (Roster roster : domainObject.getRosters()) {
                teamDTO.addRosterId(roster.getId());
            }
            teamDTO.setSportId(domainObject.getSport().getId());
            teamDTO.setTrainerId(domainObject.getTrainer().getId());
            teamDTO.setId(domainObject.getId());
            return teamDTO;
        }
        return null;
    }
}