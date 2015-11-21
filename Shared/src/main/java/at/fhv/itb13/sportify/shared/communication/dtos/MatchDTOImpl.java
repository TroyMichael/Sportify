package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.Tournament;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Caroline on 21.11.2015.
 */
public class MatchDTOImpl extends DTOImpl implements MatchDTO {

    private Integer _duration;
    private Date _start;
    private String _winnerId;
    private String _tournamentId;
    private Set<String> _rosterIds = new HashSet<>();

    @Override
    public int getDuration() {
        return _duration;
    }

    @Override
    public void setDuration(int duration) {
        _duration = duration;
    }

    @Override
    public Date getStart() {
        return _start;
    }

    @Override
    public void setStart(Date start) {
        _start = start;
    }

    @Override
    public String getWinnerId() {
        return _winnerId;
    }

    @Override
    public void setWinnerId(String id) {
        _winnerId = id;
    }

    @Override
    public String getTournamentId() {
        return _tournamentId;
    }

    @Override
    public void setTorunamentId(String id) {
        _tournamentId = id;
    }

    @Override
    public Set<String> getRostersIds() {
        return _rosterIds;
    }

    @Override
    public void setRosterIds(Set<String> ids) {
        _rosterIds = ids;
    }

    @Override
    public void addRosterId(String id) {
        _rosterIds.add(id);
    }

    @Override
    public void removeRosterId(String id) {
        _rosterIds.remove(id);
    }
}
