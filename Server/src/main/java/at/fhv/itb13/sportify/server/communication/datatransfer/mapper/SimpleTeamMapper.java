package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTeamDTOImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 02.12.2015.
 *
 */
public class SimpleTeamMapper extends Mapper<SimpleTeamDTO, Team> {
    @Override
    public Team toDomainObject(SimpleTeamDTO simpleTeamDTO) {
        return null;
    }

    @Override
    public SimpleTeamDTO toDTOObject(Team domainObject) {
        SimpleTeamDTO newSimpleTeam = null;
        if (domainObject != null) {
            newSimpleTeam = new SimpleTeamDTOImpl(domainObject.getName());
            newSimpleTeam.setId(domainObject.getId());
            newSimpleTeam.setVersion(domainObject.getVersion());
        }
        return newSimpleTeam;
    }

    public List<SimpleTeamDTO> toDTOList(Set<Team> teams) {
        List<SimpleTeamDTO> _teams = new LinkedList<>();
        teams.forEach(team -> _teams.add(toDTOObject(team)));
        return _teams;
    }
}
