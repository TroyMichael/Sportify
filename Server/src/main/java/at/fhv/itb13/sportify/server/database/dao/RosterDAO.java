package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Roster;

public class RosterDAO extends GenericDAOImpl<Roster, String> {
    public RosterDAO() {
        super(Roster.class);
    }
}
