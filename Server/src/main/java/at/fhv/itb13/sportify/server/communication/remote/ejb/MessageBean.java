package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.MessageController;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.MessageRemote;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.io.Serializable;

@Stateful
public class MessageBean implements MessageRemote {

    private MessageController _messageController;

    public MessageBean() {
        _messageController = new MessageController();
    }

    @Override
    public Serializable getMessage(String username) {
        return _messageController.getMessage(username);
    }

    @Override
    public void sendMessage(String username, Serializable objectMessage) {
        _messageController.sendMessage(username, objectMessage);
    }
}
