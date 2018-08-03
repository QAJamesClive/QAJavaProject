package code;

import java.sql.*;
import java.util.ArrayList;

import org.json.JSONML;
import org.json.JSONObject;


public final class JDBC {

	private String dB_URL;
	private String username;
	private String password;
	private Connection conn;
	private Statement stmt;
	
	private ArrayList<String> returnedList;
	
	public JDBC(String DB_URL,String Username,String Password){
		this.dB_URL = DB_URL;
		this.username = Username;
		this.password = Password;
	}

	public String getdB_URL() {
		return dB_URL;
	}

	public void setdB_URL(String dB_URL) {
		this.dB_URL = dB_URL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void Connect() {
		conn= null;
		stmt= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
		try {
			conn = DriverManager.getConnection(dB_URL+"?useSSL=false", username, password);
		} catch (SQLException e) {
			System.out.println("Failed database connection creation");
		}
	}
	
	public void Create(String table, String columnList, String valuesList) {
		
		try {
			stmt = this.conn.createStatement();
		} catch (SQLException e) {
		}
		String sql= "INSERT INTO "+table +" ("+ columnList+") VALUES ("+valuesList+")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Failed database row insert");
		}
	}
	
	public 	JSONObject Read(String table,String columnString) {
		
		returnedList = new ArrayList<String>();
		JSONObject returnedJSon = new JSONObject();
		int rowNumber = 0;
		
		int columnCount = 0;
		
		try {
			stmt= conn.createStatement();
		} catch (SQLException e1) {

		}
		for ( int i = 0; i < columnString.length(); i++ ) {
			if(columnString.charAt(i) == ',') {
				columnCount++;
			}
        }
		columnCount++;
		String sql= "SELECT "+columnString+" FROM "+table;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Failed database read");
		}
		ResultSetMetaData rsmd = null;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e1) {
		}
		try {
			while(rs.next()) {
				JSONObject rowJSon = new JSONObject();
				for(int i = 0; i < columnCount;i++) {
					rowJSon.put(rsmd.getColumnName(i+1), rs.getString(i+1));
					rowNumber = rs.getRow();
				}
				returnedJSon.put(Integer.toString(rowNumber), rowJSon);
			}
		} catch (SQLException e) {
		}
		try {rs.close();
		} catch (SQLException e) {

		}
		return returnedJSon;
	}
	public 	JSONObject Read(String table,String columnString,String refColumn,String ref) {
		
		returnedList = new ArrayList<String>();
		JSONObject returnedJSon = new JSONObject();
		int rowNumber = 0;
		
		int columnCount = 0;
		
		try {
			stmt= conn.createStatement();
		} catch (SQLException e1) {

		}
		for ( int i = 0; i < columnString.length(); i++ ) {
			if(columnString.charAt(i) == ',') {
				columnCount++;
			}
        }
		columnCount++;
		String sql= "SELECT "+columnString+" FROM "+table+" WHERE "+refColumn+ " = ("+ ref +")";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Failed database read");
		}
		ResultSetMetaData rsmd = null;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e1) {
		}
		try {
			while(rs.next()) {
				JSONObject rowJSon = new JSONObject();
				for(int i = 0; i < columnCount;i++) {
					rowJSon.put(rsmd.getColumnName(i+1), rs.getString(i+1));
					rowNumber = rs.getRow();
				}
				returnedJSon.put(Integer.toString(rowNumber), rowJSon);
			}
		} catch (SQLException e) {
		}
		try {rs.close();
		} catch (SQLException e) {

		}
		return returnedJSon;
	}
	
public void Update(String table,String coulmn,String value,String refColumn,String ref) {
		
		try {
			stmt= conn.createStatement();
		} catch (SQLException e) {

		}
		String sql= "UPDATE "+table + " SET "+ coulmn + " = '" + value + "' WHERE "+refColumn+ "= ("+ ref +")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("failed update");
		}
	}
	
	public void Delete(String table,String column,String ref) {
		try {
			stmt= conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to create delete");
		}
		String sql4= "DELETE FROM " +table + " WHERE " + column+" = ("+ref+")";
		try {
			stmt.executeUpdate(sql4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to delete");
		}
	}
}
