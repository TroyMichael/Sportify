package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.SportController;
import at.fhv.itb13.sportify.shared.communication.dtos.SportDTO;
import at.fhv.itb13.sportify.shared.communication.remote.SportRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class SportServant extends UnicastRemoteObject implements SportRemote {

    private SportController _spoSportController;

    public SportServant() throws RemoteException {
        super();
        _spoSportController = new SportController();
    }

    @Override
    public List<SportDTO> getSports() throws RemoteException{
        return _spoSportController.getSports();
    }
}
