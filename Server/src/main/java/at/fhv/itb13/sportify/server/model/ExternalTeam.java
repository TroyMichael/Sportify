package at.fhv.itb13.sportify.server.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "externalteam")
@PrimaryKeyJoinColumn(name = "team_id")
public class ExternalTeam extends Team {

    public ExternalTeam() {
    }

    public ExternalTeam(String name, Sport sport) {
        super(name, sport);
    }
}
