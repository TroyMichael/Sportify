package at.fhv.itb13.sportify.server;

import at.fhv.itb13.sportify.server.communication.remote.ControllerFactoryImpl;
import at.fhv.itb13.sportify.shared.communication.remote.ControllerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by Michael on 27.10.2015.
 */
public class Main {

    public static void main(String[] args) {
       try {
            ControllerFactory controllerFactory = ControllerFactoryImpl.getInstance();
            Naming.rebind("rmi://localhost:12345/ControllerFactory", controllerFactory);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        SportifyGUI.run(args);
//        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        DBFacade dbFacade = (DBFacade) context.getBean("dbFacade");
//        System.out.println(dbFacade);


    }
}
