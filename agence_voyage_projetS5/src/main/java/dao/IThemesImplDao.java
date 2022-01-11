package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import beans.Theme;
import beans.Voyage;
import util.HibernateUtil;

public class IThemesImplDao implements IThemesDao {

	private static Session session;
	@Override
	public void setTheme(Theme t) {
		// TODO Auto-generated method stub
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();
	}
	public ArrayList<Theme> getThemesById(int voyageId){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Theme> i=new ArrayList<Theme>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from theme where voyage_id=?");
			ps.setInt(1, voyageId);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Theme v =new Theme();
					v.setId(rs.getInt("id"));
					v.setNom(rs.getString("nom"));
					i.add(v);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public void deleteTheme(int id) {
		Theme voyage  = getTheme(id);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(voyage);
		session.getTransaction().commit();
		session.close();
    }
	public Theme getTheme(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Theme voyage = session.find(Theme.class, id);
		session.close();
		return voyage;
	}
	public int getIdVoy (int id) {
		Connection conexion=DAOFACTORY.getConnection();
	    Theme theme = new Theme();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select voyage_id from theme where id=?");
			ps.setInt(1, id);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					
					theme.setId(rs.getInt("voyage_id"));
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theme.getId();
	}
	public int getThemeId(String nom) {
		Theme theme = new Theme();
		Connection conexion=DAOFACTORY.getConnection();
		   
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select id from theme where nom=?");
			ps.setString(1, nom);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					
					theme.setId(rs.getInt("id"));
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theme.getId();
	}
	
	public List <Theme> getThemeIdByVoy(int voyage_id) {
		
		List <Theme> themes = new ArrayList<Theme>();
		Connection conexion=DAOFACTORY.getConnection();
		   
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select t.* from voyage v,theme t where v.id=t.voyage_id and v.id=?;");
			ps.setInt(1, voyage_id);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Theme theme = new Theme();
					theme.setId(rs.getInt("id"));
					theme.setNom(rs.getString("nom"));
					
					themes.add(theme);
					
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return themes;
	}
		
}
	
	
