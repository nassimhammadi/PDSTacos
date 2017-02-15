package main.java.serv.dao;

public interface UserDAO {

	public boolean check(String login, String pwd) throws DAOException;
	
}
