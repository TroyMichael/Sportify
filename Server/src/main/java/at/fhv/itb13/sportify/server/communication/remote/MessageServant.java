package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.MessageController;
import at.fhv.itb13.sportify.shared.communication.remote.MessageRemote;
import at.fhv.itb13.sportify.shared.communication.remote.Session;

import java.io.Serializable;
import java.rmi.RemoteException;

public class MessageServant extends SessionServant implements MessageRemote {

    private MessageController _messageController;

    protected MessageServant(Session session) throws RemoteException {
        super(session);
        _messageController = new MessageController();
    }


    @Override
    public Serializable getMessage(String username) throws RemoteException {
        return _messageController.getMessage(username);
    }

    @Override
    public void sendMessage(String username, Serializable objectMessage) throws RemoteException {
        _messageController.sendMessage(username, objectMessage);
    }
}
