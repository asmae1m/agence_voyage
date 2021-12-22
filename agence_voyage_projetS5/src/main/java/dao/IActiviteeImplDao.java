package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import beans.Activite;
import beans.Theme;
import util.HibernateUtil;

public class IActiviteeImplDao implements IActiviteeDao {
	private static Session session ;
	public void setActivitee(Activite a) {
		// TODO Auto-generated method stub
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(a);
		session.getTransaction().commit();
		session.close();
	}
	public List<Activite> getActiviteById(int voyageId){
		
		Connection conexion=DAOFACTORY.getConnection();
		List<Activite> i=new ArrayList<Activite>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from activite where voyage_id=?");
			ps.setInt(1, voyageId);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Activite v =new Activite();
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
	public void deleteAct(int id) {
		Activite voyage  = getActivite(id);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(voyage);
		session.getTransaction().commit();
		session.close();
    }
	public Activite getActivite(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Activite voyage = session.find(Activite.class, id);
		session.close();
		return voyage;
	}
}