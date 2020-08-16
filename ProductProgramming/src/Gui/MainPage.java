package Gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
	}
}
