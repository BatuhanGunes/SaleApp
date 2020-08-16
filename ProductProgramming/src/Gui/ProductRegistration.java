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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProductID;
	private JTextField txtProductName;
	private JTextField txtProductPrice;
	private JTextField txtProductBarcode;
	private JComboBox<String> vatList;
	
	private int decimalLimit = 0;
	
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
					ProductRegistration frame = new ProductRegistration();
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
	public ProductRegistration() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Positions the frame in the center of the screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
		
		JLabel lblFrameTitle = new JLabel("ÜRÜN KAYDETME");
		lblFrameTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrameTitle.setBounds(137, 40, 176, 25);
		contentPane.add(lblFrameTitle);
		
		JLabel lblProductID = new JLabel("Ürün No");
		lblProductID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductID.setBounds(50, 100, 100, 20);
		contentPane.add(lblProductID);
		
		JLabel lblProductName = new JLabel("Ürün Adı");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(50, 130, 100, 20);
		contentPane.add(lblProductName);
		
		JLabel lblProductPrice = new JLabel("Ürün Fiyatı");
		lblProductPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductPrice.setBounds(50, 160, 100, 20);
		contentPane.add(lblProductPrice);
		
		JLabel lblProductVAT = new JLabel("Ürün Kdvsi");
		lblProductVAT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductVAT.setBounds(50, 190, 100, 20);
		contentPane.add(lblProductVAT);
		
		JLabel lblProductBarcode = new JLabel("Ürün Barkodu");
		lblProductBarcode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductBarcode.setBounds(50, 220, 115, 20);
		contentPane.add(lblProductBarcode);
		
		JLabel lblColon1 = new JLabel(":");
		lblColon1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon1.setBounds(185, 100, 15, 20);
		contentPane.add(lblColon1);
		
		JLabel lblColon2 = new JLabel(":");
		lblColon2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon2.setBounds(185, 130, 15, 20);
		contentPane.add(lblColon2);
		
		JLabel lblColon3 = new JLabel(":");
		lblColon3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon3.setBounds(185, 160, 15, 20);
		contentPane.add(lblColon3);
		
		JLabel lblColon4 = new JLabel(":");
		lblColon4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon4.setBounds(185, 190, 15, 20);
		contentPane.add(lblColon4);
		
		JLabel lblColon5 = new JLabel(":");
		lblColon5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon5.setBounds(185, 220, 15, 20);
		contentPane.add(lblColon5);
		
		txtProductID = new JTextField();
		txtProductID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Only allows digit input
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
		        }
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// Deletes the last inserted character when the length of the txtProductID field is more than 6
				if (txtProductID.getText().length() >= 6) {
					txtProductID.setText(txtProductID.getText().substring(0, txtProductID.getText().length() - 1));
				}
			}
		});
		txtProductID.setBounds(215, 100, 150, 20);
		contentPane.add(txtProductID);
		txtProductID.setColumns(10);
		
		txtProductName = new JTextField();
		txtProductName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Allows entry of alphanumeric characters only
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && !Character.isDigit(c)) {
					e.consume();
		        }
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// Deletes the last inserted character when the length of the txtProductName field is more than 21
				if (txtProductName.getText().length() >= 21) {
					txtProductName.setText(txtProductName.getText().substring(0, txtProductName.getText().length() - 1));
				}
			}
		});
		txtProductName.setBounds(215, 130, 150, 20);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		txtProductPrice = new JTextField();
		txtProductPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Only '.' and allows entry of alphanumeric characters
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && e.getKeyChar() != '.') {
					e.consume();
		        }
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtProductPrice.getText().indexOf(".") != -1 || txtProductPrice.getText().length() >= 9) {	
					decimalLimit += 1;
					if(decimalLimit == 4) {
						txtProductPrice.setText(txtProductPrice.getText().substring(0, txtProductPrice.getText().length() - 1));
						decimalLimit -= 1;
					}
				}
			}
		});
		txtProductPrice.setBounds(215, 160, 150, 20);
		contentPane.add(txtProductPrice);
		txtProductPrice.setColumns(10);

		String[] vatStrings = { "0", "1", "8", "18"};
		vatList = new JComboBox<String>(vatStrings);
		vatList.setBounds(215, 190, 150, 22);
		contentPane.add(vatList);
		
		txtProductBarcode = new JTextField();
		txtProductBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// Allows entry of alphanumeric characters only
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && !Character.isDigit(c)) {
					e.consume();
		        }
			}
		});
		txtProductBarcode.setBounds(215, 220, 150, 20);
		contentPane.add(txtProductBarcode);
		txtProductBarcode.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 270, 350, 2);
		contentPane.add(separator);
		
		JButton btnSave = new JButton("Kaydet");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//It performs the control of the fields that must be filled.
				if(txtProductName.getText().isEmpty() || txtProductPrice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ürün Adı veya Ürün Fiyatı alanları boş bırakılamaz. \n"
							+ "Lütfen gerekli alanları doldurduktan sonra tekrar deneyiniz.");			
				}else {
					sameProductIdControl(Integer.parseInt(txtProductID.getText()));
				}
			}
		});
		btnSave.setBounds(275, 302, 89, 23);
		contentPane.add(btnSave);
		
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
		btnBack.setBounds(50, 302, 89, 23);
		contentPane.add(btnBack);

	}
	
	//Clears information in components
	public void clearComp() {
		txtProductID.setText("");
		txtProductName.setText("");
		txtProductPrice.setText("");
		vatList.setSelectedIndex(0);
		txtProductBarcode.setText("");
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
	
	public void sameProductIdControl(int id) {
		conn = DbConnection.ConnectDB();
		
		String query = "SELECT * FROM Products WHERE ID=" + id;
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			//Checks if there is an active recording.
			if(rs.isClosed()) {
				insertProduct();
			}else {
				JOptionPane.showMessageDialog(null, "Aynı Ürün No değerine sahip veri bulunmaktadır. \n"
						+ "lütfen Ürün No alanını değiştirip tekrar deneyiniz.");
			}
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
		
	public void insertProduct() {

		//Saving process when 'Barcode' field is empty
		if(txtProductBarcode.getText().isEmpty()) {
			conn = DbConnection.ConnectDB();
			
			// Barcode field is assigned null because 'Barcode' field is empty
			String query = "INSERT INTO Products(ID, Name, Price, Vat, Barcode) VALUES (?,?,?,?,null)";
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(txtProductID.getText()));
				pst.setString(2, txtProductName.getText());
				pst.setDouble(3, Double.parseDouble(txtProductPrice.getText()));
				pst.setInt(4, Integer.parseInt(vatList.getSelectedItem().toString()));
				pst.execute();

				clearComp();
				closeDB();
				JOptionPane.showMessageDialog(null, "Ürün Eklendi.");
			}catch (Exception e1) {
				// handle exception
				JOptionPane.showMessageDialog(null, e1);	
			}
				
		}else if(txtProductBarcode.getText().length() == 13) {
			// Saving process when 'Barcode' field is filled
			String query = "INSERT INTO Products(ID, Name, Price, Vat, Barcode) VALUES (?,?,?,?,?)";
			try {
				pst = conn.prepareStatement(query);
				pst.setInt(1, Integer.parseInt(txtProductID.getText()));
				pst.setString(2, txtProductName.getText());
				pst.setDouble(3, Double.parseDouble(txtProductPrice.getText()));
				pst.setInt(4, Integer.parseInt(vatList.getSelectedItem().toString()));
				pst.setString(5, txtProductBarcode.getText());
				pst.execute();
				
				clearComp();
				closeDB();
				JOptionPane.showMessageDialog(null, "Ürün Eklendi.");
			} catch (Exception e1) {
				// handle exception
				JOptionPane.showMessageDialog(null, e1);	
			}
		}else {
			JOptionPane.showMessageDialog(null, "'Ürün Barkodu' alanı 0 veya 13 karakter uzunluğunda olmalıdır.\n"
					+ "Lütfen tekrar deneyiniz.");
		}
	}
}
