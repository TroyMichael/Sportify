package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Match;
import at.fhv.itb13.sportify.server.model.Sport;
import at.fhv.itb13.sportify.server.model.Team;
import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTOImpl;
import org.hibernate.HibernateException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by KYUSS on 26.11.2015.
 *
 */
public class SimpleTournamentMapper extends Mapper<SimpleTournamentDTO, Tournament>{

    private SimpleTeamMapper _simpleTeamMapper = new SimpleTeamMapper();
    private MatchMapper _matchMapper = new MatchMapper();

    @Override
    public Tournament toDomainObject(SimpleTournamentDTO simpleTournamentDTO) {
        return null;
    }

    @Override
    public SimpleTournamentDTO toDTOObject(Tournament domainObject) {
        if (domainObject != null){
            SimpleTournamentDTO simpleTournamentDTO = new SimpleTournamentDTOImpl(domainObject.getDescription(), domainObject.getLocation(), domainObject.getStart(), domainObject.getSport().getName());
            simpleTournamentDTO.setId(domainObject.getId());
            simpleTournamentDTO.setVersion(domainObject.getVersion());
            simpleTournamentDTO.setTeams(_simpleTeamMapper.toDTOSet(domainObject.getTeams()));
            simpleTournamentDTO.setMatches(_matchMapper.toDTOSet(domainObject.getMatches()));
            return simpleTournamentDTO;
        }
        return null;
    }

    public List<SimpleTournamentDTO> toDTOList(List<Tournament> tournaments) {
        if (tournaments != null){
            List <SimpleTournamentDTO> simpleTournamentDTOs = new LinkedList<>();
            for (Tournament tournament : tournaments){
                simpleTournamentDTOs.add(toDTOObject(tournament));
            }
            return simpleTournamentDTOs;
        }
        return null;
    }
}
