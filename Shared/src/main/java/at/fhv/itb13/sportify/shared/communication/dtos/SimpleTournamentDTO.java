package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;
import java.util.HashSet;

/**
 * Created by mod on 11/23/15.
 */
public interface SimpleTournamentDTO {
    String getName();
    void setName(String name);
    String getSport();
    void setSport(String sportID);
    Date getDate();
    void setDate(Date date);
    String getLocation();
    void setLocation(String location);
    HashSet<SimpleTeamDTO> teamNames();
    void addSimpleTeam(SimpleTeamDTO simpleTeamDTO);
    void removeSimpleTeam(SimpleTeamDTO simpleTeamDTO);
}
