package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Team;

public class TeamDAO extends GenericDAOImpl<Team, String> {

    public TeamDAO() {
        super(Team.class);
    }
}
