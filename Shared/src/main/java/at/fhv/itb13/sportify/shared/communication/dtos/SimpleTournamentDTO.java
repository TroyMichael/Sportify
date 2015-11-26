package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by KYUSS on 26.11.2015.
 */
public interface SimpleTournamentDTO extends DTO, Serializable{

    String getLocation ();
    String getDescription ();
    Date getStartDate();

    void setLocation (String location);
    void setStartDate (Date startDate);
    void setDescription (String description);
}
