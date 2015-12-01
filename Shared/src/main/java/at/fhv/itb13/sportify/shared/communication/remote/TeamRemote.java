package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.ExternalDisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TeamRemote extends Remote {
    void createTeam(TeamDTO teamDTO) throws RemoteException, NotAuthorizedException;

    void createExternalTeam(ExternalDisplayTeamDTO team) throws RemoteException;

    void editTeam(TeamDTO TeamDTO) throws RemoteException;
    void editTeam(TeamDTO TeamDTO) throws RemoteException, NotAuthorizedException;

    void addPersonToTeam(PersonDTO personDTO) throws RemoteException, NotAuthorizedException;

    List<TeamDTO> getAllTeams() throws RemoteException;

    List<DisplayTeamDTO> getAllDisplayTeams() throws RemoteException;
}
