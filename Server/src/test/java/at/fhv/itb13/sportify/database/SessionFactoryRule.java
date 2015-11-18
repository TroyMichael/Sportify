package at.fhv.itb13.sportify.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class SessionFactoryRule implements MethodRule {

    private SessionFactory _sessionFactory;
    private Session _session;

    public SessionFactoryRule() {
        _sessionFactory = buildSessionFactory();
    }

    private SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public Statement apply(Statement statement, FrameworkMethod method, Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                _session = _sessionFactory.openSession();
                try {
                    statement.evaluate();
                } finally {
                    _session.close();
                    _sessionFactory.close();
                }
            }
        };
    }

    public Session getSession() {
        return _session;
    }

    public void beginTransaction() {
        _session.beginTransaction();
    }

    public void commitTransaction() {
        _session.getTransaction().commit();
        _session.clear();
    }

    public void rollbackTransaction() {
        _session.getTransaction().rollback();
    }
}


