package at.fhv.itb13.sportify.database;

import at.fhv.itb13.sportify.server.model.Department;
import at.fhv.itb13.sportify.server.model.Sport;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class DepartmentMother extends PersistentObjectMother<Department, DepartmentMother> {

    private String _name = "name";
    private String _description = "description";
    private Set<Sport> _sports = new HashSet<Sport>();

    public DepartmentMother() {
        super(Department.class);
    }

    public DepartmentMother(Session session) {
        super(session, Department.class);
    }

    public DepartmentMother(Session session, String defaultId) {
        super(session, Department.class, defaultId);
    }

    @Override
    protected void configureInstance(Department department) {
        department.setName(_name);
        department.setDescription(_description);
        department.setSports(_sports);
    }

    @Override
    protected Department createInstance() {
        Department department = new Department();
        department.setId(getId());
        return department;
    }

    public DepartmentMother name(String name) {
        _name = name;
        return this;
    }

    public DepartmentMother description(String description) {
        _description = description;
        return this;
    }

    public DepartmentMother sport(Sport sport) {
        _sports.add(sport);
        return this;
    }
}