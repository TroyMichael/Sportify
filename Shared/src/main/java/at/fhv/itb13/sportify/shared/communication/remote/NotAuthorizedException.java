package at.fhv.itb13.sportify.shared.communication.remote;

import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

public class NotAuthorizedException extends Exception {

    private UserDTO _userDTO;
    private UserRight.RightName _rightName;

    public NotAuthorizedException(UserDTO userDTO, UserRight.RightName rightName) {
        _userDTO = userDTO;
        _rightName = rightName;
    }

    public UserDTO getUserDTO() {
        return _userDTO;
    }

    public UserRight.RightName getRightName() {
        return _rightName;
    }
}
