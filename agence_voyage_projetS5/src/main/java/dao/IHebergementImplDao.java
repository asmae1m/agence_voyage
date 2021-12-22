package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.hibernate.Session;

import beans.Hebergement;
import beans.Theme;
import util.HibernateUtil;

public class IHebergementImplDao implements IHebergementDao {
	private static Session session ;
	@Override
	public void setHeber(Hebergement h) {
		// TODO Auto-generated method stub
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(h);
		session.getTransaction().commit();
		session.close();
	}
	public ArrayList<Hebergement> getHebergementById(int voyageId){
		Connection conexion=DAOFACTORY.getConnection();
		ArrayList<Hebergement> i=new ArrayList<Hebergement>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select * from hebergement where voyage_id=?");
			ps.setInt(1, voyageId);
			 ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Hebergement v =new Hebergement();
					v.setId(rs.getInt("id"));
					v.setNom_hebergement(rs.getString("nom_heberement"));
					i.add(v);
				}			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public void deleteHeber(int id) {
		Hebergement voyage  = getHeber(id);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(voyage);
		session.getTransaction().commit();
		session.close();
    }
	public Hebergement getHeber(int id) {
		session = HibernateUtil.getSessionFactory().openSession();
		Hebergement voyage = session.find(Hebergement.class, id);
		session.close();
		return voyage;
	}
}