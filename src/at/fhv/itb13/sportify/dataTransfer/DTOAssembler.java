package at.fhv.itb13.sportify.dataTransfer;

import at.fhv.itb13.sportify.dataTransfer.dataTransferObjects.PersonDTO;
import at.fhv.itb13.sportify.dataTransfer.exceptions.EntityHasNoDTOImplementationException;
import at.fhv.itb13.sportify.database.PersistentObjectImpl;
import at.fhv.itb13.sportify.model.entities.Person;

/**
 * Created by KYUSS on 27.10.2015.
 */
public class DTOAssembler {
    //tODO singleton useful? or spring?


    /*
    DTO Assembler will build Data Transfer Objects when given normal Entities
    */

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

    public void updateDTOObject (DTOObject object){
        //TODO UPDATE DTO OBJECT in database incl version control (only update when objct is updated / changed
    }
}
