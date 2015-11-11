package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;

public class UserMapper extends Mapper<UserDTO, User> {

    private static UserMapper _instance;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (_instance == null) {
            _instance = new UserMapper();
        }
        return _instance;
    }

    @Override
    public User toDomainObject(UserDTO userDTO) {
        if (userDTO != null) {
            return new User(userDTO.getName(), userDTO.getPassword());
        }
        return null;
    }

    @Override
    public UserDTO toDTOObject(User userDomain) {
        if (userDomain != null) {
            UserDTO userDTO = new UserDTOImpl();
            userDTO.setName(userDomain.getUsername());
            userDTO.setPassword(userDomain.getPassword());
            return userDTO;
        }
        return null;
    }
}
