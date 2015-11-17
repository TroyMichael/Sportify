package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public interface TeamDetailRemote extends Remote {

    List<TeamDetailDTO> getAllTeams() throws RemoteException;
}
