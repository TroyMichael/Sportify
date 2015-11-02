package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Patrick on 29.10.2015.
 */
public class ServiceLocator {

    private static final String RMI_SERVER = "rmi://localhost:12345/";

    private static ServiceLocator _instance;

    private PersonRemote _personRemote;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (_instance == null) {
            _instance = new ServiceLocator();
        }
        return _instance;
    }

    public PersonRemote getPersonRemote() throws RemoteException {
        if (_personRemote == null) {
            String url = RMI_SERVER + "PersonServant";
            try {
                _personRemote = (PersonRemote) Naming.lookup(url);
            } catch (MalformedURLException | NotBoundException e) {
                throw new InternalError();
            }
        }
        return _personRemote;
    }
}
