package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.InternalTeam;

public class InternalTeamDAO extends GenericDAOImpl<InternalTeam, String> {

    public InternalTeamDAO() {
        super(InternalTeam.class);
    }
}
