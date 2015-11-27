package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Tournament;

import java.util.List;

public abstract class Mapper<DTO, DO> {
    public abstract DO toDomainObject(DTO dto);

    public abstract DTO toDTOObject(DO domainObject);


}
