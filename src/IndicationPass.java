import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class IndicationPass extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldN;
	private JTextField textFieldPass;

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndicationPass frame = new IndicationPass();
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
	public IndicationPass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = ConnectionMysql.getConnection();
		
		textFieldN = new JTextField();
		textFieldN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String username = textFieldN.getText();
				try {
					pst = con.prepareStatement("select password from user where userName=?");
					pst.setString(1, username);
					rs = pst.executeQuery();
					while(rs.next()){
					String pass = rs.getString("password").substring(0,3);
					textFieldPass.setText("les 3 chifre de mot de pass sont :" + pass);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textFieldN.setBounds(202, 65, 165, 35);
		contentPane.add(textFieldN);
		textFieldN.setColumns(10);
		
		textFieldPass = new JTextField();
		textFieldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		textFieldPass.setBounds(202, 113, 282, 35);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(" Name :");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(104, 68, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblPass = new JLabel("  Pass :");
		lblPass.setBounds(104, 116, 56, 16);
		contentPane.add(lblPass);
	}
}
