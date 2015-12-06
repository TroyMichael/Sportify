package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.shared.communication.dtos.UserDTO;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionFactoryRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;

import javax.ejb.Singleton;

@Singleton
public class SessionFactoryBean implements SessionFactoryRemote {

    public SessionFactoryBean() {
        super();
    }

    @Override
    public SessionRemote create(UserDTO userDTO) {
        SessionBean sessionBean = new SessionBean();
        if (sessionBean.login(userDTO)) {
            return sessionBean;
        }
        return null;
    }
}
