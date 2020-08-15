package Gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.JScrollPane;

public class Reporting extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane textPane;
	
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
					Reporting frame = new Reporting();
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
	public Reporting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Positions the frame in the center of the screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
		
		JLabel lblFrameTitle = new JLabel("RAPORLAMA");
		lblFrameTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrameTitle.setBounds(160, 40, 129, 25);
		contentPane.add(lblFrameTitle);
		
		JButton btnCreateReport = new JButton("Oluştur");
		btnCreateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createReport();
			}
		});
		btnCreateReport.setBounds(250, 80, 89, 23);
		contentPane.add(btnCreateReport);
		
		JButton btnBack = new JButton("Geri");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Closes one frame and opens another
				setVisible(false);
				dispose();
				MainPage mainPage = new MainPage();
				mainPage.setVisible(true);
			}
		});
		btnBack.setBounds(100, 80, 89, 23);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 414, 231);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}

	// A class to save data read from the database
	public class Product{
		public int ID = 0;
		public String name = null;
		public double price = 0.0;
		public int vat = 0;
		public String barcode = null;
	}
	
	// Converting the recorded data to arraylist format
	public class Products{
		ArrayList<Product> products = new ArrayList<Product>();
	}
	
	// Creating Json format and printing it on the screen
	public void createReport() {		
		conn = DbConnection.ConnectDB();
		String query = "SELECT *FROM Products";
		
		try {	
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

			Products productsToConvert = new Products();
			
			while (rs.next()) {
							
				Product product = new Product();
				
				// Parsing data read from the database
				product.ID = rs.getInt("ID");
				product.name = rs.getString("Name");
				product.price = rs.getDouble("Price");
				product.vat = rs.getInt("Vat");
				product.barcode = rs.getString("Barcode");
				
				// Addition to arraylist
				productsToConvert.products.add(product);
				
			}
			// Creates the JSON format.
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(productsToConvert);
			textPane.setText(json);
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	public void closeDB() {
		try {
			//close DB
			rs.close();
			pst.close();
			conn.close();
			System.out.println("Disconnected from SQLite.");
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}
