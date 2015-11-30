package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTOImpl;

/**
 * Created by KYUSS on 23.11.2015.
 *
 */

public class SimpleSportMapper extends Mapper<SimpleSportDTO, Sport>{
    @Override
    public Sport toDomainObject(SimpleSportDTO simpleSportDTO) {
        return null;
    }

    @Override
    public SimpleSportDTO toDTOObject(Sport domainObject) {
        if (domainObject != null){
            SimpleSportDTO simpleSportDTO = new SimpleSportDTOImpl(domainObject.getName(), domainObject.getId());
            return simpleSportDTO;
        }
        return null;
    }
}
