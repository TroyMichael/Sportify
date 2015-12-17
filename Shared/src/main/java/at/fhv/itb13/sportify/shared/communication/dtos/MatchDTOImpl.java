package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;

public class MatchDTOImpl extends DTOImpl implements MatchDTO {

    private Integer _duration;
    private Date _start;
    private String _tournamentId;
    private String _matchStatus;
    private SimpleMatchTeamDTO _team1;
    private SimpleMatchTeamDTO _team2;


    public MatchDTOImpl() {
    }

    public MatchDTOImpl(Integer duration, Date start, String tournamentId, String matchStatus) {
        _duration = duration;
        _start = start;
        _tournamentId = tournamentId;
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
    public void setTournamentId(String id) {
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
    public SimpleMatchTeamDTO getTeam1() {
        return _team1;
    }

    @Override
    public void setTeam1(SimpleMatchTeamDTO team1) {
        _team1 = team1;
    }

    @Override
    public SimpleMatchTeamDTO getTeam2() {
        return _team2;
    }

    @Override
    public void setTeam2(SimpleMatchTeamDTO team2) {
        _team2 = team2;
    }

    @Override
    public String getPoints1() {

        if(_team1 != null) {
            return _team1.getPoints();
        }
        return "";
    }

    @Override
    public String getPoints2() {
        if(_team2 != null){
            return _team2.getPoints();
        }
        return  "";
    }



    public static class SimpleMatchTeamDTO extends DTOImpl{
        private String _name;
        private String _points;
        private String _teamID;

        public SimpleMatchTeamDTO(String teamID) {
            _teamID = teamID;
        }

        public String getName() {
            return _name;
        }

        public void setName(String name) {
            _name = name;
        }

        public String getPoints(){
            return _points;

        }

        public void setPoints(String points){
            _points = points;
        }

        @Override
        public String toString() {
            return _name;
        }

        public void setTeamID (String teamID){
            _teamID = teamID;
        }

        public String getTeamID (){
            return _teamID;
        }
    }
}
