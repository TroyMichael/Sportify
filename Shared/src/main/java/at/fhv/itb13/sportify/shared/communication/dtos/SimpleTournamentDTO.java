package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by mod on 11/23/15.
 */
public interface SimpleTournamentDTO {
    String getName();
    void setDescription(String description);
    SimpleSportDTO getSport();
    void setSport(SimpleSportDTO sportID);
    Date getStartDate();
    void setStartDate(Date date);
    String getLocation();
    void setLocation(String location);
    HashSet<SimpleTeamDTO> teamNames();
    void addSimpleTeam(SimpleTeamDTO simpleTeamDTO);
    void removeSimpleTeam(SimpleTeamDTO simpleTeamDTO);
}
