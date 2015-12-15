package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface MessageRemote {

    void setSession(SessionRemote session);

    Serializable getMessage(String username);

    void sendMessage(String username, Serializable objectMessage);
}
