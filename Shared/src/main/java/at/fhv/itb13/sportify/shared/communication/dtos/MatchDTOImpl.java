package at.fhv.itb13.sportify.shared.communication.dtos;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Caroline on 21.11.2015.
 */
public class MatchDTOImpl extends DTOImpl implements MatchDTO {

    private Integer _duration;
    private Date _start;
    private String _tournamentId;
    private String _matchStatus;
    private Set<String> _matchTeamIds = new HashSet<>();

    public MatchDTOImpl(){}
    public MatchDTOImpl(Integer duration, Date start,String matchStatus){
        _duration = duration;
        _start = start;
        _matchStatus = matchStatus;
    }


    @Override
    public Integer getDuration() {
        return _duration;
    }

    @Override
    public void setDuration(Integer duration) {
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
    public String getTournamentId() {
        return _tournamentId;
    }

    @Override
    public void setTorunamentId(String id) {
        _tournamentId = id;
    }

    @Override
    public String getMatchStatus() {
        return _matchStatus;
    }

    @Override
    public void setMatchStatus(String matchStatus) {
        _matchStatus = matchStatus;
    }

    @Override
    public Set<String> getMatchTeamIds() {
        return _matchTeamIds;
    }

    @Override
    public void setMatchTeams(Set<String> ids) {
        _matchTeamIds = ids;
    }

    @Override
    public void addMatchTeamId(String id) {
        _matchTeamIds.add(id);
    }

    @Override
    public void removeMatchTeamId(String id) {
        _matchTeamIds.remove(id);
    }


}
