package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Client;
import beans.User;
import util.HibernateUtil;

public class IClientImplDAO implements IClientDAO{

    public void saveClient(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the User object
            session.save(client);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	
}
