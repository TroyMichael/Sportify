package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;

/**
 * Created by KYUSS on 23.11.2015.
 */
public class SimpleSportMapper extends Mapper<SimpleSportDTO, Sport>{
    @Override
    public Sport toDomainObject(SimpleSportDTO simpleSportDTO) {
        return null;
    }

    @Override
    public SimpleSportDTO toDTOObject(Sport domainObject) {
        return null;
    }
}
