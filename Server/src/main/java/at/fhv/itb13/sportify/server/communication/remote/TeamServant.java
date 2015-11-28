package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TeamController;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.remote.TeamRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TeamServant extends UnicastRemoteObject implements TeamRemote {

    private TeamController _teamController;

    public TeamServant() throws RemoteException {
        super();
        _teamController = new TeamController();
    }
    
    @Override
    public void createTeam(TeamDTO teamDTO) throws RemoteException {
        _teamController.create(teamDTO);
    }

    @Override
    public void editTeam(TeamDTO teamDTO) throws RemoteException {
        _teamController.editTeam(teamDTO);
    }

    @Override
    public void addPersonToTeam(PersonDTO personDTO) throws RemoteException {
        _teamController.addPersonToTeam(personDTO);
    }

    @Override
    public List<TeamDTO> getAllTeams() throws RemoteException {
        return _teamController.getAllTeams();
    }

    @Override
    public List<DisplayTeamDTO> getAllDisplayTeams() throws RemoteException {
        return _teamController.getAllDisplayTeams();
    }
}
