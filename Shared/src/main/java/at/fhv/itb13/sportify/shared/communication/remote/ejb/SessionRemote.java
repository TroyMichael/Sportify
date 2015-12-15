package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface SessionRemote {

    boolean login(UserDTO userDTO);

    UserDTO getUserDTO();

    void authorize(RightName rightName) throws NotAuthorizedException;

    MatchRemote getMatchRemote();

    MessageRemote getMessageRemote();

    PersonRemote getPersonRemote();

    SportRemote getSportRemote();

    TeamRemote getTeamRemote();

    TournamentRemote getTournamentRemote();
}
