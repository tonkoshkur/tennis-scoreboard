package ua.tonkoshkur.tennis.common.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDao {
    protected void executeTransactional(Session session, Runnable runnable) throws HibernateException {
        Transaction transaction = session.beginTransaction();
        try {
            runnable.run();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new HibernateException(e);
        }
    }
}
