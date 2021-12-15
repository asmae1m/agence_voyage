package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Client;
import beans.User;
import util.HibernateUtil;

public class IClientImplDAO implements IClientDAO{
	
	User user=new User();
	IUserImplDao userDao = new IUserImplDao();
	
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
try {
	        Connection conexion=DAOFACTORY.getConnection();
			PreparedStatement ps= conexion.prepareStatement("INSERT INTO client (adresse,nationalite,nom,prenom,telephone,User_id,codePostal,email) VALUES(?,?,?,?,?,?,?,?)");
			
		    ps.setString(1, client.getAdresse());
			System.out.println("this is the name"+ client.getAdresse());
			ps.setString(2, client.getNationalite());
			ps.setString(3, client.getNom());
			ps.setString(4, client.getPrenom());
			ps.setInt(5, client.getTelephone());
			ps.setInt(6, userDao.getId(user));
			System.out.println("ID USER EST : "+user.getId());
			ps.setInt(7, client.getCodePostal());
			ps.setString(8,client.getEmail());
			
			ps.executeUpdate();
			ps.close();
		
		}
		catch (SQLException e) {
			 e.printStackTrace();
			 
		}
	}
    
    public Client client(User user) {
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
   
    
      
    }
	

