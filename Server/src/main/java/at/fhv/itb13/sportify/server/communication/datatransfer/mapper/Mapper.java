package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.FacadeFailedException;

/**
 * Created by KYUSS on 28.10.2015.
 */
public abstract class Mapper<DTO, DO> {
    public abstract DO toDomainObject(DTO dto) throws DTOIsNullException, FacadeFailedException;

    public abstract DTO toDTOObject(DO domainObject) throws DomainObjectIsNullException;
}
