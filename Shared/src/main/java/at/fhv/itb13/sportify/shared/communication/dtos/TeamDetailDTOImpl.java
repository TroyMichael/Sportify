package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.HashSet;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailDTOImpl extends DTOImpl implements TeamDetailDTO {

    private String _name;
    private HashSet<SimplePersonDTO> _members = new HashSet<>();
    private SimplePersonDTO _trainer;
    private SimpleSportDTO _sport;

    public TeamDetailDTOImpl() {

    }

    public TeamDetailDTOImpl(String name, HashSet<SimplePersonDTO> members, SimplePersonDTO trainer, SimpleSportDTO sport) {
        _name = name;
        _members = members;
        _trainer = trainer;
        _sport = sport;
    }


    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public HashSet<SimplePersonDTO> getMembers() {
        return _members;
    }

    @Override
    public void addMember(SimplePersonDTO personDTO) {
        _members.add(personDTO);
    }

    @Override
    public SimplePersonDTO getTrainer() {
        return _trainer;
    }

    @Override
    public void setTrainer(SimplePersonDTO trainer) {
        _trainer = trainer;
    }

    @Override
    public SimpleSportDTO getSport() {
        return _sport;
    }

    @Override
    public void setSport(SimpleSportDTO sport) {
        _sport = sport;
    }
}
