package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TeamDetailController;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import at.fhv.itb13.sportify.shared.communication.remote.TeamDetailRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailServant extends UnicastRemoteObject implements TeamDetailRemote {

    private TeamDetailController _teamDetailController;

    public TeamDetailServant() throws RemoteException {
        super();
        _teamDetailController = new TeamDetailController();
    }

    @Override
    public List<TeamDetailDTO> getAllTeams() throws RemoteException {
        return _teamDetailController.getAllTeams();
    }
}
