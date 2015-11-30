package at.fhv.itb13.sportify.server.communication.remote;

import at.fhv.itb13.sportify.server.application.controller.MessageController;
import at.fhv.itb13.sportify.shared.communication.remote.MessageRemote;

import javax.jms.ObjectMessage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by KYUSS on 29.11.2015.
 */
public class MessageServant extends UnicastRemoteObject implements MessageRemote {

    private MessageController _messageController;

    protected MessageServant () throws RemoteException {
        super();
        _messageController = new MessageController();
    }


    @Override
    public void getMessage(String queueName) throws RemoteException {
        _messageController.getMessage(queueName);
    }

    @Override
    public void sendMessage(String queueName, ObjectMessage objectMessage) throws RemoteException {
        _messageController.sendMessage(queueName, objectMessage);
    }
}
