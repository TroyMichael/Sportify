package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.server.communication.remote.SessionImpl;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

import java.rmi.Remote;

/**
 * Created by Caroline on 10.11.2015.
 */
public interface SessionFactory extends Remote {

    SessionImpl create(UserDTO userDTO);
}
