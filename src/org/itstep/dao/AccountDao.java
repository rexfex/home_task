package org.itstep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.itstep.dao.DBConnection;
import org.itstep.model.Account;

public class AccountDao {
	public void save(Account account) {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "INSERT INTO accounts(login,first_name,second_name,password) VALUES(?,?,?,?)";
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, account.getLogin());
			statement.setString(2, account.getFistName());
			statement.setString(3, account.getSecondName());
			statement.setString(4, account.getPassword());
			statement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public Account get(String login) {

		Account account = new Account();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM accounts WHERE login=?";

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				account.setLogin(resultSet.getString("login"));
				account.setFistName(resultSet.getString("first_name"));
				account.setSecondName(resultSet.getString("second_name"));
				account.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return account;
	}
	public void update(String login, Account newAccount) {
		Connection connection = null;
		PreparedStatement statement = null;		
		String sql = "UPDATE accounts SET first_name=?, second_name=?, login=?, password=? WHERE login=?";		
		
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(5, login);			
					
			statement.setString(1, newAccount.getFistName());			
			statement.setString(2, newAccount.getSecondName());
			statement.setString(3, newAccount.getLogin());
			statement.setString(4, newAccount.getPassword());
					
			statement.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
	public void delete(String login) {
    	Connection connection = null;
 		PreparedStatement statement = null;
		String sql = "DELETE FROM accounts WHERE login=?";		
		
		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);			
			
			statement.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
				
	}

	
}
