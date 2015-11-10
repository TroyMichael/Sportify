package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

public class UserController {

    private UserMapper _userMapper;

    public UserController() {
        _userMapper = new UserMapper();
    }

    public boolean login(UserDTO userDTO) throws DTOIsNullException {
        User userDomain = _userMapper.toDomainObject(userDTO);
        return userDomain.login();
    }
}
