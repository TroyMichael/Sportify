package at.fhv.itb13.sportify.shared.communication.dtos;

public class UserDTOImpl implements UserDTO {

    private String _name;
    private String _password;

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public String getPassword() {
        return _password;
    }

    @Override
    public void setPassword(String password) {
        _password = password;
    }
}
