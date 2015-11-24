package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.ExternalTeam;

public class ExternalTeamDAO extends GenericDAOImpl<ExternalTeam, String> {

    public ExternalTeamDAO() {
        super(ExternalTeam.class);
    }
}
