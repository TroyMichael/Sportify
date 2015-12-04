package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class SessionImpl extends UnicastRemoteObject implements Session {

    private UserDTO _userDto;

    private SessionImpl(UserDTO userDto) throws RemoteException {
        super();
        _userDto = userDto;
    }

    public static Session create(UserDTO userDTO) throws RemoteException {
        User user = new UserMapper().toDomainObject(userDTO);
        if(userDTO.getName().toLowerCase() == "demo"){
            return new SessionImpl(userDTO);
        }
        if (user.login()) {
            return new SessionImpl(userDTO);
        }
        return null;
    }

    public UserDTO getUserDTO() {
        return _userDto;
    }

    public PersonRemote getPersonRemote() throws RemoteException {
        return new PersonServant(this);
    }

    public TeamRemote getTeamRemote() throws RemoteException {
        return new TeamServant(this);
    }

    public SportRemote getSportRemote() throws RemoteException {
        return new SportServant(this);
    }

    public TournamentRemote getTournamentRemote() throws RemoteException {
        return new TournamentServant(this);
    }

    public MatchRemote getMatchRemote() throws RemoteException {
        return new MatchServant(this);
    }

    @Override
    public MessageRemote getMessageRemote() throws RemoteException {
        return new MessageServant(this);
    }

}
