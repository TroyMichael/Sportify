package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

/**
 * Created by Caroline on 05.11.2015.
 */
public class UserController {

    private UserMapper _userMapper;

    public UserController(UserMapper userMapper){
        _userMapper = userMapper;
    }

    public boolean login(UserDTO userDTO) throws DTOIsNullException {
        User userDomain = _userMapper.toDomainObject(userDTO);
        return userDomain.login();
    }
}
