package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.PersonController;
import at.fhv.itb13.sportify.shared.communication.remote.UserRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by mod on 11/5/15.
 */
//TODO : Uncomment it all
public class UserServant extends UnicastRemoteObject implements UserRemote {

   // private LoginController _logincontroller;
    public UserServant() throws RemoteException {
        super();
      //  _logincontroller = new LoginController();
    }
    @Override
    public void login() {
        //TODO: Implement
        //_logincontroller.login();
    }
}
