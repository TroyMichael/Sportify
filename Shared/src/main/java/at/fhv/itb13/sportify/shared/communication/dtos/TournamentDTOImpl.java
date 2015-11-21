package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by KYUSS on 19.11.2015.
 */
public class TournamentDTOImpl extends DTOImpl implements TournamentDTO {


    private String _description;
    private String _sportID;
    private Set<String> _matchIDs = new HashSet<>();

    public TournamentDTOImpl (String description, String sportID, HashSet<String> matchIDs){
        _description = description;
        _sportID = sportID;
        _matchIDs = matchIDs;
    }

    public TournamentDTOImpl (){

    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;
    }

    @Override
    public String getSportID() {
        return _sportID;
    }

    @Override
    public void setSportID(String sportID) {
        _sportID = sportID;
    }

    @Override
    public Set<String> getMatchIDs() {
        return _matchIDs;
    }

    @Override
    public void addMatchID(String matchID) {
        _matchIDs.add(matchID);
    }

    @Override
    public void removeMatchID(String matchID) {
        _matchIDs.remove(matchID);
    }
}
