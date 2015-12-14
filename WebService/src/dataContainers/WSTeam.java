package dataContainers;

import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;

/**
 * Created by Michael on 14.12.2015.
 *
 */
public class WSTeam {

    private String _teamName = "";
    private String _score = "";

    public WSTeam(MatchDTOImpl.SimpleMatchTeamDTO team) {
        _teamName = team.getName();
        _score = team.getPoints();
    }

    public String getTeamName() {
        return _teamName;
    }

    public void setTeamName(String teamName) {
        _teamName = teamName;
    }

    public String getScore() {
        return _score;
    }

    public void setScore(String score) {
        _score = score;
    }
}
