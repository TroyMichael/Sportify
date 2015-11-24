package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by KYUSS on 19.11.2015.
 *
 */
public class TournamentDTOImpl extends DTOImpl implements TournamentDTO {

    private String _description;
    private String _sportID;
    private Set<String> _matchIDs = new HashSet<>();
    private Set<String> _teamIDs = new HashSet<>();
    private Date _date;
    private String _location;

    public TournamentDTOImpl (String description, String sportID, HashSet<String> matchIDs, Date date, String location){
        _description = description;
        _sportID = sportID;
        _matchIDs = matchIDs;
        _date = date;
        _location = location;
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

    @Override
    public Set<String> getTeamIDs() {
        return _teamIDs;
    }

    @Override
    public void addTeamID(String teamID) {
        _teamIDs.add(teamID);
    }

    @Override
    public void removeTeamID(String teamID) {
        _teamIDs.remove(teamID);
    }

    @Override
    public void setStartDate(Date date) {
        _date = date;
    }

    @Override
    public Date getStartDate() {
        return _date;
    }

    @Override
    public void setLocation(String location) {
        _location = location;
    }

    @Override
    public String getLocation() {
        return _location;
    }

}
