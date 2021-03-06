package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamlDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;

/**
 * Created by Michael on 16.11.2015.
 */
public class DisplayTeamMapper extends Mapper<DisplayTeamDTO, InternalTeam> {

    private SimplePersonMapper _simplePersonMapper = new SimplePersonMapper();
    private SimpleTournamentMapper _simpleTournamentMapper = new SimpleTournamentMapper();

    public DisplayTeamMapper() {
    }

    public DisplayTeamMapper(SimplePersonMapper simplePersonMapper) {
        _simplePersonMapper = simplePersonMapper;
    }

    @Override
    public InternalTeam toDomainObject(DisplayTeamDTO displayTeamDTO) {
        return null;
    }

    @Override
    public DisplayTeamDTO toDTOObject(InternalTeam domainObject) {

        if (domainObject != null) {
            DisplayTeamDTO newDisplayTeamDTO = new DisplayTeamlDTOImpl();
            newDisplayTeamDTO.setName(domainObject.getName());

            for (Person person : domainObject.getPersons()) {
                newDisplayTeamDTO.addMember(_simplePersonMapper.toDTOObject(person));
            }

            if (domainObject.getTrainer() != null) {
                newDisplayTeamDTO.setTrainer(_simplePersonMapper.toDTOObject(domainObject.getTrainer()));
            }

            if (domainObject.getSport() != null) {
                newDisplayTeamDTO.setSport(new SimpleSportDTOImpl(domainObject.getSport().getName(), domainObject.getSport().getId()));
            }

            if (domainObject.getTournaments() != null) {
                for (Tournament tournament : domainObject.getTournaments()) {
                    newDisplayTeamDTO.addSimpleTournamentDTO(_simpleTournamentMapper.toDTOObject(tournament));
                }
            }

            newDisplayTeamDTO.setId(domainObject.getId());
            newDisplayTeamDTO.setVersion(domainObject.getVersion());

            return newDisplayTeamDTO;
        }
        return null;
    }
}
