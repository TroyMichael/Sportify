package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;

import javax.ejb.Remote;

@Remote
public interface SessionRemote {

    boolean login(UserDTO userDTO);

    UserDTO getUserDTO();

    PersonRemote getPersonRemote();

    TeamRemote getTeamRemote();

    SportRemote getSportRemote();

    TournamentRemote getTournamentRemote();

    MatchRemote getMatchRemote();

    MessageRemote getMessageRemote();
}
