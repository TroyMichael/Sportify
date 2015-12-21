package at.fhv.itb13.sportify.client.communication;

import at.fhv.itb13.sportify.shared.communication.remote.SessionFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceLocator {

    private static Properties _props;
    private static ServiceLocator _instance;

    private Map<Class, Remote> _remoteObjects;
    private Map<Class, String> _remoteUrls;

    static {
        InputStream inputStream = null;
        try {
            String path = System.getProperty("user.dir") + "\\client.properties";
            File props = new File(path);
            if (props.exists()) {
                inputStream = new FileInputStream(props);
                System.out.println("Loading configuration file from " + path + "...");
            } else {
                inputStream = ServiceLocator.class.getClassLoader().getResourceAsStream("client.properties");
                System.out.println("Configuration file " + path + " not found!");
                System.out.println("Loading default configuration file...");
            }
            _props = new Properties();
            try {
                _props.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ServiceLocator() {
        // init map for remote objects
        _remoteObjects = new HashMap<Class, Remote>();

        // init map for remote urls
        _remoteUrls = new HashMap<Class, String>();
        // add remote urls to map
        _remoteUrls.put(SessionFactory.class, "SessionFactory");
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

    public <T extends Remote> T getRemote(Class<T> cls) throws RemoteException {
        if (_remoteObjects.get(cls) == null) {
            try {
                _remoteObjects.put(cls, Naming.lookup(getUrl(cls)));
            } catch (MalformedURLException | NotBoundException e) {
                throw new InternalError();
            } catch (RemoteException e) {
                reload();
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
        return getRMIServer() + url;
    }

    private String getRMIServer() {
        return "rmi://" + _props.getProperty("SERVER") + ":" + _props.getProperty("RMI_REGISTRY_PORT") + "/";
    }
}
