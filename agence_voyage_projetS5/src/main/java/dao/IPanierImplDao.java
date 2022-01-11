package dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import org.hibernate.Session;

import beans.Voyage;

public class IPanierImplDao {
	private static Session session ;
	public void ajout_pan(int idVoy,int idClient) {
		Connection conexion=DAOFACTORY.getConnection();
		try {
			System.out.println("bien entrer");
	        PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO reservation (etat_reservation, Voyage_id, client_id) VALUES (?, ?, ?);");
	        preparedStatement.setBoolean(1, false);
	        preparedStatement.setInt(2, idVoy);
	        preparedStatement.setInt(3, idClient);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void reserverVoy(int idVoy,int idClient) {
		Connection conexion=DAOFACTORY.getConnection();
		try {
			System.out.println("bien entrer");
	        PreparedStatement preparedStatement = conexion.prepareStatement("update reservation set etat_reservation=? where Voyage_id=? and client_id=?;");
	        preparedStatement.setBoolean(1, true);
	        preparedStatement.setInt(2, idVoy);
	        preparedStatement.setInt(3, idClient);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void suprimmer_pan(int idVoy,int idClient) {
		Connection conexion=DAOFACTORY.getConnection();
		try {
			System.out.println("bien entrer");
	        PreparedStatement preparedStatement = conexion.prepareStatement("delete from reservation where Voyage_id=? and client_id=?;");
	        preparedStatement.setInt(1, idVoy);
	        preparedStatement.setInt(2, idClient);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public ArrayList<Voyage> getPanier(int idClient){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> i=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select distinct s.* from voyage s, client c, reservation r where r.Voyage_id=s.id and r.client_id=? and etat_reservation=0");
			ps.setInt(1, idClient);
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					IHebergementImplDao h=new IHebergementImplDao();
					Voyage v =new Voyage();
					v.setId(rs.getInt("id"));
					v.setDestination(rs.getString("destination"));
					v.setDate_arrivee(rs.getString("date_arrivee"));
					v.setDate_depart(rs.getString("date_depart"));
					v.setPrix(rs.getFloat("prix"));
					v.setType_voyage(rs.getString("type_voyage"));
					v.setDuree(rs.getString("duree"));
					v.setEndroit_depart(rs.getString("endroit_depart"));
					v.setHebergements(h.getHebergementById(rs.getInt("id")));
					Blob blob = rs.getBlob("image");
	                 
	                InputStream inputStream = blob.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
	                 
	                byte[] imageBytes = outputStream.toByteArray();
	                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                v.setBase64Image(base64Image);
	                
	                inputStream.close();
	                outputStream.close();
					i.add(v);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	/*public void update_res(int idVoy,String heb) {
		Connection conexion=DAOFACTORY.getConnection();
		try {
			System.out.println("bien entrer");
	        PreparedStatement preparedStatement = conexion.prepareStatement("delete from hebergement where nom_heberement=? and voyage_id=? LIMIT 1;");
	        preparedStatement.setString(1,heb );
	        preparedStatement.setInt(2, idVoy);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}*/
	public void mod_pan(int idVoy,int id_client) {
		Connection conexion=DAOFACTORY.getConnection();
		try {
			PreparedStatement preparedStatement = conexion.prepareStatement("update reservation set etat_reservation=? where Voyage_id=? and client_id=?;");
	        preparedStatement.setBoolean(1, false);
	        preparedStatement.setInt(2, idVoy);
	        preparedStatement.setInt(3, id_client);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public ArrayList<Voyage> getVoyageReser(int idClient){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> i=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select distinct s.* from voyage s, client c, reservation r where r.Voyage_id=s.id and r.client_id=? and etat_reservation=1");
			ps.setInt(1, idClient);
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					IHebergementImplDao h=new IHebergementImplDao();
					Voyage v =new Voyage();
					v.setId(rs.getInt("id"));
					v.setDestination(rs.getString("destination"));
					v.setDate_arrivee(rs.getString("date_arrivee"));
					v.setDate_depart(rs.getString("date_depart"));
					v.setPrix(rs.getFloat("prix"));
					v.setType_voyage(rs.getString("type_voyage"));
					v.setDuree(rs.getString("duree"));
					v.setEndroit_depart(rs.getString("endroit_depart"));
					v.setHebergements(h.getHebergementById(rs.getInt("id")));
					Blob blob = rs.getBlob("image");
	                 
	                InputStream inputStream = blob.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                 
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
	                 
	                byte[] imageBytes = outputStream.toByteArray();
	                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	                v.setBase64Image(base64Image);
	                
	                inputStream.close();
	                outputStream.close();
					i.add(v);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public ArrayList<Voyage> getVoyageReserAdmin(){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> i=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT v.id AS id, v.destination AS voyage_destination, v.date_depart AS voyage_date, v.prix AS voyage_prix, c.nom AS client_nom FROM voyage v\r\n"
					+ "  INNER JOIN reservation r ON v.id = r.Voyage_id\r\n"
					+ "  INNER JOIN client c ON r.client_id = c.id"
					+ " where r.etat_reservation=1;");
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Voyage v =new Voyage();
					v.setId(rs.getInt("id"));
					v.setDestination(rs.getString("voyage_destination"));
					v.setDate_depart(rs.getString("voyage_date"));
					v.setPrix(Float.parseFloat(rs.getString("voyage_prix")));
					v.setEndroit_depart(rs.getString("client_nom"));
					i.add(v);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public int getClientId(String nom){
		Connection conexion=DAOFACTORY.getConnection();
		int id=0;
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT id from client where nom=?;");
			ps.setString(1, nom);
			
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					id=rs.getInt("id");
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}