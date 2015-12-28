package at.fhv.itb13.sportify.server.communication.remote.ejb;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

@Startup
@Singleton
@Local
public class RMIBean {

    public RMIBean() {
//        try {
//            SessionFactory sessionFactory = new SessionFactoryImpl();
////            Naming.rebind("rmi://localhost:12345/SessionFactory", sessionFactory);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }
}
