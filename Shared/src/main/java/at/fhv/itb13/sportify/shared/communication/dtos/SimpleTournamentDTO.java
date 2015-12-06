package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Created by KYUSS on 26.11.2015.
 *
 */
public interface SimpleTournamentDTO extends DTO, Serializable{

    String getLocation ();
    String getDescription ();
    Date getStartDate();
    String getSport();
    Set<SimpleTeamDTO> getTeams();
    Set<MatchDTO> getMatches();

    void setLocation (String location);
    void setStartDate (Date startDate);
    void setDescription (String description);
    void setTeams(Set<SimpleTeamDTO> simpleTeams);
    void setSport(String sport);
    void setMatches(Set<MatchDTO> matches);
}
