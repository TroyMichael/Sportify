package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public interface DisplayTeamRemote extends Remote {

    List<DisplayTeamDTO> getAllTeams() throws RemoteException;
}
