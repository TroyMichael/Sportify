package at.fhv.itb13.sportify.server.database;

import at.fhv.itb13.sportify.server.model.User;

public class UserMother extends ObjectMother<User> {

    private String _username = "username";
    private String _password = "password";

    public UserMother() {
        super();
    }

    @Override
    protected void configureInstance(User user) {
        user.setPassword(_password);
        user.setUsername(_username);
    }

    @Override
    protected User createInstance() {
        return new User();
    }

    public UserMother username(String username) {
        _username = username;
        return this;
    }

    public UserMother password(String password) {
        _password = password;
        return this;
    }
}
