package at.fhv.itb13.sportify.shared.communication.dtos;


import java.util.HashSet;
import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public interface TeamDTO {
    String getName();
    void setName(String name);

    HashSet<String> getPersonIds();

    void addPersonId(String personId);
    void removePersonId(String personId);

    String getSportId();
    void setSportId(String sportId);
}
