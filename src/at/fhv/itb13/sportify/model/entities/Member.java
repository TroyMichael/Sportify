package at.fhv.itb13.sportify.model.entities;

import javax.persistence.Entity;

/**
 * Created by mod on 10/22/15.
 */
@Entity(name = "Member")
public class Member {
    private String _fname = "";
    private String _lname = "";
    private String _address = "";
    private String _birthdate = "";

}
