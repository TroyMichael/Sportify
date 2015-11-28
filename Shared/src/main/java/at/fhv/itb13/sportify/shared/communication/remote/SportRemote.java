package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SportRemote extends Remote {
    List<SportDTO> getSports() throws RemoteException;
}
