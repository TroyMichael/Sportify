package at.fhv.itb13.sportify.communication;

import at.fhv.itb13.sportify.communication.remote.PersonRemote;

import java.rmi.Naming;

/**
 * Created by Patrick on 29.10.2015.
 */
public class ServiceLocator {

    private static final String RMISERVER = "rmi://localhost/";

    private PersonRemote _personRemote;

    public PersonRemote getPersonRemote() {
        if (_personRemote == null) {
            try {
                _personRemote = (PersonRemote) Naming.lookup(RMISERVER + "PersonServant");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return _personRemote;
    }
}
