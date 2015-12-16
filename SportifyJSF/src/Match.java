import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTOImpl;

import java.sql.Date;

/**
 * Created by mod on 12/15/15.
 */
public class Match {
    private String id;
    private int version;
    private Integer _duration;
    private Date _start;
    private String _tournamentId;
    private String _matchStatus;
    private MatchDTOImpl.SimpleMatchTeamDTO _team1;
    private MatchDTOImpl.SimpleMatchTeamDTO _team2;
    private String score1;
    private String score2;
    boolean editable;

    public boolean getEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    public Match(){}


    public Integer get_duration() {
        return _duration;
    }

    public void set_duration(Integer _duration) {
        this._duration = _duration;
    }

    public Date get_start() {
        return _start;
    }

    public void set_start(Date _start) {
        this._start = _start;
    }

    public String get_tournamentId() {
        return _tournamentId;
    }

    public void set_tournamentId(String _tournamentId) {
        this._tournamentId = _tournamentId;
    }

    public String get_matchStatus() {
        return _matchStatus;
    }

    public void set_matchStatus(String _matchStatus) {
        this._matchStatus = _matchStatus;
    }

    public MatchDTOImpl.SimpleMatchTeamDTO get_team1() {
        return _team1;
    }

    public void set_team1(MatchDTOImpl.SimpleMatchTeamDTO _team1) {
        this._team1 = _team1;
    }

    public MatchDTOImpl.SimpleMatchTeamDTO get_team2() {
        return _team2;
    }

    public void set_team2(MatchDTOImpl.SimpleMatchTeamDTO _team2) {
        this._team2 = _team2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
