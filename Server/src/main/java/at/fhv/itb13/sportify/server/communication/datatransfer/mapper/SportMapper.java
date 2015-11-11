package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DTOIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.FacadeFailedException;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTOImpl;

/**
 * Created by Michael on 11.11.2015.
 *
 */
public class SportMapper extends Mapper<SportDTO, Sport>{
    private DBFacade _dbFacade = new DBFacadeImpl();

    @Override
    public Sport toDomainObject(SportDTO sportDTO) throws DTOIsNullException, FacadeFailedException {

        if (sportDTO != null) {
            Sport newSport = new Sport();
            newSport.setName(sportDTO.getName());

            try {
                _dbFacade.beginTransaction();
                //set department
                newSport.setDepartment(_dbFacade.get(Department.class, sportDTO.getDepartmentId()));

                //set teams
                for (String teamID : sportDTO.getTeamIds()) {
                    newSport.addTeam(_dbFacade.get(Team.class, teamID));
                }
                _dbFacade.commitTransaction();
            } catch (Exception e) {
                _dbFacade.rollbackTransaction();
                throw new FacadeFailedException();
            }

            return newSport;
        } else {
            throw new DTOIsNullException();
        }
    }

    @Override
    public SportDTO toDTOObject(Sport domainObject) throws DomainObjectIsNullException {

        if (domainObject != null) {
            SportDTO newSportDTO = new SportDTOImpl(domainObject.getName());
            newSportDTO.setDepartment(domainObject.getDepartment().getId());
            domainObject.getTeams().forEach(team -> newSportDTO.addTeam(team.getId()));

            return newSportDTO;
        } else {
            throw new DomainObjectIsNullException();
        }
    }
}
