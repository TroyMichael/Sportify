package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.HashSet;

/**
 * Created by mod on 11/11/15.
 */
public interface DepartmentDTO {
    public String getName();
    public void setName(String name);
    public String getDescription();
    public void setDescription(String description);
    public HashSet<String> getSportIds();
    public void addSport(String sportID);
    public void removeSport(String sportID);
}
