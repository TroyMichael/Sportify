package at.fhv.itb13.sportify.server.model;

import at.fhv.itb13.sportify.server.database.PersistentObjectImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by mod on 11/4/15.
 */
@Entity
@Table(name = "department")
public class Department extends PersistentObjectImpl {

    private String _name;
    private String _description;
    private List<Sport> _sports;

    public Department(String deptName){
        _name = deptName;
    }

    public Department(){}

    @Column(name = "name")
    public String getName(){
        return _name;
    }

    @Column(name = "description")
    public String getDescription(){
        return _description;
    }

    public List<Sport> getSports(){
        return _sports;
    }

    public void setDeptName(String deptName){
        _name = deptName;
    }

    public void addSport(Sport sport){
        _sports.add(sport);
    }
}
