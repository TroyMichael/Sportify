package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Match;

public class MatchDAO extends GenericDAOImpl<Match, String> {

    public MatchDAO() {
        super(Match.class);
    }
}
