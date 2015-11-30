package at.fhv.itb13.sportify.server.application.controller;

import at.fhv.itb13.sportify.server.communication.datatransfer.mapper.PersonMapper;
import at.fhv.itb13.sportify.server.model.Person;
import at.fhv.itb13.sportify.shared.communication.dtos.PersonDTO;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by Caroline on 28.11.2015.
 */
public class MessageController {

    public Serializable getMessage(String queueName) {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.setProperty(Context.PROVIDER_URL, "tcp://52.28.97.28:61616");
        env.setProperty("queue." + queueName, queueName);

        Context context = null;
        ObjectMessage message = null;

        try {
            context = new InitialContext(env);
            QueueConnectionFactory cf = (QueueConnectionFactory) context.lookup("ConnectionFactory");
            Connection con = null;
            try {
                con = cf.createConnection();
                Destination dest = (Destination) context.lookup(queueName);

                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageConsumer cons = session.createConsumer(dest);
                con.start();
                message = (ObjectMessage) cons.receive();
                //   System.out.println(message.getText());
                return message.getObject();
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

    public void sendMessage(PersonDTO personDTO, Serializable messageDTO) {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.setProperty(Context.PROVIDER_URL, "tcp://52.28.97.28:61616");
        Person person = new PersonMapper().toDomainObject(personDTO);
        String queueName = person.getUser().getUsername() + "Queue";
        env.setProperty("queue." + queueName, queueName);

        Context context = null;
        try {
            context = new InitialContext(env);
            QueueConnectionFactory cf = (QueueConnectionFactory) context.lookup("ConnectionFactory");
            Connection con = null;
            try {
                con = cf.createConnection();
                Destination dest = (Destination) context.lookup(queueName);

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
}

