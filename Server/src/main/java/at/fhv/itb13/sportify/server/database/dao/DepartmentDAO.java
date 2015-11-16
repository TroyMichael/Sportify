package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.GenericDAOImpl;
import at.fhv.itb13.sportify.server.model.Department;

public class DepartmentDAO extends GenericDAOImpl<Department, String> {
    public DepartmentDAO() {
        super(Department.class);
    }
}
