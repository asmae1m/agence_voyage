package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import beans.*;

public interface IVoyageDAO {
	
	public void saveVoyage(Voyage voyage);
	public List<Voyage> getVoyageList();
	public Voyage getVoyage(int id);
	public void updateVoyage(Voyage voyage);
	public void deleteVoyage(int id);
	
	
	
	
	
	

}
