package at.fhv.itb13.sportify.server.communication.atm;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.server.communication.remote.PersonServant;
import at.fhv.itb13.sportify.shared.communication.atm.ControllerFactory;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Niklas Fessler on 30.10.2015.
 */
public class ControllerFactoryImpl extends UnicastRemoteObject implements ControllerFactory {
    private static ControllerFactoryImpl _instance;

    protected ControllerFactoryImpl() throws RemoteException {
        super();
    }

    public static ControllerFactoryImpl getInstance() throws RemoteException {
        if (_instance == null){
            _instance = new ControllerFactoryImpl();
        }
        return _instance;
    }

    @Override
    public PersonRemote getPersonRemote() throws RemoteException {
        return new PersonServant();
    }
}
