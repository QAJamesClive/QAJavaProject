package test;

import static org.junit.Assert.*;

import org.junit.Test;

import code.JDBCConnection;

public class JDBCConnectionTest {

	@Test
	public void constructorTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		assertNotNull(c);		
	}
	@Test
	public void getdB_URLTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		assertEquals("The database url get has failed",c.getdB_URL(),"jdbc:mysql://localhost/db_ticketBook");	
	}
	@Test
	public void setdB_URLTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		c.setdB_URL("url");
		assertEquals("The database url set has failed",c.getdB_URL(),"url");	
	}
	@Test
	public void getUsernameTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		assertEquals("The database url get has failed",c.getUsername(),"root");	
	}
	@Test
	public void setusernameTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		c.setUsername("public");
		assertEquals("The database url set has failed",c.getUsername(),"public");	
	}
	@Test
	public void getPasswordTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		assertEquals("The database url get has failed",c.getPassword(),"password");	
	}
	@Test
	public void setPasswordTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		c.setPassword("password1");
		assertEquals("The database url set has failed",c.getPassword(),"password1");	
	}
	@Test
	public void ConnectionTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		c.Connect();
	}
	@Test 
	public void ReadTest() {
		JDBCConnection c = new JDBCConnection("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		
	}

}
