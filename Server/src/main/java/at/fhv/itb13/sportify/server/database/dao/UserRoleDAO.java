package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.UserRole;

public class UserRoleDAO extends GenericDAOImpl<UserRole, String> {

    public UserRoleDAO() {
        super(UserRole.class);
    }
}
