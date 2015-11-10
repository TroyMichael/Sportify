package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;

import java.rmi.Remote;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamRemote extends Remote {
    void createTeam(TeamDTO teamDTO);
}
