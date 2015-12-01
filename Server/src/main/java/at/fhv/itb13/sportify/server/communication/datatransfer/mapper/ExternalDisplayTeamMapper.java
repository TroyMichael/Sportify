package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.ExternalTeam;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamlDTOImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.ExternalDisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;

/**
 * Created by Michael on 01.12.2015.
 *
 */
public class ExternalDisplayTeamMapper extends Mapper<ExternalDisplayTeamDTO, ExternalTeam> {

    private SimpleTournamentMapper _simpleTournamentMapper = new SimpleTournamentMapper();
    private DBFacade _facade;

    public ExternalDisplayTeamMapper(){
        _facade = new DBFacadeImpl();
    }

    @Override
    public ExternalTeam toDomainObject(ExternalDisplayTeamDTO externalDisplayTeamDTO) {
        ExternalTeam newExternalTeam = null;

        if (externalDisplayTeamDTO != null) {
            newExternalTeam = new ExternalTeam();
            newExternalTeam.setId(externalDisplayTeamDTO.getId());
            newExternalTeam.setVersion(externalDisplayTeamDTO.getVersion());
            newExternalTeam.setName(externalDisplayTeamDTO.getName());

            try {
                _facade.beginTransaction();
                newExternalTeam.setSport(_facade.get(Sport.class, externalDisplayTeamDTO.getSport().getId()));
                _facade.commitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
                _facade.rollbackTransaction();
            }

        }
        return newExternalTeam;
    }

    @Override
    public ExternalDisplayTeamDTO toDTOObject(ExternalTeam domainObject) {
        if (domainObject != null) {
            ExternalDisplayTeamDTO newExternalDisplayTeamDTO = new ExternalDisplayTeamDTO(domainObject.getName());

            if (domainObject.getSport() != null) {
                newExternalDisplayTeamDTO.setSport(new SimpleSportDTOImpl(domainObject.getSport().getName(), domainObject.getSport().getId()));
            }

            if (domainObject.getTournaments() != null){
                for (Tournament tournament : domainObject.getTournaments()){
                    newExternalDisplayTeamDTO.addSimpleTournamentDTO(_simpleTournamentMapper.toDTOObject(tournament));
                }
            }

            newExternalDisplayTeamDTO.setId(domainObject.getId());
            newExternalDisplayTeamDTO.setVersion(domainObject.getVersion());
            return newExternalDisplayTeamDTO;
        }
        return null;
    }
}
