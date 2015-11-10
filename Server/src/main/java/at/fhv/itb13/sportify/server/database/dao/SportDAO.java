package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Sport;

public class SportDAO extends GenericDAOImpl<Sport, String> {
    public SportDAO() {
        super(Sport.class);
    }
}
