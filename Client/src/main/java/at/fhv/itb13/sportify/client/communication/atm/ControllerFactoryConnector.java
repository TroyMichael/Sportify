package at.fhv.itb13.sportify.client.communication.atm;

import at.fhv.itb13.sportify.shared.communication.atm.ControllerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Created by Niklas Fessler on 30.10.2015.
 */
public class ControllerFactoryConnector {
    private static ControllerFactoryConnector _instance;
    private static ControllerFactory _controllerFactory;

    private ControllerFactoryConnector(){}

    public static ControllerFactoryConnector loadControllerFactoryConnector(){
        if (_instance == null){
            _instance = new ControllerFactoryConnector();

            //load security manager
            System.setProperty("java.security.policy", "policy.all");
            System.setSecurityManager(new SecurityManager());

            //Read properties file
            System.out.println("Reading Configuration file");
            Properties rmiConfig = new Properties();

            BufferedInputStream stream;
            try {
                stream = new BufferedInputStream(new FileInputStream("rmiConfig.properties"));
                rmiConfig.load(stream);
                stream.close();
            } catch (IOException e) {
                System.out.println("Could not read Server configuration");
            }

            String rmiLocation = rmiConfig.getProperty("location");
            int port = Integer.parseInt(rmiConfig.getProperty("port"));

            try {
                _controllerFactory = (ControllerFactory) Naming.lookup(rmiLocation + ":" + port + "/ControllerFactory");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return _instance;
    }

    public ControllerFactory getControllerFactory() {
        return _controllerFactory;
    }
}
