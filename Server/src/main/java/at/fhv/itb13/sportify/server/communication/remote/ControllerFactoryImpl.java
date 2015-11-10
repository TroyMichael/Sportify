package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.shared.communication.remote.ControllerFactory;
import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;
import at.fhv.itb13.sportify.shared.communication.remote.TeamRemote;
import at.fhv.itb13.sportify.shared.communication.remote.UserRemote;

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

    @Override
    public UserRemote getUserRemote() throws RemoteException {
        return new UserServant();
    }
    @Override
    public TeamRemote getTeamRemote() throws RemoteException{
        return new TeamServant();
    }

}
