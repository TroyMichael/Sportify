package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import javax.ejb.Local;

@Local
public interface SessionLocal {
    boolean login(UserDTO userDTO);

    UserDTO getUserDTO();

    void authorize(RightName rightName) throws NotAuthorizedException;
}
