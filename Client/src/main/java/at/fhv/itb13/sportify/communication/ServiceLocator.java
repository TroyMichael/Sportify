package at.fhv.itb13.sportify.communication;

import at.fhv.itb13.sportify.communication.remote.PersonRemote;

import java.rmi.Naming;

/**
 * Created by Patrick on 29.10.2015.
 */
public class ServiceLocator {

    private static final String RMI_SERVER = "rmi://localhost/";
    private static ServiceLocator _instance;

    public static ServiceLocator getInstance() {
        if (_instance == null) {
            _instance = new ServiceLocator();
        }
        return _instance;
    }

    private PersonRemote _personRemote;

    private ServiceLocator() {
    }

    public PersonRemote getPersonRemote() {
        if (_personRemote == null) {
            try {
                _personRemote = (PersonRemote) Naming.lookup(RMI_SERVER + "PersonServant");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return _personRemote;
    }
}
