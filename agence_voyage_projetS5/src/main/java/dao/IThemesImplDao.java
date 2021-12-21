package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.hibernate.Session;

import beans.Theme;
import beans.Voyage;
import util.HibernateUtil;

public class IThemesImplDao implements IThemesDao {

	private static Session session = HibernateUtil.getSessionFactory().openSession();
	@Override
	public void setTheme(Theme t) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
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
		session.beginTransaction();
		session.delete(voyage);
		session.getTransaction().commit();
    }
	public Theme getTheme(int id) {
		Theme voyage = session.find(Theme.class, id);
		return voyage;
	}
}