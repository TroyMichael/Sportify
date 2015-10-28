package at.fhv.itb13.sportify.dataTransfer.mapper;

import at.fhv.itb13.sportify.dataTransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.dataTransfer.exceptions.DomainObjectIsNullException;

/**
 * Created by KYUSS on 28.10.2015.
 */
public abstract class Mapper <DTO, DO> {
    public abstract DO toDomainObject (DTO dto) throws DTOIsNullException;
    public abstract DTO toDTOObject (DO domainObject) throws DomainObjectIsNullException;
}
