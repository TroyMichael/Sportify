package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.InternalTeam;

public class TeamDAO extends GenericDAOImpl<InternalTeam, String> {

    public TeamDAO() {
        super(InternalTeam.class);
    }
}
