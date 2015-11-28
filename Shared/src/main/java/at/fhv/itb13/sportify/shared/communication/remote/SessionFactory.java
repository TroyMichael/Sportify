package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionFactory extends Remote {

    Session create(UserDTO userDTO) throws RemoteException;
}
