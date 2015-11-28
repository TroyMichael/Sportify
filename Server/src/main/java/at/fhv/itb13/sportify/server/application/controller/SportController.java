package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SportMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import java.util.LinkedList;
import java.util.List;

public class SportController {

    private DBFacade _facade;
    private SportMapper _sportMapper;

    public SportController() {
        _facade = new DBFacadeImpl();
        _sportMapper = new SportMapper();
    }

    public SportController(DBFacade facade, SportMapper sportMapper) {
        _facade = facade;
        _sportMapper = sportMapper;
    }

    public List<SportDTO> getSports() {
        List<Sport> allSports = null;
        try {
            _facade.beginTransaction();
            allSports = _facade.getAll(Sport.class);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
        }

        List<SportDTO> sportDTOList = new LinkedList<>();
        allSports.forEach(sport -> sportDTOList.add(_sportMapper.toDTOObject(sport)));
        return sportDTOList;
    }
}
