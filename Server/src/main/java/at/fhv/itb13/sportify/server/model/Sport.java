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
@Table(name = "sport")
public class Sport extends PersistentObjectImpl {
    private String _sportname;
    private List<Team> _teams;

    public Sport(String sportname) {
        _sportname = sportname;
    }

    public Sport() {
    }

    @Column(name = "sportname")
    public String getSportname() {
        return _sportname;
    }

    public void setSportname(String sportname) {
        _sportname = sportname;
    }
}
