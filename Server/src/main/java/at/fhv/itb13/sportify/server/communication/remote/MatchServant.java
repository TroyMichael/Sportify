package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.MatchController;
import at.fhv.itb13.sportify.shared.communication.dtos.MatchDTO;
import at.fhv.itb13.sportify.shared.communication.remote.MatchRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatchServant extends UnicastRemoteObject implements MatchRemote {

    private MatchController _matchController;

    protected MatchServant() throws RemoteException {
        super();
        _matchController = new MatchController();
    }

    @Override
    public void create(MatchDTO matchDto) throws RemoteException {
        _matchController.create(matchDto);
    }
}
