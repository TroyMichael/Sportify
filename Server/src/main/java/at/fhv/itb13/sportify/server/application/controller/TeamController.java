package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TeamMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;

public class TeamController {

    private DBFacade _facade;
    private TeamMapper _teamMapper;

    public TeamController() {
        _facade = new DBFacadeImpl();
        _teamMapper = new TeamMapper();
    }

    /**
     * Creates a new entry in the table team
     *
     * @param team save this object
     */
    public void create(TeamDTO team) {
        try {
            Team teamDomain = _teamMapper.toDomainObject(team);
            _facade.beginTransaction();
            _facade.create(teamDomain);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
    }
}
