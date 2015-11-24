package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTOImpl;

import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailMapper extends Mapper<TeamDetailDTO, Team> {

    private SimplePersonMapper _simplePersonMapper = new SimplePersonMapper();

    @Override
    public Team toDomainObject(TeamDetailDTO teamDetailDTO) {
        return null;
    }

    @Override
    public TeamDetailDTO toDTOObject(Team domainObject) {
        TeamDetailDTO newTeamDetailDTO = new TeamDetailDTOImpl();

        if (domainObject != null) {
            newTeamDetailDTO.setName(domainObject.getName());

            domainObject.getPersons().forEach(p -> newTeamDetailDTO.addMember(_simplePersonMapper.toDTOObject(p)));

            if (domainObject.getTrainer() != null) {
                newTeamDetailDTO.setTrainer(_simplePersonMapper.toDTOObject(domainObject.getTrainer()));
            }

            if (domainObject.getSport() != null) {
                newTeamDetailDTO.setSport(new SimpleSportDTOImpl(domainObject.getSport().getName(), domainObject.getSport().getId()));
            }

            newTeamDetailDTO.setId(domainObject.getId());
            newTeamDetailDTO.setVersion(domainObject.getVersion());
        }
        return newTeamDetailDTO;
    }
}
