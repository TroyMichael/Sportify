package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.Session;
import at.fhv.itb13.sportify.shared.communication.remote.SessionFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SessionFactoryImpl extends UnicastRemoteObject implements SessionFactory {

    public SessionFactoryImpl() throws RemoteException {
        super();
    }

    @Override
    public Session create(UserDTO userDTO) throws RemoteException {
        return SessionImpl.create(userDTO);
    }
}
