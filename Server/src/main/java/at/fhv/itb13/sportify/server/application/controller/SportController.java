package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SimpleSportMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SportMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleSportDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;

import java.util.LinkedList;
import java.util.List;

public class SportController {

    private DBFacade _facade;
    private SportMapper _sportMapper;
    private SimpleSportMapper _simpleSportMapper;

    public SportController() {
        _facade = new DBFacadeImpl();
        _sportMapper = new SportMapper();
        _simpleSportMapper = new SimpleSportMapper();
    }

    public SportController(DBFacade facade, SportMapper sportMapper, SimpleSportMapper simpleSportMapper) {
        _facade = facade;
        _sportMapper = sportMapper;
        _simpleSportMapper = simpleSportMapper;
    }

    public List<SportDTO> getSports() {
        List<Sport> allSports = null;
        try {
            _facade.beginTransaction();
            allSports = _facade.getAll(Sport.class);
            _facade.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }

        List<SportDTO> sportDTOList = new LinkedList<>();
        allSports.forEach(sport -> sportDTOList.add(_sportMapper.toDTOObject(sport)));
        return sportDTOList;
    }

    public List<SimpleSportDTO> getAllSimpleSports (){
        List<Sport> allSports = null;
        try {
            _facade.beginTransaction();
            allSports = _facade.getAll(Sport.class);
            _facade.commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
        List <SimpleSportDTO> simpleSportDTOs = new LinkedList<>();
        allSports.forEach(sport -> simpleSportDTOs.add(_simpleSportMapper.toDTOObject(sport)));
        return simpleSportDTOs;
    }
}
