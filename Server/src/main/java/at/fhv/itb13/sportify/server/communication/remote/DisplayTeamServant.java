package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TeamDetailController;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.remote.DisplayTeamRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class DisplayTeamServant extends UnicastRemoteObject implements DisplayTeamRemote {

    private TeamDetailController _teamDetailController;

    public DisplayTeamServant() throws RemoteException {
        super();
        _teamDetailController = new TeamDetailController();
    }

    @Override
    public List<DisplayTeamDTO> getAllTeams() throws RemoteException {
        return _teamDetailController.getAllTeams();
    }
}
