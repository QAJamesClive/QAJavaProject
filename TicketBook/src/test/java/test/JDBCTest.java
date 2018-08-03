package test;


import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import code.JDBC;

public class JDBCTest {
	
	JDBC c = new JDBC("jdbc:mysql://localhost/db_ticketBook", "root", "password");
	static JSONObject TestJSon;
	static JSONObject TestUpdateJSon;
	
	@BeforeAll
	public static void PriorRequirements() {
		TestJSon = new JSONObject();
		TestJSon.put("bandName", "A good band name");
		TestJSon.put("bandDescription", "A good band description");
		TestUpdateJSon = new JSONObject();
		TestUpdateJSon.put("bandName", "A exceptional band name");
		TestUpdateJSon.put("bandDescription", "A good band description");
	}
	@Test
	public void constructorTest() {
		Assertions.assertNotNull(c);		
	}
	@Test
	public void getdB_URLTest() {
		Assertions.assertEquals(c.getdB_URL(),"jdbc:mysql://localhost/db_ticketBook","The database url get has failed");	
	}
	@Test
	public void setdB_URLTest() {
		c.setdB_URL("url");
		Assertions.assertEquals(c.getdB_URL(),"url","The database url set has failed");	
	}
	@Test
	public void getUsernameTest() {
		Assertions.assertEquals(c.getUsername(),"root","The database url get has failed");	
	}
	@Test
	public void setusernameTest() {
		c.setUsername("public");
		Assertions.assertEquals(c.getUsername(),"public","The database url set has failed");	
	}
	@Test
	public void getPasswordTest() {
		Assertions.assertEquals(c.getPassword(),"password","The database url get has failed");	
	}
	@Test
	public void setPasswordTest() {
		c.setPassword("password1");
		Assertions.assertEquals(c.getPassword(),"password1","The database url set has failed");	
	}
	@Test
	public void InsertTest() {
		c.Connect();
		c.Create("tbl_band", "bandName, bandDescription", "'A good band name','A good band description'");
		JSONObject js = (JSONObject) c.Read("tbl_band", "bandName, bandDescription").get("2");
		Assertions.assertEquals(TestJSon.get("bandName"),js.get("bandName"),"Reading/returning data from the database");	
	}
	@Test
	public void UpdateTest() {
		c.Connect();
		c.Update("tbl_band", "bandName", "A exceptional band name", "bandIDPK", "2");
		JSONObject js = (JSONObject) (c.Read("tbl_band", "bandName, bandDescription").get("1"));
		System.out.print(js);
		Assertions.assertEquals(TestUpdateJSon.get("bandName"),js.get("bandName"),"Update data in the database");
	}
	@Test
	public void DeleteTest() {
		c.Connect();
		c.Delete("tbl_band", "bandIDPK", "1");
	}

}
