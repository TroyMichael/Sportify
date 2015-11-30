package at.fhv.itb13.sportify.server.application.controller;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by Caroline on 28.11.2015.
 *
 */
public class MessageController {

    public void getMessage(String queueName) {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.setProperty(Context.PROVIDER_URL, "tcp://52.28.97.28:61616");
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
                MessageConsumer cons = session.createConsumer(dest);
                con.start();
                TextMessage message = (TextMessage) cons.receive();
                System.out.println(message.getText());
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

    public void sendMessage(String queueName, ObjectMessage message) {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.setProperty(Context.PROVIDER_URL, "tcp://52.28.97.28:61616");
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

