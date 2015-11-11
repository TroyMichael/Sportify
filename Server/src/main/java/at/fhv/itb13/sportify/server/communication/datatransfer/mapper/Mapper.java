package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

public abstract class Mapper<DTO, DO> {
    public abstract DO toDomainObject(DTO dto);

    public abstract DTO toDTOObject(DO domainObject);
}
