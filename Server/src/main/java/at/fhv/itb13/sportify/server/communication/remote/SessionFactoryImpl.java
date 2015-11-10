package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.SessionFactory;

/**
 * Created by Caroline on 10.11.2015.
 */
public class SessionFactoryImpl implements SessionFactory {


    @Override
    public SessionImpl create(UserDTO userDTO) {
        return SessionImpl.create(userDTO);
    }
}
