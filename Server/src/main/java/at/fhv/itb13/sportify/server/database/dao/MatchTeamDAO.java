package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.MatchTeam;

public class MatchTeamDAO extends GenericDAOImpl<MatchTeam, String> {

    public MatchTeamDAO() {
        super(MatchTeam.class);
    }
}
