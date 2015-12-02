package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.MatchMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.SimpleTournamentMapper;
import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.TournamentMapper;
import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.MatchTeam;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import org.hibernate.HibernateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TournamentController {

    private DBFacade _facade;
    private TournamentMapper _tournamentMapper;
    private SimpleTournamentMapper _simpleTournamentMapper;

    public TournamentController() {
        _facade = new DBFacadeImpl();
        _tournamentMapper = new TournamentMapper();
        _simpleTournamentMapper = new SimpleTournamentMapper();
    }

    public TournamentController(DBFacade facade, TournamentMapper tournamentMapper, SimpleTournamentMapper simpleTournamentMapper) {
        _facade = facade;
        _tournamentMapper = tournamentMapper;
        _simpleTournamentMapper = simpleTournamentMapper;
    }

    public void create(TournamentDTO tournamentDTO) {
        Tournament tournament = _tournamentMapper.toDomainObject(tournamentDTO);
        try {
            MatchMapper matchMapper = new MatchMapper();
            Set<Match> matches = new HashSet<>();
            for (MatchDTO matchDTO : tournamentDTO.getMatches()) {
                Match match = matchMapper.toDomainObject(matchDTO);
                matches.add(match);
                match.setTournament(tournament);
            }
            tournament.setMatches(matches);

            _facade.beginTransaction();
            for (Match match : tournament.getMatches()) {
                _facade.create(match);
            }

            _facade.create(tournament);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void saveOrUpdate(TournamentDTO tournamentDTO) {
        try {
            //todo verify if tournament mapper doesn't need to call "toExistingDomainObject"
            //todo to ensure that the tournament is not being duplicated
            Tournament tournamentDomain = _tournamentMapper.toDomainObject(tournamentDTO);
            _facade.beginTransaction();
            _facade.createOrUpdate(tournamentDomain);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
    }

    public List<TournamentDTO> getAllTournaments() {
        List<Tournament> tournaments = null;
        try {
            _facade.beginTransaction();
            tournaments = _facade.getAll(Tournament.class);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
        return _tournamentMapper.toDTOList(tournaments);
    }

    public List<SimpleTournamentDTO> getAllSimpleTournaments() {
        List<Tournament> tournaments = null;
        try {
            _facade.beginTransaction();
            tournaments = _facade.getAll(Tournament.class);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
        return _simpleTournamentMapper.toDTOList(tournaments);
    }

    public TournamentDTO getByID(String id) {
        TournamentDTO newTournamentDTO = null;
        try {
            _facade.beginTransaction();
            Tournament tournament = _facade.get(Tournament.class, id);
            newTournamentDTO = _tournamentMapper.toDTOObject(tournament);
            _facade.commitTransaction();
        } catch (HibernateException e) {
            _facade.rollbackTransaction();
            e.printStackTrace();
        }
        return newTournamentDTO;
    }
}
