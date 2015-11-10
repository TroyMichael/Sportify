package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Sport;

/**
 * Created by mod on 11/4/15.
 */
public class SportDAO extends GenericDAOImpl<Sport,String> {
    public SportDAO() {
        super(Sport.class);
    }
}
