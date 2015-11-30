package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by KYUSS on 29.11.2015.
 *
 */
public interface MessageRemote extends Remote {

    Serializable getMessage(String username) throws RemoteException;

    void sendMessage(String username, Serializable objectMessage) throws RemoteException;
}
