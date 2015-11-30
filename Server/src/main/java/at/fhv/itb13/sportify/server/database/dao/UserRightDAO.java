package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.UserRight;

public class UserRightDAO extends GenericDAOImpl<UserRight, String> {

    public UserRightDAO() {
        super(UserRight.class);
    }
}
