package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.InternalTeamController;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.ExternalDisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import at.fhv.itb13.sportify.shared.communication.enums.RightName;
import at.fhv.itb13.sportify.shared.communication.exceptions.NotAuthorizedException;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.TeamRemote;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class TeamBean implements TeamRemote {

    private SessionRemote _session;
    private InternalTeamController _teamController;

    public TeamBean() {
        _teamController = new InternalTeamController();
    }

    public void setSession(SessionRemote session) {
        _session = session;
    }

    @Override
    public void createTeam(TeamDTO teamDTO) throws NotAuthorizedException {
        _session.authorize(RightName.TEAM_MODIFY);
        _teamController.create(teamDTO);
    }

    @Override
    public void createExternalTeam(ExternalDisplayTeamDTO team) {
        _teamController.createExternalTeam(team);
    }

    @Override
    public void editTeam(TeamDTO teamDTO) throws NotAuthorizedException {
        _session.authorize(RightName.TEAM_MODIFY);
        _teamController.editTeam(teamDTO);
    }

    @Override
    public void addPersonToTeam(PersonDTO personDTO) throws NotAuthorizedException {
        _session.authorize(RightName.TEAM_MODIFY);
        _teamController.addPersonToTeam(personDTO);
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return _teamController.getAllTeams();
    }

    @Override
    public List<ExternalDisplayTeamDTO> getAllExternalTeams() {
        return _teamController.getAllExternalTeams();
    }

    @Override
    public List<DisplayTeamDTO> getAllDisplayTeams() {
        return _teamController.getAllDisplayTeams();
    }
}
