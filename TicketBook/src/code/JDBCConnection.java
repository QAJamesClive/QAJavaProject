package code;

import java.sql.*;
import java.util.ArrayList;

public final class JDBCConnection {

	private String dB_URL;
	private String username;
	private String password;
	private Connection conn;
	
	private Statement stmt;
	
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
	
	public void Create(String table, ArrayList<String> List ) {
		String listAsString = "(";
		for(int i = 0; i <List.size();i++) {
			listAsString += List.get(i);
			if(i == List.size()-1) {
				listAsString += ")";
			}else {
				listAsString += ",";
			}
		}
		System.out.println("Inserting records into the table...");
		try {
			stmt = this.conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql= "INSERT INTO "+table +" VALUES "+listAsString;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Inserted records into the table...");
	}
	
	public ArrayList<String> Read(String table,ArrayList<String> list) {
		
		ArrayList<String> returnedList = new ArrayList<String>();
		
		System.out.println("Creating statement...");
		try {
			stmt= conn.createStatement();
		} catch (SQLException e1) {
			System.out.println("stmt = conn creation statment error exception");
			e1.printStackTrace();
		}
		String listAsString = "";
		for(int i = 0; i <list.size();i++) {
			listAsString += list.get(i);
			if(i != list.size()-1) {
				listAsString += ",";
			}
		}
		String sql= "SELECT "+listAsString+" FROM "+table;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("using ther query and collecting results set ");
			e.printStackTrace();
		}
		try {
			while(rs.next()) {
				String itemString = "";
				for(int i = 0; i < list.size();i++) {
					itemString += rs.getString(i);
					System.out.println(list.get(i)+ " : "+ returnedList.get(i));
				}
				returnedList.add(itemString);
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
}
