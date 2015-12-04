package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

import javax.ejb.Remote;

@Remote
public interface SessionFactoryRemote {

    SessionRemote create(UserDTO userDTO);
}
