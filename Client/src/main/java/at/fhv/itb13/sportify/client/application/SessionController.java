package at.fhv.itb13.sportify.client.application;

import at.fhv.itb13.sportify.client.communication.ServiceLocator;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.Session;
import at.fhv.itb13.sportify.shared.communication.remote.SessionFactory;

import javax.security.auth.login.FailedLoginException;
import java.rmi.RemoteException;

public class SessionController {

    private static SessionController _instance;

    private Session _session;

    private SessionController() {
    }

    public static SessionController getInstance() {
        if (_instance == null) {
            _instance = new SessionController();
        }
        return _instance;
    }

    public Session getSession() {
        return _session;
    }

    public void login(UserDTO userDTO) throws RemoteException, FailedLoginException {
        _session = ServiceLocator.getInstance().getRemote(SessionFactory.class).create(userDTO);
        if (_session == null) {
            throw new FailedLoginException();
        }
    }

    public void logout() {
        _session = null;
    }
}