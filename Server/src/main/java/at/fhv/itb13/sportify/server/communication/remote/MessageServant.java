package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.MessageController;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;
import at.fhv.itb13.sportify.shared.communication.remote.MessageRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by KYUSS on 29.11.2015.
 */
public class MessageServant extends UnicastRemoteObject implements MessageRemote {

    private MessageController _messageController;

    protected MessageServant() throws RemoteException {
        super();
        _messageController = new MessageController();
    }


    @Override
    public Serializable getMessage(String queueName) throws RemoteException {
        return _messageController.getMessage(queueName);
    }

    @Override
    public void sendMessage(PersonDTO queueName, Serializable objectMessage) throws RemoteException {
        _messageController.sendMessage(queueName, objectMessage);
    }
}
