package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.DisplayTeamMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16.11.2015.
 *
 */
public class TeamDetailController {

    private DBFacade _facade;
    private DisplayTeamMapper _teamMapper;

    public TeamDetailController() {
        _facade = new DBFacadeImpl();
        _teamMapper = new DisplayTeamMapper();
    }

    public List<DisplayTeamDTO> getAllTeams() {
        List<InternalTeam> allTeams = null;

        try {
            _facade.beginTransaction();
            allTeams = _facade.getAll(InternalTeam.class);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }

        if (allTeams != null) {
            List<DisplayTeamDTO> teamDetailList = new ArrayList<>();
            for (InternalTeam t : allTeams) {
                teamDetailList.add(_teamMapper.toDTOObject(t));
            }
            return teamDetailList;
        }
        return null;
    }
}
