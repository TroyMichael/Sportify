package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTOImpl;

/**
 * Created by mod on 11/23/15.
 */
public class SimpleTournamentMapper extends Mapper <SimpleTournamentDTO, Tournament>{

    private DBFacade _facade;
    private SimpleSportMapper _simpleSportMapper;

    public SimpleTournamentMapper (){
        _facade = new DBFacadeImpl();
    }

    public SimpleTournamentMapper (DBFacade facade){
        _facade = facade;
    }

    @Override
    public Tournament toDomainObject(SimpleTournamentDTO simpleTournamentDTO) {
        return null;
    }

    @Override
    public SimpleTournamentDTO toDTOObject(Tournament domainObject) {
        if (domainObject != null){
            SimpleTournamentDTO simpleTournamentDTO = new SimpleTournamentDTOImpl();
            simpleTournamentDTO.setLocation(domainObject.getLocation());
            simpleTournamentDTO.setStartDate(domainObject.getStart());
            simpleTournamentDTO.setDescription(domainObject.getDescription());
            if (domainObject.getSport() != null){
                simpleTournamentDTO.setSport(_simpleSportMapper.toDTOObject(domainObject.getSport()));
            }
            if (domainObject.getMatches() != null){

            }
            try {
                _facade.beginTransaction();

                _facade.commitTransaction();
            }catch (Exception e){
                e.printStackTrace();
                _facade.rollbackTransaction();
            }
        }
        return null;
    }
}
