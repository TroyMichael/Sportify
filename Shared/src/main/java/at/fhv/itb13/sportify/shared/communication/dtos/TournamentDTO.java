package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Created by KYUSS on 19.11.2015.
 *
 */
public interface TournamentDTO extends Serializable, DTO {

    String getDescription();

    void setDescription(String description);

    String getSportID();

    void setSportID(String sportID);

    Set<String> getMatchIDs();

    void addMatchID(String matchID);

    void removeMatchID(String matchID);

    Set<String> getTeamIDs();

    void addTeamID(String teamID);

    void removeTeamID(String teamID);

    void setStartDate (Date date);

    Date getStartDate();

    void setLocation(String location);

    String getLocation();
}
