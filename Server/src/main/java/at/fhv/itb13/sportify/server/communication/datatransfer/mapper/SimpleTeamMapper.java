package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTeamDTOImpl;

import java.util.HashSet;
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

    public Set<SimpleTeamDTO> toDTOSet(Set<Team> teams) {
        Set<SimpleTeamDTO> _teams = new HashSet<>();
        teams.forEach(team -> _teams.add(toDTOObject(team)));
        return _teams;
    }
}
