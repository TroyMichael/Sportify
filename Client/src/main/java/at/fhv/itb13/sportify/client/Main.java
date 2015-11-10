package at.fhv.itb13.sportify.client;

//import at.fhv.itb13.sportify.communication.servants.PersonServant;

import at.fhv.itb13.sportify.client.presentation.SportifyGUI;
import at.fhv.itb13.sportify.shared.communication.remote.ControllerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Michael on 27.10.2015.
 */
public class Main {

    public static void main(String[] args) {

        SportifyGUI.run(args);

    }
}