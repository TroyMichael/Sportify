package at.fhv.itb13.sportify;

import at.fhv.itb13.sportify.communicaton.servants.PersonServant;
import at.fhv.itb13.sportify.presentation.SportifyGUI;

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
            //NAMING mit client fixieren
            Naming.rebind("rmi://localhost/PersonServant", personServant);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
            //TODO handle exception
        }
    }
}