package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Caroline on 10.11.2015.
 */
public class SessionImpl extends UnicastRemoteObject implements Session{

  private User _user;

    private SessionImpl(User user) throws RemoteException {
        super();
        _user = user;
    }

    public static SessionImpl create(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        if(user.login()){
            try {
                return new SessionImpl(user);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public PersonRemote getPersonRemote() throws RemoteException {
        return new PersonServant();
    }


    public UserRemote getUserRemote() throws RemoteException {
        return new UserServant();
    }

    public TeamRemote getTeamRemote() throws RemoteException{
        return new TeamServant();
    }

    public SportRemote getSportRemote() throws RemoteException{
        return new SportServant();
    }
}
