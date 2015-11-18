package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TeamMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

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
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void addPersonToTeam(PersonDTO personDTO) {

    }

    public void editTeam(TeamDTO team) {
        try {
            Team teamDomain = _teamMapper.toDomainObject(team);
            _facade.beginTransaction();
            _facade.merge(teamDomain);
            //_facade.createOrUpdate(teamDomain);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
    }

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = null;
        try {
            _facade.beginTransaction();
            teams = _facade.getAll(Team.class);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
        if (teams != null) {
            List<TeamDTO> teamDTOs = new ArrayList<>();
            for (Team team : teams) {
                teamDTOs.add(_teamMapper.toDTOObject(team));
            }
            return teamDTOs;
        }
        return null;
    }
}
