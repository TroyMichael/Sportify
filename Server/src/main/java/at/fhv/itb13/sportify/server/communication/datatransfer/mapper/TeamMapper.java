package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTOImpl;

/**
 * Created by Caroline on 10.11.2015.
 */
public class TeamMapper extends Mapper<TeamDTO, Team> {
    @Override
    public Team toDomainObject(TeamDTO teamDTO) {
        if (teamDTO != null) {
            Team team = new Team();
            team.setName(teamDTO.getName());
            return team;
        }
        return null;
    }

    @Override
    public TeamDTO toDTOObject(Team domainObject) {
        if (domainObject != null) {
            TeamDTO teamDTO = new TeamDTOImpl();
            teamDTO.setName(domainObject.getName());
            return teamDTO;
        }
        return null;
    }
}
