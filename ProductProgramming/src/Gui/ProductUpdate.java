package Gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import Model.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductUpdate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProductID;
	private JTextField txtProductName;
	private JTextField txtProductPrice;
	private JTextField txtProductBarcode;
	private JComboBox<String> vatList;
	private JLabel lblProductPrice;
	private JLabel lblProductVAT;
	private JLabel lblProductBarcode;
	private JLabel lblColon3;
	private JLabel lblColon4;
	private JLabel lblColon5;
	
	private int decimalLimit = 0;
	private int defaultID;
	
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
					ProductUpdate frame = new ProductUpdate();
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
	public ProductUpdate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Positions the frame in the center of the screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
		
		JLabel lblFrameTitle = new JLabel("ÜRÜN GÜNCELLEME");
		lblFrameTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrameTitle.setBounds(125, 40, 200, 25);
		contentPane.add(lblFrameTitle);
		
		JLabel lblProductName = new JLabel("Ürün Adı");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(20, 130, 100, 20);
		contentPane.add(lblProductName);
		
		JLabel lblProductID = new JLabel("Ürün No");
		lblProductID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductID.setBounds(20, 100, 100, 20);
		contentPane.add(lblProductID);
		
		lblProductPrice = new JLabel("Ürün Fiyatı");
		lblProductPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductPrice.setBounds(20, 160, 100, 20);
		contentPane.add(lblProductPrice);
		
		lblProductVAT = new JLabel("Ürün Kdvsi");
		lblProductVAT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductVAT.setBounds(20, 190, 100, 20);
		contentPane.add(lblProductVAT);
		
		lblProductBarcode = new JLabel("Ürün Barkodu");
		lblProductBarcode.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductBarcode.setBounds(20, 220, 115, 20);
		contentPane.add(lblProductBarcode);
		
		JLabel lblColon1 = new JLabel(":");
		lblColon1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon1.setBounds(150, 100, 15, 20);
		contentPane.add(lblColon1);
		
		JLabel lblColon2 = new JLabel(":");
		lblColon2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon2.setBounds(150, 130, 15, 20);
		contentPane.add(lblColon2);
		
		lblColon3 = new JLabel(":");
		lblColon3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon3.setBounds(150, 160, 15, 20);
		contentPane.add(lblColon3);
		
		lblColon4 = new JLabel(":");
		lblColon4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon4.setBounds(150, 190, 15, 20);
		contentPane.add(lblColon4);
		
		lblColon5 = new JLabel(":");
		lblColon5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblColon5.setBounds(150, 220, 15, 20);
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
		txtProductID.setColumns(10);
		txtProductID.setBounds(170, 100, 150, 20);
		contentPane.add(txtProductID);
				
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
		txtProductName.setColumns(10);
		txtProductName.setBounds(170, 130, 150, 20);
		contentPane.add(txtProductName);
		
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
		txtProductPrice.setBounds(170, 160, 150, 20);
		contentPane.add(txtProductPrice);
		txtProductPrice.setColumns(10);
		
		String[] vatStrings = { "0", "1", "8", "18"};
		vatList = new JComboBox<String>(vatStrings);
		vatList.setBounds(170, 190, 150, 22);
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
		txtProductBarcode.setBounds(170, 220, 150, 20);
		contentPane.add(txtProductBarcode);
		txtProductBarcode.setColumns(10);

		JButton btnSearchProductID = new JButton("Ara");
		btnSearchProductID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//It performs the control of the field that must be filled.
				if(txtProductID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bu işlem için 'Ürün No' alanı boş bırakılamaz. \n"
							+ "Lütfen gerekli alanı doldurduktan sonra tekrar deneyiniz.");
				}else {
					searchByID(Integer.parseInt(txtProductID.getText()));
				}
			}
		});
		btnSearchProductID.setBounds(350, 100, 56, 23);
		contentPane.add(btnSearchProductID);
		
		JButton btnSearchProductName = new JButton("Ara");
		btnSearchProductName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//It performs the control of the field that must be filled.
				if(txtProductName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bu işlem için 'Ürün Adı' alanı boş bırakılamaz. \n"
							+ "Lütfen gerekli alanı doldurduktan sonra tekrar deneyiniz.");
				}else {					
					searchByName(txtProductName.getText().toString());					
				}
			}
		});
		btnSearchProductName.setBounds(350, 130, 56, 23);
		contentPane.add(btnSearchProductName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 270, 350, 2);
		contentPane.add(separator);
		
		JButton btnSave = new JButton("Güncelle");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//It performs the control of the fields that must be filled.
				if(txtProductID.getText().isEmpty() || txtProductName.getText().isEmpty() || 
						txtProductPrice.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ürün Adı, Ürün Fiyatı veya Ürün Kdvsi alanları boş bırakılamaz. \n"
							+ "Lütfen gerekli alanları doldurduktan sonra tekrar deneyiniz.");		
				}else {
					updateRow(defaultID);	
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
		
		JButton btnReset = new JButton("Sıfırla");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Clear the information in the frame
				setVisiableComp(false);
				clearComp();
				txtProductID.setEnabled(true);
				txtProductName.setEnabled(true);
			}
		});
		btnReset.setBounds(161, 302, 89, 23);
		contentPane.add(btnReset);
		
		setVisiableComp(false);
	}
	
	//It changes the invisibility of some components.
	public void setVisiableComp(Boolean visibleFlag) {
		lblProductPrice.setVisible(visibleFlag);
		lblProductVAT.setVisible(visibleFlag);
		lblProductBarcode.setVisible(visibleFlag);
		lblColon3.setVisible(visibleFlag);
		lblColon4.setVisible(visibleFlag);
		lblColon5.setVisible(visibleFlag);
		txtProductPrice.setVisible(visibleFlag);
		txtProductBarcode.setVisible(visibleFlag);
		vatList.setVisible(visibleFlag);
		
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
			pst.close();
			conn.close();
			System.out.println("Disconnected from SQLite.");
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Searches according to the ID variable.
	public void searchByID(int id) {
		conn = DbConnection.ConnectDB();
		String query = "SELECT * FROM Products WHERE ID=" + id;
		try {	
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			//Checks if there is an active recording.
			if(rs.isClosed()) {
				JOptionPane.showMessageDialog(null, "Kayıt Bulunamadı. Lütfen Tekrar Deneyiniz.");
				setVisiableComp(false);
				clearComp();
				txtProductID.setEnabled(true);
				txtProductName.setEnabled(true);
				return;
			}else {
				txtProductName.setEnabled(true);
				txtProductID.setEnabled(false);
				
				// The data read from the database was written to the required places
				txtProductName.setText(rs.getString("Name"));	
				txtProductPrice.setText(rs.getString("Price"));
				vatList.setSelectedItem(rs.getString("Vat"));
				txtProductBarcode.setText(rs.getString("Barcode"));
				
				defaultID = Integer.parseInt(txtProductID.getText());
				setVisiableComp(true);
			}
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	//Searches according to the Name variable.
	public void searchByName(String name) {
		conn = DbConnection.ConnectDB();
		String query = "SELECT * FROM Products WHERE Name='" + name + "'";
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			
			//Checks if there is an active recording.
			if(rs.isClosed()) {
				JOptionPane.showMessageDialog(null, "Kayıt Bulunamadı. Lütfen Tekrar Deneyiniz.");
				setVisiableComp(false);
				clearComp();
				txtProductID.setEnabled(true);
				txtProductName.setEnabled(true);
				return;
			}else {
				txtProductName.setEnabled(false);
				txtProductID.setEnabled(true);
				
				// The data read from the database was written to the required places
				txtProductID.setText(rs.getString("ID"));	
				txtProductPrice.setText(rs.getString("Price"));
				vatList.setSelectedItem(rs.getString("Vat"));
				txtProductBarcode.setText(rs.getString("Barcode"));
				
				defaultID = Integer.parseInt(txtProductID.getText());
				setVisiableComp(true);
			}
			closeDB();
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
	
	//Allows a single line to be update
	public void updateRow(int id) {
		try {
			conn = DbConnection.ConnectDB();
			String query = null;

			if(txtProductBarcode.getText().isEmpty()) {
				// Saving process when 'Barcode' field is empty
				query = "UPDATE Products SET "	
						+ "ID=" + Integer.parseInt(txtProductID.getText()) + ", "
						+ "Name='" + txtProductName.getText() +"', "
						+ "Price=" + Double.parseDouble(txtProductPrice.getText()) + ", "
						+ "Vat=" + Integer.parseInt(vatList.getSelectedItem().toString()) + ", "
						+ "Barcode=null WHERE ID=" + id;
			}else if(txtProductBarcode.getText().length() == 13){
				// Saving process when 'Barcode' field is filled
				query = "UPDATE Products SET "		
						+ "ID=" + Integer.parseInt(txtProductID.getText()) + ", "
						+ "Name='" + txtProductName.getText() + "', "
						+ "Price=" + Double.parseDouble(txtProductPrice.getText()) + ", "
						+ "Vat=" + Integer.parseInt(vatList.getSelectedItem().toString()) + ", "
						+ "Barcode='" + txtProductBarcode.getText()+ "'"
						+ " WHERE ID=" + id;	
			}else {
				JOptionPane.showMessageDialog(null, "'Ürün Barkodu' alanı 0 veya 13 karakter uzunluğunda olmalıdır.\n"
						+ "Lütfen tekrar deneyiniz.");
			}
			
			pst = conn.prepareStatement(query);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ürün güncelleştirildi.");
			closeDB();
			
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e);	
		}
	}
}
