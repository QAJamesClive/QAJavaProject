package code;

import java.sql.*;
import java.util.ArrayList;

public final class JDBCConnection {

	private String dB_URL;
	private String username;
	private String password;
	private Connection conn;
	private Statement stmt;
	
	private ArrayList<String> returnedList;
	
	public JDBCConnection(String DB_URL,String Username,String Password){
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
	
	public ArrayList<String> getReturnedList() {
		return returnedList;
	}
	
	public void Connect() {
		conn= null;
		stmt= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(dB_URL, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Create(String table, String columnList, String valuesList) {

		System.out.println("Inserting records into the table...");
		try {
			stmt = this.conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql= "INSERT INTO "+table +" ("+ columnList+") VALUES ("+valuesList+")";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
			System.out.println(sql);
			System.out.println("Inserted records into the table...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> Read(String table,String columnString) {
		
		returnedList = new ArrayList<String>();
		
		int columnCount = 0;
		
		System.out.println("Creating statement...");
		try {
			stmt= conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("stmt = conn creation statment error exception");
			e1.printStackTrace();
		}
		for ( int i = 0; i < columnString.length(); i++ ) {
			if(columnString.charAt(i) == ',') {
				columnCount++;
			}
        }
		columnCount++;
		System.out.println(columnCount);
		String sql= "SELECT "+columnString+" FROM "+table;
		System.out.println(sql);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("using the query and collecting results set failed ");
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				String itemString = "";
				for(int i = 0; i < columnCount;i++) {
					itemString += rs.getString(i+1);
					if(i+1 < columnCount) {
						itemString += ",";
					}
				}
				returnedList.add(itemString);
				System.out.println(itemString);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnedList;
	}
	
	public void Update(String table,String coulmn,String value,String refColumn,String ref) {
		System.out.println("Creating statement...");
		try {
			stmt= conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql= "UPDATE "+table + " SET "+ coulmn + " = " + value + " WHERE "+refColumn+ "in ("+ ref +")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
