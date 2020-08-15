package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbConnection {

	Connection conn = null;
	static String url;
	
	public static Connection ConnectDB() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			url = "jdbc:sqlite:Products.db"; 	// Connection String
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			
			// Creates if the database file is not already created.
			String tableProduct = "CREATE TABLE IF NOT EXISTS Products (\n"
	                + "	Id INTEGER NOT NULL,\n"
	                + "	ProductName TEXT NOT NULL,\n"
	                + "	UnitPrice REAL NOT NULL,\n"
	                + "	VatRate INTEGER NOT NULL,\n"
	                + "	Barcode TEXT\n"
	                + ");";
			
			String tableSales = "CREATE TABLE IF NOT EXISTS Sales (\n"
	                + "	ReceiptCount INTEGER NOT NULL,\n"
	                + "	TotalAmount REAL NOT NULL,\n"
	                + "	CashPayment REAL NOT NULL,\n"
	                + "	CreditPayment REAL NOT NULL\n"
	                + ");";
			
			String tableSalesDetails = "CREATE TABLE IF NOT EXISTS SalesDetails (\n"
	                + "	ProductId INTEGER NOT NULL,\n"
	                + "	ProductName TEXT NOT NULL,\n"
	                + "	Amount REAL NOT NULL\n"
	                + ");";

			createTable(tableProduct);
			createTable(tableSales);
			createTable(tableSalesDetails);
			
			return conn;
			
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public static void createTable(String tableName) {
		try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(tableName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
}