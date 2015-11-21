package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Caroline on 21.11.2015.
 */
public interface MatchDTO extends Serializable, DTO {

    int getDuration();

    void setDuration(int duration);

    Date getStart();

    void setStart(Date start);

    String getWinnerId();

    void setWinnerId(String id);

    String getTournamentId();

    void setTorunamentId(String id);

    Set<String> getRostersIds();

    void setRosterIds(Set<String> ids);

    void addRosterId(String id);

    void removeRosterId(String id);
}
