package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import code.JDBC;

public class JDBCTest {
	
	JDBC c = new JDBC("jdbc:mysql://localhost/db_ticketBook", "root", "password");

	@Test
	public void constructorTest() {
		assertNotNull(c);		
	}
	@Test
	public void getdB_URLTest() {
		assertEquals("The database url get has failed",c.getdB_URL(),"jdbc:mysql://localhost/db_ticketBook");	
	}
	@Test
	public void setdB_URLTest() {
		c.setdB_URL("url");
		assertEquals("The database url set has failed",c.getdB_URL(),"url");	
	}
	@Test
	public void getUsernameTest() {
		assertEquals("The database url get has failed",c.getUsername(),"root");	
	}
	@Test
	public void setusernameTest() {
		c.setUsername("public");
		assertEquals("The database url set has failed",c.getUsername(),"public");	
	}
	@Test
	public void getPasswordTest() {
		assertEquals("The database url get has failed",c.getPassword(),"password");	
	}
	@Test
	public void setPasswordTest() {
		c.setPassword("password1");
		assertEquals("The database url set has failed",c.getPassword(),"password1");	
	}
	@Test
	public void InsertTest() {
		c.Connect();
		c.Create("tbl_band", "bandName, bandDescription", "'A good band name','A good band description'");
		ArrayList<String> returnData = new ArrayList<>();
		returnData.add("A good band name,A good band description");
		assertEquals("Reading/returning data from the database",returnData.get(0),c.Read("tbl_band", "bandName, bandDescription").get(3));	
	}
	@Test
	public void UpdateTest() {
		c.Connect();
		c.Update("tbl_band", "bandName", "A exceptional band name", "bandIDPK", "2");
		System.out.println(c.Read("tbl_band", "bandName, bandDescription").get(0).toString());
		assertEquals("Reading/returning data from the database",c.Read("tbl_band", "bandName, bandDescription").get(0).toString(),"A exceptional band name,A good band description");
	}
	@Test
	public void DeleteTest() {
		c.Connect();
		c.Delete("tbl_band", "bandIDPK", "1");
	}

}
