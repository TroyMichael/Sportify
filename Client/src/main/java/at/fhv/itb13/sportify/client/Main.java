package at.fhv.itb13.sportify.client;

//import at.fhv.itb13.sportify.communication.servants.PersonServant;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;

/**
 * Created by Michael on 27.10.2015.
 */
public class Main {

    public static void main(String[] args) {
        SportifyGUI.run(args);
        System.out.println("Client started!");
//        try {
//            PersonServant personServant = new PersonServant();
//            //NAMING mit client fixieren
//            Naming.rebind("rmi://localhost/PersonServant", personServant);
//        } catch (RemoteException | MalformedURLException e) {
//            e.printStackTrace();
//            //TODO handle exception
//        }
    }
}