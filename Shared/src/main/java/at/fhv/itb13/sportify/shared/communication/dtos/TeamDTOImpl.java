package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.HashSet;

/**
 * Created by mod on 11/10/15.
 *
 */
public class TeamDTOImpl extends DTOImpl implements TeamDTO {
    private String _name;
    private HashSet<String> _personIds = new HashSet<>();
    private HashSet<String> _rosterIds = new HashSet<>();
    private String _sportId;
    private String _trainerId;

    public TeamDTOImpl(){}

    public TeamDTOImpl(String name, String trainerId, HashSet<String> personIds, String sportId) {
        _name = name;
        _personIds = personIds;
        _sportId = sportId;
        _trainerId = trainerId;
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
    public String getTrainerId() {
        return _trainerId;
    }

    @Override
    public void setTrainerId(String trainerId) {
        _trainerId = trainerId;
    }


    @Override
    public HashSet<String> getPersonIds() {
        return _personIds;
    }

    @Override
    public void addPersonId(String personId) {
        _personIds.add(personId);
    }

    @Override
    public void removePersonId(String personId) {
        _personIds.remove(personId);
    }

    @Override
    public String getSportId() {
        return _sportId;
    }

    @Override
    public void setSportId(String sportId) {
        _sportId = sportId;
    }

    @Override
    public HashSet<String> getRosterIds() {
        return _rosterIds;
    }

    @Override
    public void addRosterId(String rosterId) {
        _rosterIds.add(rosterId);
    }

    @Override
    public void removeRosterId(String rosterId) {
        _rosterIds.remove(rosterId);
    }
}
