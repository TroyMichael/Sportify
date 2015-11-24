package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;
import java.util.HashSet;

/**
 * Created by mod on 11/23/15.
 */
public class SimpleTournamenDTOImpl extends DTOImpl implements SimpleTournamentDTO {
    private String _name;
    private SimpleSportDTO _simpleSportDTO;
    private Date _date;
    private String _location;
    private HashSet<SimpleTeamDTO> _teams  = new HashSet<>();
    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public SimpleSportDTO getSport() {
        return _simpleSportDTO;
    }

    @Override
    public void setSport(SimpleSportDTO sport) {
        _simpleSportDTO = sport;
    }

    @Override
    public Date getDate() {
        return _date;
    }

    @Override
    public void setDate(Date date) {
        _date = date;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public HashSet<SimpleTeamDTO> teamNames() {
        return _teams;
    }

    @Override
    public void addSimpleTeam(SimpleTeamDTO simpleTeamDTO) {
        _teams.add(simpleTeamDTO);
    }

    @Override
    public void removeSimpleTeam(SimpleTeamDTO simpleTeamDTO) {
        _teams.remove(simpleTeamDTO);
    }
}
