package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.util.Date;

public interface MatchDTO extends Serializable, DTO {

    Integer getDuration();

    void setDuration(Integer duration);

    Date getStart();

    void setStart(Date start);

    String getTournamentId();

    void setTournamentId(String id);

    String getMatchStatus();

    void setMatchStatus(String matchStatus);

    MatchDTOImpl.SimpleMatchTeamDTO getTeam1();

    void setTeam1(MatchDTOImpl.SimpleMatchTeamDTO team1);

    MatchDTOImpl.SimpleMatchTeamDTO getTeam2();

    void setTeam2(MatchDTOImpl.SimpleMatchTeamDTO team2);

    String getPoints1();

    String getPoints2();


}
