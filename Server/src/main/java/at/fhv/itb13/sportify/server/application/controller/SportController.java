package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.exceptions.DomainObjectIsNullException;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SportMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mod on 11/10/15.
 *
 */
public class SportController {
    private SportMapper _sportMapper = new SportMapper();
    private DBFacade _facade;


    public SportController() {
        _facade = new DBFacadeImpl();
    }

    /**
     *
     */
    public List<SportDTO> getSports(){

        List<Sport> allSports = new LinkedList<>();

        try {
            _facade.beginTransaction();
            allSports = _facade.getAll(Sport.class);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
        }

        List<SportDTO> sportDTOList = new LinkedList<>();
        allSports.forEach(sport -> {
            try {
                sportDTOList.add(_sportMapper.toDTOObject(sport));
            } catch (DomainObjectIsNullException e) {
                e.printStackTrace();
            }
        });

        return sportDTOList;
    }
}
