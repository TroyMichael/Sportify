package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;
import java.util.Set;

/**
 * Created by KYUSS on 26.11.2015.
 *
 */
public class SimpleTournamentDTOImpl extends DTOImpl implements SimpleTournamentDTO{

    private String _description;
    private String _location;
    private Date _startDate;
    private String _sport;
    private Set<SimpleTeamDTO> _teams;
    private Set<MatchDTO> _matches;

    public SimpleTournamentDTOImpl (String description, String location, Date startDate, String sport){
        _description = description;
        _startDate = startDate;
        _location = location;
        _sport = sport;
    }

   public SimpleTournamentDTOImpl(){}


    @Override
    public String getLocation() {
        return _location;
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public String getSport() {
        return _sport;
    }

    @Override
    public Set<SimpleTeamDTO> getTeams() {
        return _teams;
    }

    @Override
    public Set<MatchDTO> getMatches() {
        return _matches;
    }

    @Override
    public void setLocation(String location) {
        _location = location;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    @Override
    public void setDescription(String description) {
        _description = description;
    }

    @Override
    public void setTeams(Set<SimpleTeamDTO> simpleTeams) {
        _teams = simpleTeams;
    }

    @Override
    public void setSport(String sport) {
        _sport = sport;
    }

    @Override
    public void setMatches(Set<MatchDTO> matches) {
        _matches = matches;
    }
}
