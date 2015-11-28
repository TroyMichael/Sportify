package at.fhv.itb13.sportify.server.communication.jms;

import org.junit.Test;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class QueueConsumerTest {

    public void getMessage() {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.setProperty(Context.PROVIDER_URL, "tcp://52.28.97.28:61616");
        env.setProperty("queue.PSC6365Queue", "PSC6365Queue");

        Context context = null;
        try {
            context = new InitialContext(env);
            QueueConnectionFactory cf = (QueueConnectionFactory) context.lookup("ConnectionFactory");
            Connection con = null;
            try {
                con = cf.createConnection();
                Destination dest = (Destination) context.lookup("PSC6365Queue");

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
}
