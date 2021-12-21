package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Client;
import beans.User;
import util.HibernateUtil;
public class IClientImplDAO implements IClientDAO{
	
	private static Session session = HibernateUtil.getSessionFactory().openSession();
	User user=new User();
	IUserImplDao userDao = new IUserImplDao();
	Connection conn=DAOFACTORY.getConnection();
	
public void saveUserId(int user_id) {
		
		Connection conexion=DAOFACTORY.getConnection();
		try {
			PreparedStatement ps  = conexion.prepareStatement("INSERT INTO client (User_id) VALUES(?)");
			ps.setInt(1, user_id);
			ps.executeUpdate();
			ps.close();
			
	     }
       catch (SQLException e) {
	       e.printStackTrace();
	        System.out.println("ewa hady erreur 5/5 ");
}
		}

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
    
    public Client getclient(User user) {
		// TODO Auto-generated method stub
		Client client=new Client();
		Connection conexion=DAOFACTORY.getConnection();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from client where User_id=? ");
			ps.setInt(1, user.getId());
			
			 ResultSet rs = ps.executeQuery();
				if(rs.next()){
					
					client.setId(Integer.parseInt(rs.getString("id")));
					client.setNom(rs.getString("nom"));
					client.setPrenom(rs.getString("prenom"));
					client.setAdresse(rs.getString("adresse"));
					client.setCodePostal(Integer.parseInt(rs.getString("codePostal")));
					client.setEmail(rs.getString("email"));
					client.setNationalite(rs.getString("nationalite"));
					client.setTelephone(Integer.parseInt(rs.getString("telephone")));
					
				}			
			ps.close();
			System.out.println("query executed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}
    public List<Client> getClientList() {
		ArrayList<Client> clients = new ArrayList<Client>();
		
		Connection connexion = DAOFACTORY.getConnection();
		try {
			PreparedStatement ps=connexion.prepareStatement("select * from client ;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client client=new Client();
				client.setId(Integer.parseInt(rs.getString("id")));
				client.setAdresse(rs.getString("adresse"));
			    client.setNationalite(rs.getString("nationalite"));
				client.setNom(rs.getString("nom"));
				client.setPrenom(rs.getString("prenom"));
				client.setTelephone(Integer.parseInt(rs.getString("telephone")));
				client.setCodePostal(Integer.parseInt(rs.getString("codePostal")));
				client.setEmail(rs.getString("email"));
				
				clients.add(client);
			}
			ps.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return clients;
	}
   
    	public Client getClientByUserId(int user_id) {
    		Connection conexion=DAOFACTORY.getConnection();
    		Client p=new Client();
    		try {
    			PreparedStatement ps = conexion.prepareStatement(
    					"select * from client where User_id=?");
    			ps.setInt(1, user_id);
    			 ResultSet rs = ps.executeQuery();
    				if(rs.next()){
    					p.setId(Integer.parseInt(rs.getString("id")));
    					p.setAdresse(rs.getString("adresse"));
    					p.setNationalite(rs.getString("nationalite"));
    					p.setNom(rs.getString("nom"));
    					p.setPrenom(rs.getString("prenom"));
    					p.setTelephone(Integer.parseInt(rs.getString("telephone")));
    					p.setCodePostal(Integer.parseInt(rs.getString("codePostal")));
    					p.setEmail(rs.getString("email"));
    					
    				}			
    			ps.close();
    			System.out.println("query executed");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return p;
    	}
    	public void updateClient(Client client) {
    		
    		session.beginTransaction();
    		session.update(client);
    		session.getTransaction().commit();
    		session.close();
    			
    	}
    	public Client getClientById(int id) {
    		
    		System.out.println("YOU ARE IN GETCLIENTBYID");
    		Client client= session.find(Client.class, id);
    		return client;
    	}
    	public int getIdUserByIdClient (int id) {
			Connection conexion=DAOFACTORY.getConnection();
			Client client = new Client();
			try {
				PreparedStatement ps = conexion.prepareStatement(
						"select User_id from client where id=? ");
				ps.setInt(1, id);
				
				 ResultSet rs = ps.executeQuery();
					if(rs.next()){
						client.setId(Integer.parseInt(rs.getString("User_id")));
					}			
				ps.close();
				System.out.println("query executed");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return client.getId();
		}
    	
    	
}
	

