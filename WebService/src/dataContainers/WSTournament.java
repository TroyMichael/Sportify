package dataContainers;

import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 14.12.2015.
 *
 */
public class WSTournament {

    private String _tournamentName = "";
    private List<WSMatch> _matches = new LinkedList<>();

    public WSTournament() {

    }

    public WSTournament(TournamentDTO tournament) {
        _tournamentName = tournament.getDescription();

        if (tournament.getMatches() != null && tournament.getMatches().size() > 0) {
            for (MatchDTO match : tournament.getMatches()) {
                _matches.add(new WSMatch(match));
            }
        }
    }

    public String getTournamentName() {
        return _tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        _tournamentName = tournamentName;
    }

    public List<WSMatch> getMatches() {
        return _matches;
    }

    public void setMatches(List<WSMatch> matches) {
        _matches = matches;
    }
}
