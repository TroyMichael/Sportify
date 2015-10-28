package at.fhv.itb13.sportify.dataTransfer;

import at.fhv.itb13.sportify.dataTransfer.dataTransferObjects.PersonDTO;
import at.fhv.itb13.sportify.dataTransfer.exceptions.DTOIsNotMappedException;
import at.fhv.itb13.sportify.dataTransfer.exceptions.EntityHasNoDTOImplementationException;
import at.fhv.itb13.sportify.database.DBFacade;
import at.fhv.itb13.sportify.database.PersistentObjectImpl;
import at.fhv.itb13.sportify.model.entities.Person;

/**
 * Created by KYUSS on 27.10.2015.
 */
public class DTOAssembler {
    /*
    DTO Assembler will build Data Transfer Objects when given normal Entities
    */
    private static DTOAssembler ourInstance = new DTOAssembler();
    public static DTOAssembler getInstance() {
        return ourInstance;
    }
    private DBFacade _facade;

    public DTOObject buildDTO (PersistentObjectImpl entity) throws EntityHasNoDTOImplementationException {
        //if entity is Person, PersonDTO will be build
        //TODO else if (for every other entity that needs DTOs)
        if (entity instanceof Person){
            //will return a new PersonDTO with information taken from the given entity
            return new PersonDTO().build(entity);
        } else {
            throw new EntityHasNoDTOImplementationException();
        }
    }

    public void buildAndUpdateDomainOBject (DTOObject dtoObject) throws DTOIsNotMappedException {
        PersistentObjectImpl persistentObject;
        if (dtoObject instanceof PersonDTO){
            persistentObject = new Person(((PersonDTO) dtoObject).getFName(), ((PersonDTO) dtoObject).getLName(), ((PersonDTO) dtoObject).getStreet(), ((PersonDTO) dtoObject).getHouseNumber(), ((PersonDTO) dtoObject).getPostalCode(), ((PersonDTO) dtoObject).getCity(), ((PersonDTO) dtoObject).getEmail(), ((PersonDTO) dtoObject).getBirthdate());
        } else {
            throw new DTOIsNotMappedException();
        }
        updateDTOObject(persistentObject);
        //TODO exception
    }

    private void updateDTOObject (PersistentObjectImpl object){
        _facade.beginTransaction();
        _facade.createOrUpdate(object);
        _facade.commitTransaction();
        _facade.rollbackTransaction();
    }
}
