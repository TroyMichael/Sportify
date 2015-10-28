package at.fhv.itb13.sportify.communication.servants;

<<<<<<< HEAD:Server/src/main/java/at/fhv/itb13/sportify/communication/servants/PersonServant.java
import at.fhv.itb13.sportify.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.communication.remote.PersonRemote;
=======
import at.fhv.itb13.sportify.application.controller.PersonController;
import at.fhv.itb13.sportify.communicaton.remote.PersonRemote;
import at.fhv.itb13.sportify.dataTransfer.dtoInterfaces.PersonDTO;
import at.fhv.itb13.sportify.model.entities.Person;
>>>>>>> 7b88525b61a55e06035b5e6b65cd60ffa15862d0:src/main/java/at/fhv/itb13/sportify/communicaton/servants/PersonServant.java

import java.rmi.RemoteException;

/**
 * Created by Patrick on 28.10.2015.
 */
public class PersonServant implements PersonRemote {
    private PersonController _personController;
    @Override
    public void create(PersonDTO personDto) throws RemoteException {
        //TODO
        _personController = PersonController.getInstance();
        _personController.create(personDto);
    }
}
