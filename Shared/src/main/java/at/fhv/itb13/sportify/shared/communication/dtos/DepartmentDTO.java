package at.fhv.itb13.sportify.shared.communication.dtos;

import at.fhv.itb13.sportify.server.model.Sport;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Caroline on 10.11.2015.
 */
public interface DepartmentDTO extends Serializable, DTO {

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public Collection<SportDTO> getSports();

    public void addSport(SportDTO sportDTO);

    public void removeSport(SportDTO sportDTO);
}
