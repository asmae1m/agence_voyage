package dao;

import java.util.List;

import beans.*;

public interface IUserDao {
	
	public void saveUser(User user);
	public List<User> getUserList();
	public User getUserById(int id);
	public User getUser(String username,String password);
	public void deleteUser(int id);
	public void updateUser(User user);
	boolean login(String username, String password);
	

}
