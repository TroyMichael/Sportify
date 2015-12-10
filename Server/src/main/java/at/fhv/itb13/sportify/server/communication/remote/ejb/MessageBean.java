package at.fhv.itb13.sportify.server.communication.remote.ejb;

import at.fhv.itb13.sportify.server.application.controller.MessageController;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.MessageRemote;
import at.fhv.itb13.sportify.shared.communication.remote.ejb.SessionRemote;

import javax.ejb.Stateful;
import java.io.Serializable;

@Stateful
public class MessageBean implements MessageRemote {

    private SessionRemote _session;
    private MessageController _messageController;

    public MessageBean() {
        _messageController = new MessageController();
    }

    public void setSession(SessionRemote session) {
        _session = session;
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
