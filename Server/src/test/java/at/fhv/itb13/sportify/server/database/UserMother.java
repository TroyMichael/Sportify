package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.InternalTeam;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.server.model.Roster;
import at.fhv.itb13.sportify.server.model.User;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class UserMother extends PersistentObjectMother<User, UserMother> {

    private String _username = "username";
    private String _password = "password";

    public UserMother() {
        super(User.class);
    }

    public UserMother(Session session) {
        super(session, User.class);
    }

    public UserMother(Session session, String defaultId) {
        super(session, User.class, defaultId);
    }

    @Override
    protected void configureInstance(User user) {

        user.setPassword(_password);
        user.setUsername(_username);
    }

    @Override
    protected User createInstance() {
        User user = new User();
        user.setId(getId());

        return user;
    }

    public UserMother username(String username){
        _username = username;
        return this;
    }

    public UserMother password(String password){
        _password =password;
        return this;
    }


}
