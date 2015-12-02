package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.TeamController;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.ExternalDisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.Session;
import at.fhv.itb13.sportify.shared.communication.remote.TeamRemote;

import java.rmi.RemoteException;
import java.util.List;

public class TeamServant extends SessionServant implements TeamRemote {

    private TeamController _teamController;

    public TeamServant(Session session) throws RemoteException {
        super(session);
        _teamController = new TeamController();
    }

    @Override
    public void createTeam(TeamDTO teamDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TEAM_MODIFY);
        _teamController.create(teamDTO);
    }

    @Override
    public void createExternalTeam(ExternalDisplayTeamDTO team) throws RemoteException {
        _teamController.createExternalTeam(team);
    }

    @Override
    public void editTeam(TeamDTO teamDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TEAM_MODIFY);
        _teamController.editTeam(teamDTO);
    }

    @Override
    public void addPersonToTeam(PersonDTO personDTO) throws RemoteException, NotAuthorizedException {
        authorize(RightName.TEAM_MODIFY);
        _teamController.addPersonToTeam(personDTO);
    }

    @Override
    public List<TeamDTO> getAllTeams() throws RemoteException {
        return _teamController.getAllTeams();
    }

    @Override
    public List<ExternalDisplayTeamDTO> getAllExternalTeams() throws RemoteException {
        return _teamController.getAllExternalTeams();
    }

    @Override
    public List<DisplayTeamDTO> getAllDisplayTeams() throws RemoteException {
        return _teamController.getAllDisplayTeams();
    }
}
