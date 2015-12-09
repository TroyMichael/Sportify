package at.fhv.itb13.sportify.shared.communication.remote;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageRemote extends Remote {

    Serializable getMessage(String username) throws RemoteException;

    void sendMessage(String username, Serializable objectMessage) throws RemoteException;
}
