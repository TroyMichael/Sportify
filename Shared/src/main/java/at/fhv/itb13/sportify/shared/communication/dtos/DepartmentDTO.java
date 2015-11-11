package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.Collection;

/**
 * Created by mod on 11/11/15.
 */
public interface DepartmentDTO {
    public String getName();
    public void setName(String name);
    public String getDescription();
    public void setDescription(String description);
    public Collection<SportDTO> getSports();
    public void addSport(SportDTO sport);
    public void removeSport(SportDTO sport);
}
