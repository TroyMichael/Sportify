package at.fhv.itb13.sportify.shared.communication.dtos;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by KYUSS on 23.11.2015.
 */
public class SimpleTournamentDTOImpl implements SimpleTournamentDTO {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setDescription (String description) {

    }

    @Override
    public SimpleSportDTO getSport() {
        return null;
    }

    @Override
    public void setSport(SimpleSportDTO sportID) {

    }

    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public void setStartDate(Date date) {

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
        return null;
    }

    @Override
    public void addSimpleTeam(SimpleTeamDTO simpleTeamDTO) {

    }

    @Override
    public void removeSimpleTeam(SimpleTeamDTO simpleTeamDTO) {

    }
}
