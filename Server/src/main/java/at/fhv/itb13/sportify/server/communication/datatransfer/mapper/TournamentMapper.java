package at.fhv.itb13.sportify.server.communication.datatransfer.mapper;

import at.fhv.itb13.sportify.server.model.Tournament;
import at.fhv.itb13.sportify.shared.communication.dtos.TournamentDTO;

/**
 * Created by mod on 11/19/15.
 */
public class TournamentMapper extends Mapper<TournamentDTO, Tournament> {
    private static TournamentMapper ourInstance = new TournamentMapper();

    public static TournamentMapper getInstance() {
        return ourInstance;
    }

    private TournamentMapper() {
    }

    @Override
    public Tournament toDomainObject(TournamentDTO tournamentDTO) {
        return null;
    }

    @Override
    public TournamentDTO toDTOObject(Tournament domainObject) {
        return null;
    }
}
