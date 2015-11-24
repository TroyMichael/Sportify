package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTOImpl;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailMapper extends Mapper<TeamDetailDTO, InternalTeam> {

    private SimplePersonMapper _simplePersonMapper = new SimplePersonMapper();

    @Override
    public InternalTeam toDomainObject(TeamDetailDTO teamDetailDTO) {
        return null;
    }

    @Override
    public TeamDetailDTO toDTOObject(InternalTeam domainObject) {
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
