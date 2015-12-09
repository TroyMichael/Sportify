package at.fhv.itb13.sportify.server;

import at.fhv.itb13.sportify.server.communication.remote.SessionFactoryImpl;
import at.fhv.itb13.sportify.shared.communication.remote.SessionFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Main {

//    public static void main(String[] args) {
//        try {
//            SessionFactory sessionFactory = new SessionFactoryImpl();
//            Naming.rebind("rmi://localhost:12345/SessionFactory", sessionFactory);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
}
