package at.fhv.itb13.sportify;

import at.fhv.itb13.sportify.communication.remote.PersonServant;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by Michael on 27.10.2015.
 */
public class Main {

    public static void main(String[] args) {
        try {
            PersonServant personServant = new PersonServant();
            Naming.rebind("rmi://localhost:12345/PersonServant", personServant);
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
