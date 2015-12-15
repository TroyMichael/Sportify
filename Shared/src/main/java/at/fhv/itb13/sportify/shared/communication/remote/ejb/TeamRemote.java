package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.ExternalDisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;

import javax.ejb.Remote;
import java.util.List;
@Remote
public interface TeamRemote {

    void setSession(SessionRemote session);

    void createTeam(TeamDTO teamDTO) throws NotAuthorizedException;

    void createExternalTeam(ExternalDisplayTeamDTO team);

    void editTeam(TeamDTO TeamDTO) throws NotAuthorizedException;

    void addPersonToTeam(PersonDTO personDTO) throws NotAuthorizedException;

    List<TeamDTO> getAllTeams();

    List<ExternalDisplayTeamDTO> getAllExternalTeams();

    List<DisplayTeamDTO> getAllDisplayTeams();
}
