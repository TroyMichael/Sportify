package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TournamentMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

/**
 * Created by mod on 11/19/15.
 */
public class TournamentController {
    private DBFacade _facade;
    private TournamentMapper _tournamentMapper;
    public TournamentController(){
        _facade = new DBFacadeImpl();
        _tournamentMapper = new TournamentMapper();
    }

    public TournamentController(DBFacade facade, TournamentMapper tournamentMapper) {
        _facade = facade;
        _tournamentMapper = tournamentMapper;
    }

    public void create(TournamentDTO tournamentDTO){
        Tournament tournament = _tournamentMapper.toDomainObject(tournamentDTO);
        try{
            _facade.beginTransaction();
            _facade.create(tournament);
            _facade.commitTransaction();
        }catch (Exception e){
            _facade.rollbackTransaction();
            e.printStackTrace();
        }


    }


}