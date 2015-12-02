package at.fhv.itb13.sportify.shared.communication.exceptions;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;

public class NotAuthorizedException extends Exception {

    private UserDTO _userDTO;
    private RightName _rightName;

    public NotAuthorizedException(UserDTO userDTO, RightName rightName) {
        _userDTO = userDTO;
        _rightName = rightName;
    }

    public UserDTO getUserDTO() {
        return _userDTO;
    }

    public RightName getRightName() {
        return _rightName;
    }
}
