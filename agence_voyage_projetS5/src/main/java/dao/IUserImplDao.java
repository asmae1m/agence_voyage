package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.lang.Object;


import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.*;

import util.HibernateUtil;
import org.hibernate.Query;


public class IUserImplDao implements IUserDao{
	private static Session session = HibernateUtil.getSessionFactory().openSession();

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the User object
            session.save(user);
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<User> getUserList(){
		 return session.createQuery("from user").list();
	}
	public User getUserById(int id) {
		User user = session.find(User.class, id);
		return user;
	}
	
	
	public void deleteUser(int id) {
		User user = getUserById(id);
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
   }
	
	public User getUser(String username, String password) {
		Connection conexion=DAOFACTORY.getConnection();
		User p=new User();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from user where username=? and password=? ");
			ps.setString(1, username);
			ps.setString(2, password);
			 ResultSet rs = ps.executeQuery();
				if(rs.next()){
					p.setId(Integer.parseInt(rs.getString("id")));
					p.setLogin(rs.getString("username"));
					p.setPassword(rs.getString("password"));
					p.setRole(Role.valueOf(rs.getString("role")));
				}			
			ps.close();
			System.out.println("query executed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void updateUser(User user) {
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
			
	}
	public boolean login(String username,String password) {
		boolean b=false;
		Connection conexion=DAOFACTORY.getConnection();
		User p=new User();
		try {
			PreparedStatement ps  = conexion.prepareStatement("SELECT * FROM user WHERE username=? ");
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			if(rs.next() && (rs.getString("password")).equals(password) ){
				System.out.println("ils sont egaux donc khassou i5dm");
				b=true;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	

		public int getUserId(String username, String password) {
			Connection conexion=DAOFACTORY.getConnection();
			User p=new User();
			try {
				PreparedStatement ps = conexion.prepareStatement(
						"select id from user where username=? and password=? ");
				ps.setString(1, username);
				ps.setString(2, password);
				 ResultSet rs = ps.executeQuery();
					if(rs.next()){
						p.setId(Integer.parseInt(rs.getString("id")));
					}			
				ps.close();
				System.out.println("query executed");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p.getId();
		}
		
		
}


