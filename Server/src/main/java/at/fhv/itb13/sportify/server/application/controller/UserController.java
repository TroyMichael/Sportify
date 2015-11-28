package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

public class UserController {

    public boolean login(UserDTO userDTO) {
        User userDomain = new UserMapper().toDomainObject(userDTO);
        return userDomain.login();
    }
}
