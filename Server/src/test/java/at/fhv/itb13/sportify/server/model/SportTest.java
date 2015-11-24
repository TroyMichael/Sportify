package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.DepartmentMother;
import at.fhv.itb13.sportify.server.database.SportMother;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SportTest {

    private Sport _sport;

    @Before
    public void setUp() {
        _sport = new SportMother().department(new DepartmentMother().instance()).instance();
    }

    @Test
    public void setDepartmentWithDepartment() {
        // arrange
        Department oldDepartment = _sport.getDepartment();
        Department newDepartment = new DepartmentMother().instance();

        // assert before
        assertEquals(_sport.getDepartment(), oldDepartment);
        assertTrue(oldDepartment.getSports().contains(_sport));
        assertTrue(oldDepartment.getSports().size() == 1);
        assertTrue(!newDepartment.getSports().contains(_sport));
        assertTrue(newDepartment.getSports().size() == 0);

        // act
        _sport.setDepartment(newDepartment);

        // assert after
        assertEquals(_sport.getDepartment(), newDepartment);
        assertTrue(!oldDepartment.getSports().contains(_sport));
        assertTrue(oldDepartment.getSports().size() == 0);
        assertTrue(newDepartment.getSports().contains(_sport));
        assertTrue(newDepartment.getSports().size() == 1);
    }

    @Test
    public void setDepartmentWithNull() {
        // arrange
        Department oldDepartment = _sport.getDepartment();

        // assert before
        assertEquals(_sport.getDepartment(), oldDepartment);
        assertTrue(oldDepartment.getSports().contains(_sport));
        assertTrue(oldDepartment.getSports().size() == 1);

        // act
        _sport.setDepartment(null);

        // assert after
        assertNull(_sport.getDepartment());
        assertTrue(!oldDepartment.getSports().contains(_sport));
        assertTrue(oldDepartment.getSports().size() == 0);
    }
}

