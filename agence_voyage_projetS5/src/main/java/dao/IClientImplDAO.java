package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Client;

public class IClientImplDAO{
	Connection conn=DAOFACTORY.getConnection();
	
	Client client = new Client();
	
}
