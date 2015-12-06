package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatchRemote extends Remote {

    void create(MatchDTO matchDto) throws RemoteException, NotAuthorizedException;
    void update (MatchDTO matchDTO) throws RemoteException, NotAuthorizedException;
}
