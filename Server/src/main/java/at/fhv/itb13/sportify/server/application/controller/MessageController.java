package at.fhv.itb13.sportify.server.application.controller;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

/**
 * Created by Caroline on 28.11.2015.
 */
public class MessageController {

    public Serializable getMessage(String username) {
        Context context = null;
        try {
            context = new InitialContext();
            QueueConnectionFactory cf = (QueueConnectionFactory) context.lookup("jms/myConnectionFactory");
            Connection con = null;
            try {
                con = cf.createConnection();
                Destination dest = (Destination) context.lookup(getQueueName(username));

                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageConsumer cons = session.createConsumer(dest);
                con.start();
                ObjectMessage message = (ObjectMessage) cons.receive(500);
                if (message != null) {
                    return message.getObject();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void sendMessage(String username, Serializable messageDTO) {
        Context context = null;
        try {
            context = new InitialContext();
            QueueConnectionFactory cf = (QueueConnectionFactory) context.lookup("jms/myConnectionFactory");
            Connection con = null;
            try {
                con = cf.createConnection();
                Destination dest = (Destination) context.lookup(getQueueName(username));

                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
                ObjectMessage message = session.createObjectMessage();
                message.setObject(messageDTO);
                MessageProducer prod = session.createProducer(dest);
                prod.send(message);
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getQueueName(String username) {
        return "jms/queue/" + username + "Queue";
    }
}

