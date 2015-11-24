package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;
import java.util.HashSet;

/**
 * Created by mod on 11/23/15.
 */
public class SimpleTournamenDTOImpl extends DTOImpl implements SimpleTournamentDTO {
    private String _description;
    private SimpleSportDTO _simpleSportDTO;
    private Date _date;
    private String _location;
    private HashSet<SimpleTeamDTO> _teams = new HashSet<>();

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;
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
    public Date getStartDate() {
        return _date;
    }

    @Override
    public void setStartDate(Date date) {
        _date = date;
    }


    @Override
    public String getLocation() {
        return _location;
    }

    @Override
    public void setLocation(String location) {
        _location = location;
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
