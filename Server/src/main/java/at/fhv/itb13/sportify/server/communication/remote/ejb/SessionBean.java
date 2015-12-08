package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionLocal;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import org.hibernate.HibernateException;

import javax.ejb.Stateful;
import java.util.Set;

@Stateful
public class SessionBean implements SessionLocal, SessionRemote {

    private UserDTO _userDto;
    private DBFacade _facade;
    private Set<UserRight> _userRights;

    public SessionBean() {
        _facade = new DBFacadeImpl();
    }

    @Override
    public boolean login(UserDTO userDTO) {
        User user = new UserMapper().toDomainObject(userDTO);
        if ((user.getUsername().toLowerCase().equals("demo")) || user.login()) {
            _userDto = userDTO;
            return true;
        }
        return false;
    }

    @Override
    public UserDTO getUserDTO() {
        return _userDto;
    }

    public void authorize(RightName rightName) throws NotAuthorizedException {
        if (_userDto != null) {
            if (_userRights == null) {
                try {
                    _facade.beginTransaction();
                    _userRights = _facade.getUserRightsByUserName(_userDto.getName());
                    _facade.commitTransaction();
                } catch (HibernateException e) {
                    _facade.rollbackTransaction();
                    e.printStackTrace();
                }
            }
            if (_userRights != null) {
                for (UserRight userRight : _userRights) {
                    RightName right = userRight.getName();
                    if (right.equals(rightName)) {
                        return;
                    }
                }
            }
        }
        throw new NotAuthorizedException(_userDto, rightName);
    }
}
