import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JScrollPane;



public class gestionEtudiant extends JFrame {

	private JPanel contentPane;
	private JTextField textFnom;
	private JTextField textFPr;
	private JTextField textFcin;
	private JTextField textFtel;
	private JTextField textFadres;
	private JTextField textFdate;
	JComboBox comboFil;

	/**
	 * Launch the application.
	 */
	Connection con = null;
	PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
	ResultSet rs ;

	private JTextField textFID;
	private JTable tableEtud;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestionEtudiant frame = new gestionEtudiant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	
	public  void AutoNumber(){
		try {
			pst1 = con.prepareStatement("select max(id_etudiant) from etudiants ");
			rs =   pst1.executeQuery();
		    int lastid ;
			 while(rs.next()){
				     lastid = rs.getInt(1);
				     lastid++;
				 textFID.setText(Integer.toString(lastid));
			 }


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void jcombo(){
		
		try {
			pst2 = con.prepareStatement("SELECT * FROM filliere");
			 rs = pst2.executeQuery();
			
			comboFil.removeAllItems();
			 
			 while(rs.next()){
				 String name = rs.getString("nom");
				 comboFil.addItem(name); 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void load(){
		try {
			pst = con.prepareStatement("select * from etudiants");
			rs = pst.executeQuery(); 
			DefaultTableModel dfm = (DefaultTableModel) tableEtud.getModel();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int c = rsmd.getColumnCount();
			dfm.setRowCount(1);
			
			while(rs.next()){
				Object row[] = new Object[8];
				for(int i=0;i<c;i++){
					row[i] = rs.getString(i+1);
				}
				dfm.addRow(row);
			}
			//tableEtud.setModel(Dbutils.resultSetModelToTable(rs));                 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public gestionEtudiant() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				AutoNumber();
				load();
				jcombo();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1188, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = ConnectionMysql.getConnection();
		
		JLabel lblNewLabel_3 = new JLabel("NOM :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(5, 166, 66, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Prenom :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(5, 209, 105, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CIN :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(5, 251, 56, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Numreo Tel :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(5, 295, 114, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Date de naisance :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(5, 345, 114, 16);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Adress :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(5, 397, 146, 16);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Filliere :");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(5, 443, 80, 16);
		contentPane.add(lblNewLabel_9);
		
		textFnom = new JTextField();
		textFnom.setBounds(160, 166, 154, 22);
		contentPane.add(textFnom);
		textFnom.setColumns(10);
		
		textFPr = new JTextField();
		textFPr.setColumns(10);
		textFPr.setBounds(160, 209, 154, 22);
		contentPane.add(textFPr);
		
		textFcin = new JTextField();
		textFcin.setColumns(10);
		textFcin.setBounds(160, 247, 154, 22);
		contentPane.add(textFcin);
		
		textFtel = new JTextField();
		textFtel.setColumns(10);
		textFtel.setBounds(160, 290, 154, 22);
		contentPane.add(textFtel);
		
		textFadres = new JTextField();
		textFadres.setColumns(10);
		textFadres.setBounds(160, 344, 154, 22);
		contentPane.add(textFadres);
		
		textFdate = new JTextField();
		textFdate.setColumns(10);
		textFdate.setBounds(160, 396, 154, 22);
		contentPane.add(textFdate);
		
		 comboFil = new JComboBox();
		comboFil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
			
		});

		comboFil.setBounds(160, 442, 154, 22);
		contentPane.add(comboFil);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gestionEtudiant gest = new gestionEtudiant();
				
				int id = Integer.parseInt(textFID.getText());				
				String nom = textFnom.getText();
				String prenom = textFPr.getText();
				String cin = textFcin.getText();
				String tel = textFtel.getText();
				String adres = textFadres.getText();
				String date = textFdate.getText();
				//String filliere = comboFil.getSelectedItem().toString();
				try {
					if(!nom.equals("") && !prenom.equals("") && !cin.equals("") && !tel.equals("") && !adres.equals("") && !date.equals("")){
					pst = con.prepareStatement("insert into Etudiants(id_etudiant,nom,prenom,cin,tel,datenaissance,adress)values(?,?,?,?,?,?,?)");
					pst.setInt(1,id);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setString(4, cin);
					pst.setString(5, tel);
					pst.setString(6, date);
					pst.setString(7, adres);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Etudiant is created " );
					textFnom.setText("");
					textFPr.setText("");
					textFcin.setText("");
					textFtel.setText("");
					textFadres.setText("");
					textFdate.setText("");
					textFnom.requestFocus();
					AutoNumber();
					load();
					}
					else {
						JOptionPane.showMessageDialog(null,"remplir tout les champs");	
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\images.jpg"));
		btnNewButton.setBounds(5, 524, 190, 156);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(textFID.getText());				
				String nom = textFnom.getText();
				String prenom = textFPr.getText();
				String cin = textFcin.getText();
				String tel = textFtel.getText();
				String adres = textFadres.getText();
				String date = textFdate.getText();
				
				try {
					pst = con.prepareStatement("update etudiants set nom =?,prenom =?,cin=?,tel=?,datenaissance=?,adress=? where id_etudiant = ?");
				
					pst.setString(1, nom);
					pst.setString(2, prenom);
					pst.setString(3, cin);
					pst.setString(4, tel);
					pst.setString(5, date);
					pst.setString(6, adres);
					pst.setInt(7,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Etudiant is updated " );
					load();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button.setIcon(null);
		button.setBounds(207, 524, 190, 156);
		contentPane.add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int row = tableEtud.getSelectedRow();
				//String id = tableEtud.getValueAt(row, 0).toString();
				int id = Integer.parseInt(textFID.getText());
				try {
					
					
					pst = con.prepareStatement("delete  from etudiants where id_etudiant = '"+ id +"'");
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Etudiant is deleted " );
					load();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\2673754-200.png"));
		button_1.setBounds(417, 524, 190, 156);
		contentPane.add(button_1);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(5, 119, 66, 16);
		contentPane.add(lblId);
		
		textFID = new JTextField();
		textFID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
		});
		textFID.setEditable(false);
		textFID.setColumns(10);
		textFID.setBounds(160, 119, 154, 22);
		contentPane.add(textFID);
		
		tableEtud = new JTable();
		tableEtud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tableEtud.getSelectedRow();
				
				 textFID.setText(tableEtud.getValueAt(row, 0).toString());				
				 textFnom.setText(tableEtud.getValueAt(row, 1).toString());
				 textFPr.setText(tableEtud.getValueAt(row, 2).toString());
				 textFcin.setText(tableEtud.getValueAt(row, 3).toString());
				 textFtel.setText(tableEtud.getValueAt(row, 4).toString());
				 textFadres.setText(tableEtud.getValueAt(row, 5).toString());
			     textFdate.setText(tableEtud.getValueAt(row, 6).toString());
			     comboFil.setSelectedItem(tableEtud.getValueAt(row, 7).toString());
				
				
				
			}
		});
		tableEtud.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableEtud.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "Nom", "Prenom", "CIN", "Num TEl", "Date Naissance", "Address", "Filliere"},
			},
			new String[] {
				"ID", "Nom", "Prenom", "CIN", "Num TEl", "Date Naissance", "Address", "Filliere"
			}
		));
		tableEtud.setBounds(339, 118, 819, 393);
		contentPane.add(tableEtud);
		
		JButton btnPdfp = new JButton("pdfp");
		btnPdfp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableEtud.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPdfp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		btnPdfp.setBounds(678, 555, 119, 95);
		contentPane.add(btnPdfp);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdministrateur mm = new MenuAdministrateur();
				mm.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(Color.ORANGE);
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton_1.setBounds(5, 36, 97, 41);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exite");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_2.setBounds(809, 555, 97, 95);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\light-green-color-solid-background-1920x1080.png"));
		lblNewLabel.setBounds(0, 102, 1170, 607);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GESTION DES ETUDIANTS");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 15, 1170, 50);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\fond-texture-abstrait.jpg"));
		lblNewLabel_2.setBounds(0, 0, 1170, 688);
		contentPane.add(lblNewLabel_2);
	
	}
}
