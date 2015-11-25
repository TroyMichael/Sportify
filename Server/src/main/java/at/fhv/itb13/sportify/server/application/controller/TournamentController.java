package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.MatchMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TournamentMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by mod on 11/19/15.

 */
public class TournamentController {
    private DBFacade _facade;
    private TournamentMapper _tournamentMapper;

    public TournamentController() {
        _facade = new DBFacadeImpl();
        _tournamentMapper = new TournamentMapper();
    }

    public TournamentController(DBFacade facade, TournamentMapper tournamentMapper) {
        _facade = facade;
        _tournamentMapper = tournamentMapper;
    }


    public void create(TournamentDTO tournamentDTO) {
        Tournament tournament = _tournamentMapper.toDomainObject(tournamentDTO);
        try {
            //_facade.beginTransaction();
            //_facade.create(tournament);
            //_facade.commitTransaction();
            //_facade.beginTransaction();
            MatchMapper matchMapper = new MatchMapper();
            Set<Match> matches = new HashSet<>();
            for(MatchDTO matchDTO:tournamentDTO.getMatches()){
                Match match = matchMapper.toDomainObject(matchDTO);
                //_facade.create(match);
                matches.add(match);
                match.setTournament(tournament);
            }
          //  tournament.getMatches().forEach(match -> _facade.create(match));

            //_facade.commitTransaction();
            //_facade.beginTransaction();
            tournament.setMatches(matches);
            _facade.beginTransaction();
            for(Match match : tournament.getMatches()) {
                _facade.create(match);
            }
            _facade.create(tournament);
            _facade.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
    }


    public void saveOrUpdate(TournamentDTO tournamentDTO) {
        try {
            Tournament tournamentDomain = _tournamentMapper.toDomainObject(tournamentDTO);
            _facade.beginTransaction();
            _facade.createOrUpdate(tournamentDomain);
            _facade.commitTransaction();
        } catch (Exception e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }

    }

    public List<TournamentDTO> getAllTournaments() {
        List <Tournament> _tournamentDTOs = new LinkedList<>();
        try {
            _facade.beginTransaction();
            _tournamentDTOs = _facade.getAll(Tournament.class);
            _facade.commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
            _facade.rollbackTransaction();
        }
        List <TournamentDTO> tournamentDTOs;
        tournamentDTOs = _tournamentMapper.toDTOList(_tournamentDTOs);
        return tournamentDTOs;
    }
}
