package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {

    private static ServiceLocator _instance;

    private Map<Class, Object> _remoteObjects;
    private Map<Class, String> _remoteUrls;

    private ServiceLocator() {
        // init map for remote objects
        _remoteObjects = new HashMap<>();

        // init map for remote urls
        _remoteUrls = new HashMap<>();
        // add remote urls to map
        _remoteUrls.put(SessionRemote.class, SessionRemote.class.getCanonicalName());
    }

    public static ServiceLocator getInstance() {
        if (_instance == null) {
            _instance = new ServiceLocator();
        }
        return _instance;
    }

    public void reload() {
        // clear remote object map to allow another lookup
        _remoteObjects.clear();
    }

    @SuppressWarnings("unchecked")
    public <T> T getRemote(Class<T> cls) {
        if (_remoteObjects.get(cls) == null) {
            try {
                InitialContext context = new InitialContext();
                _remoteObjects.put(cls, context.lookup(getUrl(cls)));
            } catch (NamingException e) {
                throw new InternalError();
            }
        }
        return (T) _remoteObjects.get(cls);
    }

    private <T> String getUrl(Class<T> cls) {
        String url = _remoteUrls.get(cls);
        if (url == null) {
            throw new InternalError();
        }
        return url;
    }
}
