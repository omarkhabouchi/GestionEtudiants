import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JTextPane;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DropMode;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Authentification extends JFrame {

	private JFrame frame;
	private JTextField userNF;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JButton btnConnect;
	private JPasswordField passwordF;

	/**
	 * Launch the application.
	 */
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
        con = ConnectionMysql.getConnection();
		userNF = new JTextField();
		userNF.setBounds(393, 193, 227, 49);
		frame.getContentPane().add(userNF);
		userNF.setColumns(10);
		
		btnConnect = new JButton("Se Connecter");
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = userNF.getText();
				String password = passwordF.getText();
				
				try {
					pst =con.prepareStatement("select * from user where userName='"+username+"' &&  password='"+password+"'");
					rs = pst.executeQuery();	
					
					 if(rs.next()){
					  frame.dispose();
						 MenuAdministrateur menu = new MenuAdministrateur();
						 menu.setVisible(true);
						 menu.setLocationRelativeTo(null);
						 
						
						
						}
					 else{
							JOptionPane.showMessageDialog(null,"remplir les champ");
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e);
				
				}
				
			}
		});
		btnConnect.setBounds(393, 310, 227, 51);
		frame.getContentPane().add(btnConnect);
		
		lblUserName = new JLabel("  User Name :");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setBounds(263, 209, 129, 33);
		frame.getContentPane().add(lblUserName);
		
		lblPassword = new JLabel("  Password :");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(273, 264, 129, 33);
		frame.getContentPane().add(lblPassword);
		
		passwordF = new JPasswordField();
		passwordF.setBounds(393, 248, 227, 49);
		frame.getContentPane().add(passwordF);
		
		JLabel lblNewLabel_1 = new JLabel("   Mot de pass oblier");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IndicationPass pass = new IndicationPass();
				pass.setVisible(true);
				pass.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(495, 365, 123, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\fond-texture-abstrait.jpg"));
		lblNewLabel.setBounds(0, 0, 982, 453);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(100, 100, 1000, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
