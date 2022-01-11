package dao;

import org.hibernate.Session;



import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import beans.Activite;
import beans.Client;
import beans.Hebergement;
import beans.Role;
import beans.Theme;
import beans.User;
import beans.Voyage;
import dao.IVoyageDAO;
import util.HibernateUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyStore.Entry;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat; 

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
	
	public List <Voyage> getVoyageList(int id) {
		Connection conexion=DAOFACTORY.getConnection();
		List <Voyage> voyages = new ArrayList<Voyage>();
		Voyage i = new Voyage();
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
	                
	                voyages.add(i);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
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
	
	public void deleteOldTravels () throws ParseException{
		List <Voyage> listVoyages = getVoyageList();
		for (Voyage item:listVoyages) {
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(item.getDate_depart());  
			if (date1.before(new java.sql.Date(System.currentTimeMillis()))) {
				deleteVoyage(item.getId());
				
			}
		}
		
	}
	public List <Voyage> voyageParFiltre (String theme, String activite,String destination,String date_depart,String duree,String type){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select * from voyage v,theme t,activite a where v.id=t.voyage_id and v.id=a.voyage_id and t.nom=? and a.nom=? and v.destination=? and v.date_depart=? and v.duree=? and v.type_voyage=?");
			
			
			ps.setString(1, theme);
			ps.setString(2, activite);
			ps.setString(3, destination);
			ps.setString(4, date_depart);
			ps.setString(5, duree);
			ps.setString(6, type);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
                    Voyage voyage = new Voyage();
                    voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(date_depart);
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(duree);
					voyage.setDestination(destination);
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(type);
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
				}
			ps.close();
			System.out.println("listage FINI");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
	}
	
	public ArrayList<Voyage> getVoyagesClient(){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> i=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from voyage WHERE  DATE(date_depart) > DATE(NOW())");
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
	
	public List <Voyage> voyageParTheme (String theme){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select v.* from voyage v,theme t where v.id=t.voyage_id  and t.nom=?");
			
			ps.setString(1, theme);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
                    
					Voyage voyage = new Voyage();
					voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(rs.getString("date_depart"));
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(rs.getString("duree"));
					voyage.setDestination(rs.getString("destination"));
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(rs.getString("type_voyage"));
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
					
				}
			ps.close();
			System.out.println("listage FINI DE THEME	");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
	}
	
	public List <Voyage> voyageParActivite(String activite){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select v.* from voyage v,activite a where v.id=a.voyage_id and a.nom=? ");
			ps.setString(1, activite);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
					
					Voyage voyage = new Voyage();
					voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(rs.getString("date_depart"));
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(rs.getString("duree"));
					voyage.setDestination(rs.getString("destination"));
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(rs.getString("type_voyage"));
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
				}
			ps.close();
			System.out.println("listage FINI DE ACTIVITE");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
	}
	
	public List <Voyage> voyageParDateDepart(String date){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select v.* from voyage v where date_depart=? ");
			ps.setString(1, date);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
					
					Voyage voyage = new Voyage();
					voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(rs.getString("date_depart"));
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(rs.getString("duree"));
					voyage.setDestination(rs.getString("destination"));
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(rs.getString("type_voyage"));
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
				}
			ps.close();
			System.out.println("listage FINI DATE DEPART");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
	}
	
	public List <Voyage> voyageParDesti(String destination ){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		
		try {
			PreparedStatement ps = conexion.prepareStatement("select v.* from voyage v where destination=? ");
			ps.setString(1, destination);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
					
	                
					Voyage voyage = new Voyage();
				    voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(rs.getString("date_depart"));
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(rs.getString("duree"));
					voyage.setDestination(rs.getString("destination"));
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(rs.getString("type_voyage"));
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
				}
			ps.close();
			System.out.println("listage FINI PAR DESTINATION");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
	}
	public List <Voyage> voyageParDuree(String duree){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select v.* from voyage v where duree=? ");
			ps.setString(1, duree);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
					
					Voyage voyage = new Voyage();
					voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(rs.getString("date_depart"));
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(rs.getString("duree"));
					voyage.setDestination(rs.getString("destination"));
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(rs.getString("type_voyage"));
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
				}
			ps.close();
			System.out.println("listage FINI  DUREE");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return voyages;
	}
	
	public List <Voyage> voyageParType(String type){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> voyages=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement("select v.* from voyage v where type_voyage=?");
			ps.setString(1, type);
			
			 ResultSet rs = ps.executeQuery();
			 
				while(rs.next()){
					
	                
					Voyage voyage = new Voyage();
					voyage.setId(rs.getInt("id"));
					voyage.setDate_arrivee(rs.getString("date_arrivee"));
					voyage.setDate_depart(rs.getString("date_depart"));
					voyage.setEndroit_depart(rs.getString("endroit_depart"));
					voyage.setDuree(rs.getString("duree"));
					voyage.setDestination(rs.getString("destination"));
					voyage.setPrix(Float.parseFloat(rs.getString("prix")));
					voyage.setType_voyage(rs.getString("type_voyage"));
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
	                voyage.setBase64Image(base64Image);
	                 
	                inputStream.close();
	                outputStream.close();
	                
	                voyages.add(voyage);
				}
			ps.close();
			System.out.println("listage FINI TYPE");
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		return voyages;
	}
	public ArrayList<Voyage> getAll(){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Voyage> i=new ArrayList<Voyage>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from voyage");
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
       
     
       
       
     
	
    public static void main(String args[]) {
	 
    IVoyageImplDAO voygDao = new IVoyageImplDAO();
    Voyage vyg = new Voyage();
    vyg = voygDao.getVoyage(6);
    vyg.setPrix(80000);
    voygDao.updateVoyage(vyg);
    System.out.println("voyage updated");
    
   
}
}