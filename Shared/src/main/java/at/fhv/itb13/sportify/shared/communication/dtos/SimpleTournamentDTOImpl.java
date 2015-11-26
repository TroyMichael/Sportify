package at.fhv.itb13.sportify.shared.communication.dtos;

import java.sql.Date;

/**
 * Created by KYUSS on 26.11.2015.
 */
public class SimpleTournamentDTOImpl extends DTOImpl implements SimpleTournamentDTO{

    private String _description;
    private String _location;
    private Date _startDate;

    public SimpleTournamentDTOImpl (String description, String location, Date startDate){
        _description = description;
        _startDate = startDate;
        _location = location;
    }


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
}
