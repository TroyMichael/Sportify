package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Tournament;

public class TournamentDAO extends GenericDAOImpl<Tournament, String> {

    public TournamentDAO() {
        super(Tournament.class);
    }
}
