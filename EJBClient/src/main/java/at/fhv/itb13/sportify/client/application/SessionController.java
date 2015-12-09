package at.fhv.itb13.sportify.client.application;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;

import javax.security.auth.login.FailedLoginException;
import java.rmi.RemoteException;

public class SessionController {

    private static SessionController _instance;

    private SessionRemote _session;

    private SessionController() {
    }

    public static SessionController getInstance() {
        if (_instance == null) {
            _instance = new SessionController();
        }
        return _instance;
    }

    public SessionRemote getSession() {
        return _session;
    }

    public void login(UserDTO userDTO) throws RemoteException, FailedLoginException {
        SessionRemote session = ServiceLocator.getInstance().getRemote(SessionRemote.class);
        if (!session.login(userDTO)) {
            throw new FailedLoginException();
        }
        _session = session;
    }

    public void logout() {
        _session = null;
    }
}