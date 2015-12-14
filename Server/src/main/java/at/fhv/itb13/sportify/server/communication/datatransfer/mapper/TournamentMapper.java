package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.database.DBFacade;
import at.fhv.itb13.sportify.server.database.DBFacadeImpl;
import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTOImpl;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by KYUSS on 19.11.2015.
 */
public class TournamentMapper extends Mapper<TournamentDTO, Tournament> {

    private DBFacade _dbFacade;
    private MatchMapper _matchMapper = new MatchMapper();

    public TournamentMapper() {
        _dbFacade = new DBFacadeImpl();
    }

    public TournamentMapper(DBFacade facade) {
        _dbFacade = facade;
    }

    /**
     * use this method to update a tournament in the database
     *
     * @param tournamentDTO incoming tournament DTO to map
     * @return tournament domain object or null
     */
    public Tournament toExistingDomainObject(TournamentDTO tournamentDTO) {
        if (tournamentDTO.getId() != null) {
            try {
                _dbFacade.beginTransaction();
                Set<Match> matches = new HashSet<>();
                if (tournamentDTO.getMatches().size() > 0) {
                    for (MatchDTO matchDTO : tournamentDTO.getMatches()) {
                        if (_dbFacade.get(Match.class, matchDTO.getId()) != null) {
                            matches.add(_dbFacade.get(Match.class, matchDTO.getId()));
                        } else {
                            _dbFacade.commitTransaction();
                            matches.add(_matchMapper.toDomainObject(matchDTO));
                            _dbFacade.beginTransaction();
                        }
                    }
                }
                Tournament tournament = _dbFacade.get(Tournament.class, tournamentDTO.getId());
                tournament.setMatches(matches);
                tournament.setDescription(tournamentDTO.getDescription());
                tournament.setLocation(tournamentDTO.getLocation());
                if (tournamentDTO.getVersion() != null) {
                    tournament.setVersion(tournamentDTO.getVersion());
                }
                tournament.setStart(tournamentDTO.getStartDate());
                if (tournamentDTO.getTeamIDs().size() > 0) {
                    tournament.setTeams(new HashSet<Team>());
                    for (String teamID : tournamentDTO.getTeamIDs()) {
                        tournament.addTeam(_dbFacade.get(Team.class, teamID));
                    }
                }
                if (!tournament.getSport().getId().equals(tournamentDTO.getSportID())) {
                    tournament.setSport(_dbFacade.get(Sport.class, tournamentDTO.getSportID()));
                }
                _dbFacade.commitTransaction();
                return tournament;
            } catch (HibernateException e) {
                _dbFacade.rollbackTransaction();
            }
        }
        return null;
    }

    @Override
    public Tournament toDomainObject(TournamentDTO tournamentDTO) {
        if (tournamentDTO != null) {
            Tournament tournament = new Tournament();
            tournament.setDescription(tournamentDTO.getDescription());
            tournament.setStart(tournamentDTO.getStartDate());
            tournament.setLocation(tournamentDTO.getLocation());
            if (tournamentDTO.getId() != null) {
                tournament.setId(tournamentDTO.getId());
            }
            if (tournamentDTO.getVersion() != null) {
                tournament.setVersion(tournamentDTO.getVersion());
            }
            if (tournamentDTO.getMatches().size() > 0) {
                for (MatchDTO matchDTO : tournamentDTO.getMatches()) {
                    tournament.addMatch(_matchMapper.toDomainObject(matchDTO));
                }
            }
            try {
                _dbFacade.beginTransaction();
                if (tournamentDTO.getTeamIDs().size() > 0) {
                    for (String teamID : tournamentDTO.getTeamIDs()) {
                        tournament.addTeam(_dbFacade.get(Team.class, teamID));
                    }
                }
                if (tournamentDTO.getSportID().length() > 0) {
                    tournament.setSport(_dbFacade.get(Sport.class, tournamentDTO.getSportID()));
                }
                _dbFacade.commitTransaction();
            } catch (HibernateException e) {
                e.printStackTrace();
                _dbFacade.rollbackTransaction();
            }
            return tournament;
        }
        return null;
    }

    @Override
    public TournamentDTO toDTOObject(Tournament domainObject) {
        if (domainObject != null) {
            TournamentDTO tournamentDTO = new TournamentDTOImpl();
            tournamentDTO.setId(domainObject.getId());
            tournamentDTO.setVersion(domainObject.getVersion());
            tournamentDTO.setDescription(domainObject.getDescription());
            if (domainObject.getSport() != null) {
                tournamentDTO.setSportID(domainObject.getSport().getId());
            }
            for (Match match : domainObject.getMatches()) {
                tournamentDTO.addMatch(_matchMapper.toDTOObject(match));
            }
            for (Team team : domainObject.getTeams()) {
                tournamentDTO.addTeamID(team.getId());
            }
            tournamentDTO.setStartDate(domainObject.getStart());
            tournamentDTO.setLocation(domainObject.getLocation());
            return tournamentDTO;
        }
        return null;
    }

    public List<TournamentDTO> toDTOList(List<Tournament> tournaments) {
        List<TournamentDTO> tournamentDTOs = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            tournamentDTOs.add(toDTOObject(tournament));
        }
        return tournamentDTOs;
    }
}
