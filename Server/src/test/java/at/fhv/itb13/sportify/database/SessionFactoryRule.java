package at.fhv.itb13.sportify.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class SessionFactoryRule implements MethodRule {

    private static SessionFactory _sessionFactory;

    private Session _session;
    private Transaction _transaction;

    @Override
    public Statement apply(Statement statement, FrameworkMethod method, Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                _session = getSessionFactory().openSession();
                _transaction = _session.beginTransaction();

                try {
                    statement.evaluate();
                } finally {
                    _transaction.rollback();
                    _session.close();
                }
            }
        };
    }

    private SessionFactory getSessionFactory() {
        if (_sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            _sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return _sessionFactory;
    }

    public Session getSession() {
        return _session;
    }

    public void commit() {
        _transaction.commit();
    }
}


