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
import com.google.gson.reflect.TypeToken;

import Model.DbConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
	static String ipAddress = "192.168.2.2";
	private JTextField textFieldClientIp;
	
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
				System.out.println("Message : " + message);
				
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
		
		JLabel lblIpAddressTitle = new JLabel("Server Ip:");
		lblIpAddressTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIpAddressTitle.setBounds(10, 260, 130, 25);
		contentPane.add(lblIpAddressTitle);
		
		JLabel lblConnectPortTitle = new JLabel("Server Port:");
		lblConnectPortTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConnectPortTitle.setBounds(10, 280, 130, 25);
		contentPane.add(lblConnectPortTitle);
		
		JLabel lblUsedPortTitle = new JLabel("Client Port:");
		lblUsedPortTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsedPortTitle.setBounds(10, 300, 130, 25);
		contentPane.add(lblUsedPortTitle);
		
		JLabel lblIpAddress = new JLabel("");
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIpAddress.setBounds(150, 260, 109, 25);
		contentPane.add(lblIpAddress);
		
		try {
			InetAddress myIP;
			myIP = InetAddress.getLocalHost();
			lblIpAddress.setText(myIP.getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblConnectPort = new JLabel("");
		lblConnectPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConnectPort.setBounds(150, 280, 86, 25);
		contentPane.add(lblConnectPort);
		lblConnectPort.setText(String.valueOf(portAddress+1));
		
		JLabel lblUsedPort = new JLabel("");
		lblUsedPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsedPort.setBounds(150, 300, 118, 25);
		contentPane.add(lblUsedPort);
		lblUsedPort.setText(String.valueOf(portAddress));
		
		textFieldClientIp = new JTextField();
		textFieldClientIp.setText("192.168.2.2");
		textFieldClientIp.setBounds(290, 284, 86, 20);
		contentPane.add(textFieldClientIp);
		textFieldClientIp.setColumns(10);
		
		JButton btnClientChangeIp = new JButton("ip Değiştir");
		btnClientChangeIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ipAddress = textFieldClientIp.getText().toString();
				JOptionPane.showMessageDialog(null, "Client ip değiştirildi.");
			}
		});
		btnClientChangeIp.setBounds(278, 316, 109, 23);
		contentPane.add(btnClientChangeIp);
		
		JLabel lblClientIp = new JLabel("Client ip:");
		lblClientIp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClientIp.setBounds(294, 254, 130, 25);
		contentPane.add(lblClientIp);
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
				Gson gson = new Gson();
				Map<String, String> map = gson.fromJson(jsonMsg, new TypeToken<Map<String, String>>() {}.getType());

				String productName = map.get("productName");
				int totalQuantity = Integer.parseInt(map.get("totalQuantity"));
					        
				downloadSaleDetails(productName, totalQuantity);
				System.out.println("Save in sales 'sales detail' table");
			} catch (Throwable e) {
				JOptionPane.showMessageDialog(null, e);	
			}
			break;
		}
		case "sale":{
			try {
				// Download the sale data from the client.
				Gson gson = new Gson();
				Map<String, String> map = gson.fromJson(jsonMsg, new TypeToken<Map<String, String>>() {}.getType());
				
				int receiptCount = Integer.parseInt(map.get("receiptCount"));
				double cashPayment = Double.parseDouble(map.get("cashPayment"));
				double creditPayment = Double.parseDouble(map.get("creditPayment"));
				
				saveSale(receiptCount, cashPayment, creditPayment);
				System.out.println("Save in sales 'sale' table");
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);	
			}
			break;
		}
		case "connectionTest":{
			connectionTest();
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
				
				try {
					socket = new Socket(ipAddress, (portAddress+1));
					printWriter = new PrintWriter(socket.getOutputStream());
					printWriter.write(json);
					printWriter.flush();
					printWriter.close();
					socket.close();
					System.out.println("products sent message : " + json);
				}catch (IOException e) {
					// handle exception
					JOptionPane.showMessageDialog(null, e);	
				}
			}
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
		closeDB();
	}

	public static void downloadSaleDetails(String productName, int totalQuantity) {
		conn = DbConnection.ConnectDB();
		String query = "SELECT Id,UnitPrice FROM Products WHERE ProductName='" + productName + "'";
		
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			//Checks if there is an active recording.
			if(rs.isClosed()) {
				JOptionPane.showMessageDialog(null, "Kayıt Bulunamadı. Lütfen Tekrar Deneyiniz.");
				return;
			}else {		

				String querySaleDetails = "INSERT INTO SaleDetails(ProductId, ProductName, Amount) VALUES (?,?,?)";
				try {
					double Amount = totalQuantity * Double.parseDouble(rs.getString("UnitPrice"));
					
					pst = conn.prepareStatement(querySaleDetails);
					pst.setInt(1, Integer.parseInt(rs.getString("Id")));
					pst.setString(2, productName);
					pst.setDouble(3, Amount);
					pst.execute();
				}catch (Exception e) {
					// handle exception
					JOptionPane.showMessageDialog(null, e);	
				}
			}
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	private static void saveSale(int receiptCount, double cashPayment, double creditPayment) {
		conn = DbConnection.ConnectDB();
		
		String query = "INSERT INTO Sale(ReceiptCount, TotalAmount, CashPayment, CreditPayment) VALUES (?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, receiptCount);
			pst.setDouble(2, (cashPayment + creditPayment)); // TotalAmount = CashPayment + CreditPaymente
			pst.setDouble(3, cashPayment);
			pst.setDouble(4, creditPayment);
			pst.execute();

			closeDB();
		}catch (Exception e1) {
			// handle exception
			JOptionPane.showMessageDialog(null, e1);	
		}
	}
	
	public static void connectionTest() {
		try {
			socket = new Socket(ipAddress, (portAddress+1));
			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.write("ConnectionSuccess");
			printWriter.flush();
			printWriter.close();
			socket.close();
			System.out.println("sent message : ConnectionSuccess");
		}catch (IOException e) {
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
