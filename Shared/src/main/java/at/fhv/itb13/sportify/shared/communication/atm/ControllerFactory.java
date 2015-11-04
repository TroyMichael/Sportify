package at.fhv.itb13.sportify.shared.communication.atm;

import at.fhv.itb13.sportify.server.application.controller.PersonController;

import java.rmi.RemoteException;

/**
 * Created by Niklas Fessler on 11/4/15.
 */
public interface ControllerFactory {
    public PersonController getLazyLoadLogic() throws RemoteException;
}
