package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by KYUSS on 26.11.2015.
 *
 */
public interface SimpleTournamentDTO extends DTO, Serializable{

    String getLocation ();
    String getDescription ();
    Date getStartDate();
    String getSport();
    List<SimpleTeamDTO> getTeams();

    void setLocation (String location);
    void setStartDate (Date startDate);
    void setDescription (String description);
    void setTeams(List<SimpleTeamDTO> simpleTeams);
    void setSport(String sport);
}
