package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.HashSet;
import java.util.List;

/**
 * Created by mod on 11/10/15.
 */
public class TeamDTOImpl implements TeamDTO {
    private String _name;
    private HashSet<String> _personIds = new HashSet<>();
    private HashSet<String> _rosterIds = new HashSet<>();
    private String _sportId;
    public TeamDTOImpl(){}
    public TeamDTOImpl(String name,  HashSet<String> personIds, String sportId) {
        _name = name;
        _personIds = personIds;
        _sportId = sportId;
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
