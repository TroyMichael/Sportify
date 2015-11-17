package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.SimplePersonDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTOImpl;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailMapper extends Mapper<TeamDetailDTO, Team> {

    @Override
    public Team toDomainObject(TeamDetailDTO teamDetailDTO) {
        return null;
    }

    @Override
    public TeamDetailDTO toDTOObject(Team domainObject) {
        TeamDetailDTO newTeamDetailDTO = new TeamDetailDTOImpl();

        if (domainObject != null) {
            newTeamDetailDTO.setName(domainObject.getName());
            domainObject.getPersons().forEach(person -> newTeamDetailDTO.addMember(new SimplePersonDTOImpl(person.getFName(), person.getLName())));
            if (domainObject.getTrainer() != null) {
                newTeamDetailDTO.setTrainer(new SimplePersonDTOImpl(domainObject.getTrainer().getFName(), domainObject.getTrainer().getLName()));
            }
            if (domainObject.getSport() != null) {
                newTeamDetailDTO.setSport(new SimpleSportDTOImpl(domainObject.getSport().getName()));
            }

            newTeamDetailDTO.setId(domainObject.getId());
        }
        return newTeamDetailDTO;
    }
}
