package at.fhv.itb13.sportify.shared.communication.dtos;


import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamDTO extends Serializable, DTO {
    String getName();
    void setName(String name);

    String getTrainerId();
    void setTrainerId(String trainerId);

    HashSet<String> getPersonIds();

    void addPersonId(String personId);
    void removePersonId(String personId);

    String getSportId();
    void setSportId(String sportId);

    HashSet<String> getRosterIds();

    void addRosterId(String rosterId);
    void removeRosterId(String rosterId);
}
