package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SessionImpl extends UnicastRemoteObject implements Session {

    private UserDTO _userDto;

    private SessionImpl(UserDTO userDto) throws RemoteException {
        super();
        _userDto = userDto;
    }

    public static Session create(UserDTO userDTO) throws RemoteException {
        User user = new UserMapper().toDomainObject(userDTO);
        if (user.login()) {
            return new SessionImpl(userDTO);
        }
        return null;
    }

    public UserDTO getUserDto() {
        return _userDto;
    }

    public PersonRemote getPersonRemote() throws RemoteException {
        return new PersonServant();
    }

    public UserRemote getUserRemote() throws RemoteException {
        return new UserServant();
    }

    public TeamRemote getTeamRemote() throws RemoteException {
        return new TeamServant();
    }

    public SportRemote getSportRemote() throws RemoteException {
        return new SportServant();
    }

    public TournamentRemote getTournamentRemote() throws RemoteException {
        return new TournamentServant();
    }

    public MatchRemote getMatchRemote() throws RemoteException {
        return new MatchServant();
    }
}
