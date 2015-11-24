package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.DepartmentMother;
import at.fhv.itb13.sportify.server.database.SportMother;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DepartmentTest {
/*
    private Department _department;

    @Before
    public void setUp() {
        _department = new DepartmentMother().sport(new SportMother().instance()).instance();
    }

    @Test
    public void setSports() {
        // arrange
        Set<Sport> oldSports = _department.getSports();
        Set<Sport> newSports = new HashSet<Sport>();
        SportMother sportMother = new SportMother();
        newSports.add(sportMother.instance());

        // assert before
        assertEquals(_department.getSports(), oldSports);
        for (Sport sport : oldSports) {
            assertEquals(sport.getDepartment(), _department);
        }
        for (Sport sport : newSports) {
            assertNull(sport.getDepartment());
        }

        // act
        _department.setSports(newSports);

        // assert after
        assertEquals(_department.getSports(), newSports);
        for (Sport sport : oldSports) {
            assertNull(sport.getDepartment());
        }
        for (Sport sport : newSports) {
            assertEquals(sport.getDepartment(), _department);
        }
    }

    @Test
    public void addSport() {
        // arrange
        Set<Sport> oldSports = _department.getSports();
        Sport newSport = new SportMother().instance();

        // assert before
        assertTrue(!_department.getSports().contains(newSport));
        assertTrue(_department.getSports().size() == 1);
        for (Sport sport : oldSports) {
            assertEquals(sport.getDepartment(), _department);
        }
        assertNull(newSport.getDepartment());

        // act
        _department.addSport(newSport);

        // assert after
        assertTrue(_department.getSports().contains(newSport));
        assertTrue(_department.getSports().size() == 2);
        for (Sport sport : _department.getSports()) {
            assertEquals(sport.getDepartment(), _department);
        }
    }

    @Test
    public void removeSport() {
        // arrange
        Set<Sport> oldSports = _department.getSports();
        Sport oldSport = oldSports.iterator().next();

        // assert before
        assertTrue(_department.getSports().contains(oldSport));
        assertTrue(_department.getSports().size() == 1);
        assertEquals(oldSport.getDepartment(), _department);

        // act
        _department.removeSport(oldSport);

        // assert after
        assertTrue(!_department.getSports().contains(oldSport));
        assertTrue(_department.getSports().size() == 0);
        assertNull(oldSport.getDepartment());
    }*/
}
