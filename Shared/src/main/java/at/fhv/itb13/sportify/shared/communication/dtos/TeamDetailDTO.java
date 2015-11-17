package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Michael on 16.11.2015.
 */
public interface TeamDetailDTO extends Serializable, DTO {

    String getName();
    void setName(String name);

    HashSet<SimplePersonDTO> getMembers();
    void addMember(SimplePersonDTO personDTO);

    SimplePersonDTO getTrainer();
    void setTrainer(SimplePersonDTO trainer);

    SimpleSportDTO getSport();
    void setSport(SimpleSportDTO sport);



}
