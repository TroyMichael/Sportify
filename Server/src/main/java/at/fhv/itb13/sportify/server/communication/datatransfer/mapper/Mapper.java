package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;

public abstract class Mapper<DTO, DO> {
    public abstract DO toDomainObject(DTO dto) throws DTOIsNullException;

    public abstract DTO toDTOObject(DO domainObject) throws DomainObjectIsNullException;
}
