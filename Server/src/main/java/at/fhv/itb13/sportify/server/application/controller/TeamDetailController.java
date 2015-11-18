package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TeamDetailMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDetailDTOImpl;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailController {

    private DBFacade _facade;
    private TeamDetailMapper _teamMapper;

    public TeamDetailController() {
        _facade = new DBFacadeImpl();
        _teamMapper = new TeamDetailMapper();
    }

    public List<TeamDetailDTO> getAllTeams() {
        List<Team> allTeams = null;

        try {
            _facade.beginTransaction();
            allTeams = _facade.getAll(Team.class);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }

        if (allTeams != null) {
            List<TeamDetailDTO> teamDetailList = new ArrayList<>();
            for (Team t : allTeams) {
                teamDetailList.add(_teamMapper.toDTOObject(t));
            }
            return teamDetailList;
        }
        return null;
    }
}
