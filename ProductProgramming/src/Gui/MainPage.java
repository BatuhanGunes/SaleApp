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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
	static Connection conn = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	
	// Message sender parameters
	static Socket socket;
	static ServerSocket serverSocket;
	static InputStreamReader inputStreamReader;
	static BufferedReader bufferedReader;
	static PrintWriter printWriter;
	static String message;
	static String jsonMsg;
	static int portAddress = 7800;
	static String ipAddress = "10.0.2.16";
	
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
		
		try {
			serverSocket = new ServerSocket(portAddress);
			while(true) {
				socket = serverSocket.accept();
				inputStreamReader = new InputStreamReader(socket.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader);
				message = bufferedReader.readLine();
				System.out.println(message);
				
				String[] msg = message.split("-");
				String massegeType = msg[0];
				jsonMsg = msg[1];
				
				chooseMessageType(massegeType);
			}
		}catch (Exception e) {
			// handle exception
			e.printStackTrace();
		}
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
		
		JLabel lblIpAddressTitle = new JLabel("Bağlanılan Ip:");
		lblIpAddressTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIpAddressTitle.setBounds(10, 260, 130, 25);
		contentPane.add(lblIpAddressTitle);
		
		JLabel lblConnectPortTitle = new JLabel("Bağlanılan Port:");
		lblConnectPortTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConnectPortTitle.setBounds(10, 280, 130, 25);
		contentPane.add(lblConnectPortTitle);
		
		JLabel lblUsedPortTitle = new JLabel("Kullanılan Port:");
		lblUsedPortTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsedPortTitle.setBounds(10, 300, 130, 25);
		contentPane.add(lblUsedPortTitle);
		
		JLabel lblIpAddress = new JLabel("");
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIpAddress.setBounds(150, 260, 228, 25);
		contentPane.add(lblIpAddress);
		lblIpAddress.setText(ipAddress);
		
		JLabel lblConnectPort = new JLabel("");
		lblConnectPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConnectPort.setBounds(150, 280, 228, 25);
		contentPane.add(lblConnectPort);
		lblConnectPort.setText(String.valueOf(portAddress+1));
		
		
		JLabel lblUsedPort = new JLabel("");
		lblUsedPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsedPort.setBounds(150, 300, 228, 25);
		contentPane.add(lblUsedPort);
		lblUsedPort.setText(String.valueOf(portAddress));
	}
	
	public static void chooseMessageType(String msgType) {
		switch (msgType) {
		case "getProduct": {
			// Upload product data to the client.
			uploadProduct();
			break;
		}
		case "saleDetails":{
			// Download the saleDetails data from the client.
			try {
                downloadSaleDetails(Integer.parseInt(jsonMsg.substring(0)), Double.parseDouble(jsonMsg.substring(1)), 
    					Double.parseDouble(jsonMsg.substring(2)), Double.parseDouble(jsonMsg.substring(3)));
            } catch (Throwable e) {
            	JOptionPane.showMessageDialog(null, e);	
            }
			
			break;
		}
		case "Deneme1":{
			// Save sale data from client in database
			insertSale(1,1,1,1);
			break;
		}
		case "Deneme2":{
			// Save sale data from client in database
			uploadSale(10,10,10,10);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + msgType);
		}
	}
	
	// A class to save data read from the database
	public static class Product{
		public String ProductName = null;
		public double UnitPrice = 0.0;
		public int VatRate = 0;
	}
	
	// Converting the recorded data to arraylist format
		public static class Products{
			ArrayList<Product> products = new ArrayList<Product>();
		}
		
	// Creating Json format and send client
	public static void uploadProduct () {
		conn = DbConnection.ConnectDB();
		String query = "SELECT ProductName, UnitPrice, VatRate FROM Products";
		
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			Products productsToConvert = new Products();
			
			//Checks if there is an active recording.
			if(rs.isClosed()) {
				JOptionPane.showMessageDialog(null, "Kayıt Bulunamadı. Lütfen Tekrar Deneyiniz.");
			}else {

				while (rs.next()) {
					
					Product product = new Product();
					
					// Parsing data read from the database
					product.ProductName = rs.getString("ProductName");
					product.UnitPrice = rs.getDouble("UnitPrice");
					product.VatRate = rs.getInt("VatRate");

					// Addition to arraylist
					productsToConvert.products.add(product);
				}
				
				// Creates the JSON format.
				Gson gson = new GsonBuilder().create();
				String json = gson.toJson(productsToConvert);
				System.out.print(json);
				
				try {
					socket = new Socket(ipAddress, (portAddress+1));
					printWriter = new PrintWriter(socket.getOutputStream());
					printWriter.write(json);
					printWriter.flush();
					printWriter.close();
					socket.close();
				}catch (IOException e) {
					// handle exception
					e.printStackTrace();
				}
			}
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public static void downloadSaleDetails (int receiptCount, double totalAmount, double cashPayment, double creditPayment) {
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
			JOptionPane.showMessageDialog(null, "Satış detayları Eklendi.");
		}catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public static void insertSale(int receiptCount, double totalAmount, double cashPayment, double creditPayment) {
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
	
	public static void uploadSale(int receiptCount, double totalAmount, double cashPayment, double creditPayment) {
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
	
	public static void closeDB() {	
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
