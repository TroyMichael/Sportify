package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.shared.communication.remote.PersonRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Patrick on 29.10.2015.
 */
public class ServiceLocator {

    private static final String RMI_SERVER = "rmi://localhost:12345/";

    private static ServiceLocator _instance;

    private Map<Class, Remote> _remoteObjects;
    private Map<Class, String> _remoteUrls;

    private ServiceLocator() {
        // init map for remote objects
        _remoteObjects = new HashMap<Class, Remote>();

        // init map for remote urls
        _remoteUrls = new HashMap<Class, String>();
        // add remote urls to map
        _remoteUrls.put(PersonRemote.class, "PersonServant");
    }

    public static ServiceLocator getInstance() {
        if (_instance == null) {
            _instance = new ServiceLocator();
        }
        return _instance;
    }

    public <T extends Remote> T getRemote(Class<T> cls) throws RemoteException {
        if (_remoteObjects.get(cls) == null) {
            try {
                _remoteObjects.put(cls, Naming.lookup(getUrl(cls)));
            } catch (MalformedURLException | NotBoundException e) {
                throw new InternalError();
            } catch (RemoteException e) {
                // clear remote object map to allow another lookup
                _remoteObjects.clear();
                throw e;
            }
        }
        return (T) _remoteObjects.get(cls);
    }

    private <T extends Remote> String getUrl(Class<T> cls) {
        String url = _remoteUrls.get(cls);
        if (url == null) {
            throw new InternalError();
        }
        return RMI_SERVER + url;
    }
}
