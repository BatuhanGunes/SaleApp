package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbConnection {

	Connection conn = null;
	
	public static Connection ConnectDB() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:Products.db"; 	// Connection String
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			
			// Creates if the database file is not already created.
			String sql = "CREATE TABLE IF NOT EXISTS Products (\n"
	                + "	ID INTEGER NOT NULL,\n"
	                + "	Name TEXT NOT NULL,\n"
	                + "	Price REAL NOT NULL,\n"
	                + "	Vat INTEGER NOT NULL,\n"
	                + "	Barcode TEXT\n"
	                + ");";
			
			try (Connection conn1 = DriverManager.getConnection(url);
	                Statement stmt = conn1.createStatement()) {
	            // create a new table
	            stmt.execute(sql);

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
			
			return conn;
			
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}