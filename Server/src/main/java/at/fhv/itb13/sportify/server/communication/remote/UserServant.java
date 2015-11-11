package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.UserController;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.UserRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by mod on 11/5/15.
 */
//TODO : Uncomment it all
public class UserServant extends UnicastRemoteObject implements UserRemote {

    private UserController _userController;

    public UserServant() throws RemoteException {
        super();
        _userController = new UserController();
    }
    
    @Override
    public boolean login(UserDTO userDTO) throws RemoteException {
        try {
            return _userController.login(userDTO);
        } catch (DTOIsNullException e) {
            e.printStackTrace();
            return false;
        }
    }
}
