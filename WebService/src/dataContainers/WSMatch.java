package dataContainers;

import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;

/**
 * Created by Michael on 14.12.2015.
 *
 */
public class WSMatch {

    private WSTeam _team1;
    private WSTeam _team2;

    public WSMatch() {

    }

    public WSMatch(MatchDTO match) {
        _team1 = new WSTeam(match.getTeam1());
        _team2 = new WSTeam(match.getTeam2());
    }

    public WSTeam getTeam1() {
        return _team1;
    }

    public void setTeam1(WSTeam team1) {
        _team1 = team1;
    }

    public WSTeam getTeam2() {
        return _team2;
    }

    public void setTeam2(WSTeam team2) {
        _team2 = team2;
    }
}
