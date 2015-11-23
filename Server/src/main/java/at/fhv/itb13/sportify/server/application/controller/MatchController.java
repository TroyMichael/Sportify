package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.MatchMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;

/**
 * Created by Caroline on 21.11.2015.
 *
 */
public class MatchController {

    private DBFacade _facade;
    private MatchMapper _matchMapper;

    public MatchController (DBFacade facade, MatchMapper matchMapper){
        _facade = facade;
        _matchMapper = matchMapper;
    }

    public MatchController (){
        _facade = new DBFacadeImpl();
        _matchMapper = new MatchMapper();
    }

    public void create(MatchDTO matchDto) {
        try {
            Match match = _matchMapper.toDomainObject(matchDto);
            _facade.beginTransaction();
            _facade.create(match);
            _facade.commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
    }
}