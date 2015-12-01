package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.Session;
import org.hibernate.HibernateException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class SessionServant extends UnicastRemoteObject {

    private Session _session;
    private DBFacade _facade;
    private Set<UserRight> _userRights;

    protected SessionServant(Session session) throws RemoteException {
        _session = session;
        _facade = new DBFacadeImpl();
    }

    protected SessionServant(Session session, DBFacade facade) throws RemoteException {
        _session = session;
        _facade = facade;
    }

    public void authorize(UserRight.RightName rightName) throws RemoteException, NotAuthorizedException {
        UserDTO userDTO = _session.getUserDTO();
        if (userDTO != null) {
            if (_userRights == null) {
                try {
                    _facade.beginTransaction();
                    _userRights = _facade.getUserRightsByUserName(userDTO.getName());
                    _facade.commitTransaction();
                } catch (HibernateException e) {
                    _facade.rollbackTransaction();
                    e.printStackTrace();
                }
            }
            if (_userRights != null) {
                for (UserRight userRight : _userRights) {
                    UserRight.RightName right = userRight.getName();
                    if (right.equals(rightName)) {
                        return;
                    }
                }
            }
        }
        throw new NotAuthorizedException(userDTO, rightName);
    }
}
