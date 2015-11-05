package at.fhv.itb13.sportify.shared.communication.remote;

import java.rmi.Remote;

/**
 * Created by mod on 11/5/15.
 */
public interface UserRemote extends Remote {

    void login();
}
