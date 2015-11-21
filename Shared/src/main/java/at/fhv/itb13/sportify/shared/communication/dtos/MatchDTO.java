package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Caroline on 21.11.2015.
 */
public interface MatchDTO extends Serializable, DTO {

    Integer getDuration();

    void setDuration(Integer duration);

    Date getStart();

    void setStart(Date start);

    String getTournamentId();

    void setTorunamentId(String id);

    String getMatchStatus();

    void setMatchStatus(String matchStatus);

    Set<String> getMatchTeamIds();

    void setMatchTeams(Set<String> ids);

    void addMatchTeamId(String id);

    void removeMatchTeamId(String id);
}
