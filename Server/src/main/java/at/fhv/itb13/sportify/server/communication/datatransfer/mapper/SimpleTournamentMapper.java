package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTO;
import at.fhv.itb13.sportify.shared.communication.dtos.SimpleTournamentDTOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KYUSS on 26.11.2015.
 */
public class SimpleTournamentMapper extends Mapper<SimpleTournamentDTO, Tournament>{
    @Override
    public Tournament toDomainObject(SimpleTournamentDTO simpleTournamentDTO) {
        return null;
    }

    @Override
    public SimpleTournamentDTO toDTOObject(Tournament domainObject) {
        if (domainObject != null){
            return new SimpleTournamentDTOImpl(domainObject.getDescription(), domainObject.getLocation(), domainObject.getStart());
        }
        return null;
    }

    public List<SimpleTournamentDTO> toDTOList(List<Tournament> tournaments) {
        if (tournaments != null){
            List <SimpleTournamentDTO> simpleTournamentDTOs = new ArrayList<>();
            for (Tournament tournament : tournaments){
                simpleTournamentDTOs.add(toDTOObject(tournament));
            }
            return simpleTournamentDTOs;
        }
        return null;
    }
}
