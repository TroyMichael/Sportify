package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamRemote extends Remote {
    void createTeam(TeamDTO teamDTO);
    void editTeam(TeamDTO TeamDTO);
    void addPersonToTeam(PersonDTO personDTO);
}
