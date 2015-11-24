package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamlDTOImpl;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class DisplayTeamMapper extends Mapper<DisplayTeamDTO, InternalTeam> {

    private SimplePersonMapper _simplePersonMapper = new SimplePersonMapper();

    @Override
    public InternalTeam toDomainObject(DisplayTeamDTO displayTeamDTO) {
        return null;
    }

    @Override
    public DisplayTeamDTO toDTOObject(InternalTeam domainObject) {
        DisplayTeamDTO newDisplayTeamDTO = new DisplayTeamlDTOImpl();

        if (domainObject != null) {
            newDisplayTeamDTO.setName(domainObject.getName());

            domainObject.getPersons().forEach(p -> newDisplayTeamDTO.addMember(_simplePersonMapper.toDTOObject(p)));

            if (domainObject.getTrainer() != null) {
                newDisplayTeamDTO.setTrainer(_simplePersonMapper.toDTOObject(domainObject.getTrainer()));
            }

            if (domainObject.getSport() != null) {
                newDisplayTeamDTO.setSport(new SimpleSportDTOImpl(domainObject.getSport().getName(), domainObject.getSport().getId()));
            }

            newDisplayTeamDTO.setId(domainObject.getId());
            newDisplayTeamDTO.setVersion(domainObject.getVersion());
        }
        return newDisplayTeamDTO;
    }
}
