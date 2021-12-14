package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import beans.Voyage;
import dao.IVoyageDAO;
import util.HibernateUtil;

import java.util.List;

public class IVoyageImplDAO implements IVoyageDAO{
	private static Session session = HibernateUtil.getSessionFactory().openSession();
	
	public void saveVoyage(Voyage voyage) {
		session.beginTransaction();
		session.save(voyage);
		session.getTransaction().commit();
	}
    
	
	public List<Voyage> getVoyageList(){
		 return session.createQuery("from voyage").list();
	}
	public Voyage getVoyage(int id) {
		Voyage voyage = session.find(Voyage.class, id);
		return voyage;
	}
	
	public void deleteVoyage(int id) {
		Voyage voyage  = getVoyage(id);
		session.beginTransaction();
		session.delete(voyage);
		session.getTransaction().commit();
    }
	
	
	public void updateVoyage(Voyage voyage) {
		session.beginTransaction();
		session.update(voyage);
		session.getTransaction().commit();
			
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
