package dao;


import java.util.List;

import beans.Activite;

public interface IActiviteeDao {
	
	public void setActivitee(Activite a);
	public List<Activite> getActiviteById(int voyageId);
	
	public int getActiviteId(String nom);
	public int getIdVoyDeAct(int id);
}