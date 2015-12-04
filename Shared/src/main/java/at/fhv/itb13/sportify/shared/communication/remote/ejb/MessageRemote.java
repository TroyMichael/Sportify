package at.fhv.itb13.sportify.shared.communication.remote.ejb;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface MessageRemote {

    Serializable getMessage(String username);

    void sendMessage(String username, Serializable objectMessage);
}
