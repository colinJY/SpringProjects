package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDao {
	
	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection connection = connectionMaker.makeNewConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME, PASSWORD) VALUES(?, ?, ?)");
		pStatement.setString(1, user.getId());
		pStatement.setString(2, user.getName());
		pStatement.setString(3, user.getPassword());
		
		pStatement.executeUpdate();
		
		pStatement.close();
		connection.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection connection = connectionMaker.makeNewConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
		pStatement.setString(1, id);
		
		ResultSet rs = pStatement.executeQuery();
		
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		pStatement.close();
		connection.close();
		
		return user;
		
	}

}
