package at.fhv.itb13.sportify.shared.communication.remote;

import javax.jms.ObjectMessage;
import java.rmi.RemoteException;

/**
 * Created by KYUSS on 29.11.2015.
 */
public interface MessageRemote {

    void getMessage (String queueName) throws RemoteException;
    void sendMessage (String queueName, ObjectMessage objectMessage) throws RemoteException;
}
