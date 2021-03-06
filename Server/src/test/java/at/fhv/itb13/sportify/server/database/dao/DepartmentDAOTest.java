package at.fhv.itb13.sportify.server.database.dao;

import at.fhv.itb13.sportify.server.database.DepartmentMother;
import at.fhv.itb13.sportify.server.database.SessionFactoryRule;
import at.fhv.itb13.sportify.server.database.SportMother;
import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.shared.util.IdGenerator;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DepartmentDAOTest {

    @Rule
    public SessionFactoryRule _sf = new SessionFactoryRule();

    @Test
    public void get() {
        // arrange
        _sf.beginTransaction();
        String departmentId = IdGenerator.createId();
        DepartmentMother departmentMother = new DepartmentMother(_sf.getSession(), departmentId);
        Department department1 = departmentMother.instance();
        department1.addSport(new SportMother(_sf.getSession()).instance());
        _sf.commitTransaction();

        // act
        _sf.beginTransaction();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        departmentDAO.setSession(_sf.getSession());
        Department department2 = departmentDAO.get(departmentId);
        _sf.commitTransaction();

        // assert
        assertNotNull(department1);
        assertNotNull(department2);
        assertEquals(department1.getId(), department2.getId());
        assertEquals(department1.getVersion(), department2.getVersion());
        assertEquals(department1.getName(), department2.getName());
        assertEquals(department1.getDescription(), department2.getDescription());
        //assertNotNull(department1.getSports());
        //assertEquals(department1.getSports(), department2.getSports());
    }
}
