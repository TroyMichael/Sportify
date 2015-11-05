package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTOImpl;

/**
 * Created by Caroline on 05.11.2015.
 */
public class UserMapper extends Mapper<UserDTO, User> {

    @Override
    public User toDomainObject(UserDTO userDTO) throws DTOIsNullException {
    if(userDTO != null){
        return new User(userDTO.getName(), userDTO.getPassword());

    } else {
        throw new DTOIsNullException();
    }
    }

    @Override
    public UserDTO toDTOObject(User domainObject) throws DomainObjectIsNullException {
    if(domainObject != null){
        UserDTO userDTO = new UserDTOImpl();
        userDTO.setName(domainObject.getUsername());
        userDTO.setPassword(domainObject.getPassword());

        return userDTO;
    }else{
        throw new DomainObjectIsNullException();
    }




    }
}
