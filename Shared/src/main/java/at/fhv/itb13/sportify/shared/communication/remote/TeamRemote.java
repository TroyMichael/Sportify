package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TeamRemote extends Remote {
    void createTeam(TeamDTO teamDTO) throws RemoteException;

    void editTeam(TeamDTO TeamDTO) throws RemoteException;

    void addPersonToTeam(PersonDTO personDTO) throws RemoteException;

    List<TeamDTO> getAllTeams() throws RemoteException;

    List<DisplayTeamDTO> getAllDisplayTeams() throws RemoteException;
}
