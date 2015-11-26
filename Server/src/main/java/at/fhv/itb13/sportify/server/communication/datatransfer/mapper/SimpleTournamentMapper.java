package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTOImpl;

/**
 * Created by KYUSS on 26.11.2015.
 */
public class SimpleTournamentMapper extends Mapper<SimpleTournamentDTO, Tournament>{
    @Override
    public Tournament toDomainObject(SimpleTournamentDTO simpleTournamentDTO) {
        return null;
    }

    @Override
    public SimpleTournamentDTO toDTOObject(Tournament domainObject) {
        if (domainObject != null){
            return new SimpleTournamentDTOImpl(domainObject.getDescription(), domainObject.getLocation(), domainObject.getStart());
        }
        return null;
    }
}
