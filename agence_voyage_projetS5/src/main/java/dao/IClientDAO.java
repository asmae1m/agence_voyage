package dao;
import java.util.List;

import beans.*;

public interface IClientDAO {
	
	public void saveClient(Client client);
	public List<Client> getClientList();
	public void updateClient(Client client);

}
