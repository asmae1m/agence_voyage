package dao;

import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import beans.Activite;
import beans.Hebergement;
import beans.Role;
import beans.Theme;
import beans.User;
import beans.Voyage;
import dao.IVoyageDAO;
import util.HibernateUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class IVoyageImplDAO implements IVoyageDAO{
	private static Session session ;
	
	public void saveVoyage(Voyage voyage) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(voyage);
		session.getTransaction().commit();
		session.close();
	}
    public int getIdVoy(Voyage v) {
    	Connection conexion=DAOFACTORY.getConnection();
		int i=0;
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select id from voyage where date_arrivee=? and destination=? and date_depart=? and endroit_depart=?");
			ps.setString(1, v.getDate_arrivee());
			ps.setString(2, v.getDestination());
			ps.setString(3, v.getDate_depart());
			ps.setString(4, v.getEndroit_depart());
			 ResultSet rs = ps.executeQuery();
				if(rs.next()){
					i = Integer.parseInt(rs.getString("id"));
					System.out.println(i);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
    }
	
	public ArrayList<Voyage> getVoyageList(){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> i=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from voyage ");
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Voyage v =new Voyage();
					v.setId(rs.getInt("id"));
					v.setDestination(rs.getString("destination"));
					v.setDate_arrivee(rs.getString("date_arrivee"));
					v.setDate_depart(rs.getString("date_depart"));
					v.setPrix(rs.getFloat("prix"));
					v.setType_voyage(rs.getString("type_voyage"));
					v.setDuree(rs.getString("duree"));
					v.setEndroit_depart(rs.getString("endroit_depart"));
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
	public Voyage getVoyage(int id) {
		Connection conexion=DAOFACTORY.getConnection();
		Voyage i=new Voyage();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from voyage where id=?");
			ps.setInt(1, id);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					i.setId(rs.getInt("id"));
					i.setDestination(rs.getString("destination"));
					i.setEndroit_depart(rs.getString("endroit_depart"));
					i.setDate_arrivee(rs.getString("date_arrivee"));
					i.setDate_depart(rs.getString("date_depart"));
					i.setDuree(rs.getString("duree"));
					i.setPrix(rs.getFloat("prix"));
					i.setType_voyage(rs.getString("type_voyage"));
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
	                i.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public void deleteVoyage(int id) {
		Voyage voyage  = getVoyage(id);
		IThemesImplDao t=new IThemesImplDao();
		List <Theme> the= t.getThemesById(id);
		IActiviteeImplDao a=new IActiviteeImplDao();
		List <Activite> act= a.getActiviteById(id);
		IHebergementImplDao Heb= new IHebergementImplDao();
		List <Hebergement> heb= Heb.getHebergementById(id);
		
		for(int i=0;i<the.size();i++) {
			
			t.deleteTheme(the.get(i).getId());
			
		}
		for(int i=0;i<act.size();i++) {
			
			a.deleteAct(act.get(i).getId());
			
		}
		for(int i=0;i<heb.size();i++) {
			
			Heb.deleteHeber(heb.get(i).getId());
			
		}
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(voyage);
		session.getTransaction().commit();
		session.close();
    }
	
	
	public void updateVoyage(Voyage voyage) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(voyage);
		session.getTransaction().commit();
		session.close();
	}
      public static void main(String args[]) {
	 
    IVoyageImplDAO voygDao = new IVoyageImplDAO();
    Voyage vyg = new Voyage();
    vyg = voygDao.getVoyage(6);
    vyg.setPrix(80000);
    voygDao.updateVoyage(vyg);
    System.out.println("voyage updated");
    
   
}
}