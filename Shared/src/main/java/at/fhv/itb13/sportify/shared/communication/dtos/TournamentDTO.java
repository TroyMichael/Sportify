package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;

import java.io.Serializable;
import java.util.HashSet;
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
}
