package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.Roster;

public class RosterDAO extends GenericDAOImpl<Roster, String> {
    public RosterDAO() {
        super(Roster.class);
    }
}
