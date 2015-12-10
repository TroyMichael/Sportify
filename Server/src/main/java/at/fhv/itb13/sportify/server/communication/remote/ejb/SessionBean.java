package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.UserMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.User;
import at.fhv.itb13.sportify.server.model.UserRight;
import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.*;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.Set;

@Stateful
public class SessionBean implements SessionRemote {

    private UserDTO _userDto;
    private DBFacade _facade;
    private Set<UserRight> _userRights;

    @EJB
    private MatchRemote _matchRemote;
    @EJB
    private MessageRemote _messageRemote;
    @EJB
    private PersonRemote _personRemote;
    @EJB
    private SportRemote _sportRemote;
    @EJB
    private TeamRemote _teamRemote;
    @EJB
    private TournamentRemote _tournamentRemote;

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

    @Override
    public MatchRemote getMatchRemote() {
        _matchRemote.setSession(this);
        return _matchRemote;
    }

    @Override
    public MessageRemote getMessageRemote() {
        _messageRemote.setSession(this);
        return _messageRemote;
    }

    @Override
    public PersonRemote getPersonRemote() {
        _personRemote.setSession(this);
        return _personRemote;
    }

    @Override
    public SportRemote getSportRemote() {
        _sportRemote.setSession(this);
        return _sportRemote;
    }

    @Override
    public TeamRemote getTeamRemote() {
        _teamRemote.setSession(this);
        return _teamRemote;
    }

    @Override
    public TournamentRemote getTournamentRemote() {
        _tournamentRemote.setSession(this);
        return _tournamentRemote;
    }
}
