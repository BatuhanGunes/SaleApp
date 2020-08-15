package Gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Gui.Reporting.Product;
import Gui.Reporting.Products;
import Model.DbConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//SQLite parameters
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setTitle("Ürün Programlama");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Positions the frame in the center of the screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
		
		JLabel lblFrameTitle = new JLabel("ÜRÜN PROGRAMLAMA");
		lblFrameTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrameTitle.setBounds(112, 40, 228, 25);
		contentPane.add(lblFrameTitle);
		
		JButton btnProductRegistration = new JButton("Ürün Kaydetme");
		btnProductRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Closes one frame and opens another
				setVisible(false);
				dispose();
				ProductRegistration productRegistration = new ProductRegistration();
				productRegistration.setVisible(true);
			}
		});
		btnProductRegistration.setBounds(150, 100, 150, 23);
		contentPane.add(btnProductRegistration);
		
		JButton btnProductUpdate = new JButton("Ürün Güncelleme");
		btnProductUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Close one frame and open another frame
				setVisible(false);
				dispose();
				ProductUpdate productUpdate = new ProductUpdate();
				productUpdate.setVisible(true);
			}
		});
		btnProductUpdate.setBounds(150, 140, 150, 23);
		contentPane.add(btnProductUpdate);
		
		JButton btnReporting = new JButton("Raporlama");
		btnReporting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Close one frame and open another frame
				setVisible(false);
				dispose();
				Reporting reporting = new Reporting();
				reporting.setVisible(true);
			}
		});
		btnReporting.setBounds(150, 180, 150, 23);
		contentPane.add(btnReporting);
		
		JButton btnExit = new JButton("Çıkış");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	//Close the application
			}
		});
		btnExit.setBounds(150, 220, 150, 23);
		contentPane.add(btnExit);
		
		connectToClient();
	}
	
	public void connectToClient() {
		int msgNo = 0;
		chooseMessageType(msgNo);
		
	}
	
	public void chooseMessageType(int msgNo) {
		switch (msgNo) {
		case 0: {
			// Upload product data to the client.
			uploadProduct();
			break;
		}
		case 1:{
			// Download the saleDetails data from the client.
			downloadSaleDetails(1,"Deneme",1,1.0);
			break;
		}
		case 2:{
			// Save sale data from client in database
			insertSale(1,1,1,1);
			break;
		}
		case 3:{
			// Save sale data from client in database
			uploadSale(10,10,10,10);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + msgNo);
		}
	}
	
	// A class to save data read from the database
	public class Product{
		public String ProductName = null;
		public double UnitPrice = 0.0;
		public int VatRate = 0;
	}
		
	// Converting the recorded data to arraylist format
	public class Products{
		ArrayList<Product> products = new ArrayList<Product>();
	}
		
	public void uploadProduct () {
		conn = DbConnection.ConnectDB();
		
		String query = "SELECT ProductName, UnitPrice, VatRate FROM Products";
		
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			//Checks if there is an active recording.
			if(rs.isClosed()) {
				JOptionPane.showMessageDialog(null, "Kayıt Bulunamadı. Lütfen Tekrar Deneyiniz.");
			}else {
				
				Products productsToConvert = new Products();
				
				while (rs.next()) {
					
					Product product = new Product();
					
					// Parsing data read from the database
					product.ProductName = rs.getString("ProductName");
					product.UnitPrice = rs.getDouble("UnitPrice");
					product.VatRate = rs.getInt("VatRate");
					
					// Addition to arraylist
					productsToConvert.products.add(product);
					
					// Creates the JSON format.
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String jsonProduct = gson.toJson(productsToConvert);
				}
			}
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public void downloadSaleDetails (int productId, String productName, int quantity, double amount) {
		conn = DbConnection.ConnectDB();

		String query = "INSERT INTO SaleDetails(ProductId, ProductName, Quantity, Amount) VALUES (?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, productId);
			pst.setString(2, productName);
			pst.setInt(3, quantity);
			pst.setDouble(4, amount);
			pst.execute();

			closeDB();
			JOptionPane.showMessageDialog(null, "Satış detayları Eklendi.");
		}catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public void insertSale(int receiptCount, double totalAmount, double cashPayment, double creditPayment) {
		conn = DbConnection.ConnectDB();

		String query = "INSERT INTO Sale(ReceiptCount, TotalAmount, CashPayment, CreditPayment) VALUES (?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, receiptCount);
			pst.setDouble(2, totalAmount);
			pst.setDouble(3, cashPayment);
			pst.setDouble(4, creditPayment);
			pst.execute();

			closeDB();
			JOptionPane.showMessageDialog(null, "Satış Eklendi.");
		}catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public void uploadSale(int receiptCount, double totalAmount, double cashPayment, double creditPayment) {
		conn = DbConnection.ConnectDB();
		
		String query = "UPDATE Sale SET "	
				+ "ReceiptCount=" + receiptCount + ", "
				+ "TotalAmount='" + totalAmount +"', "
				+ "CashPayment=" + cashPayment + ", "
				+ "CreditPayment=" + creditPayment 
				+ " WHERE Id=(SELECT max(Id) FROM Sale)";
		
		try {
			pst = conn.prepareStatement(query);
			pst.executeUpdate();
			
			closeDB();
			JOptionPane.showMessageDialog(null, "Satış güncelleştirildi.");
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public void closeDB() {	
		try {
			//close DB
			pst.close();	// PreparedStatement closed
			conn.close();	// Connection closed
			System.out.println("Disconnected from SQLite.");
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}
