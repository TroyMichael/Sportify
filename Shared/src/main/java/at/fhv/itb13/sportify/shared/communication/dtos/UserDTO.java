package at.fhv.itb13.sportify.shared.communication.dtos;

import java.io.Serializable;

public interface UserDTO extends Serializable, DTO{
    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);
}
