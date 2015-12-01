package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.DisplayTeamMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.ExternalDisplayTeamMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TeamMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.ExternalTeam;
import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.shared.communication.dtos.DisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.ExternalDisplayTeamDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TeamDTO;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class TeamController {

    private DBFacade _facade;
    private TeamMapper _teamMapper;
    private ExternalDisplayTeamMapper _externalDisplayTeamMapper;
    private DisplayTeamMapper _displayTeamMapper;

    public TeamController() {
        _facade = new DBFacadeImpl();
        _teamMapper = new TeamMapper();
        _displayTeamMapper = new DisplayTeamMapper();
        _externalDisplayTeamMapper = new ExternalDisplayTeamMapper();
    }

    public TeamController(DBFacade facade, TeamMapper teamMapper, DisplayTeamMapper displayTeamMapper) {
        _facade = facade;
        _teamMapper = teamMapper;
        _displayTeamMapper = displayTeamMapper;
    }

    /**
     * Creates a new entry in the table team
     *
     * @param team save this object
     */
    public void create(TeamDTO team) {
        try {
            InternalTeam teamDomain = _teamMapper.toDomainObject(team);
            _facade.beginTransaction();
            _facade.create(teamDomain);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
    }
    /**
     * Creates a new entry in the table team
     *
     * @param team save this object
     */
    public void createExternalTeam(ExternalDisplayTeamDTO team) {
        try {
            ExternalTeam teamDomain = _externalDisplayTeamMapper.toDomainObject(team);
            _facade.beginTransaction();
            _facade.create(teamDomain);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
    }
    public void addPersonToTeam(PersonDTO personDTO) {

    }

    public void editTeam(TeamDTO team) {
        try {
            InternalTeam teamDomain = _teamMapper.toExistingDomainObject(team);
            _facade.beginTransaction();
            _facade.createOrUpdate(teamDomain);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
    }

    public List<TeamDTO> getAllTeams() {
        List<InternalTeam> teams = null;
        try {
            _facade.beginTransaction();
            teams = _facade.getAll(InternalTeam.class);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
        if (teams != null) {
            List<TeamDTO> teamDTOs = new ArrayList<>();
            for (InternalTeam team : teams) {
                teamDTOs.add(_teamMapper.toDTOObject(team));
            }
            return teamDTOs;
        }
        return null;
    }

    public List<DisplayTeamDTO> getAllDisplayTeams() {
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
                teamDetailList.add(_displayTeamMapper.toDTOObject(t));
            }
            return teamDetailList;
        }
        return null;
    }
}
