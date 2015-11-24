package at.fhv.itb13.sportify.shared.communication.dtos;


import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Created by KYUSS on 19.11.2015.
 */
public interface TournamentDTO extends Serializable, DTO {

    public String getDescription();

    public void setDescription(String description);

    public String getSportID();

    public void setSportID(String sportID);

    public Set<String> getMatchIDs();

    public void addMatchID(String matchID);

    public void removeMatchID(String matchID);

    public Set<String> getTeamIDs();

    public void addTeamID(String teamID);

    public void removeTeamID(String teamID);

    public void setStartDate (Date date);

    public Date getStartDate();

    public void setLocation(String location);

    public String getLocation();
}
