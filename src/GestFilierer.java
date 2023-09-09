import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import com.mysql.jdbc.ResultSetMetaData;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GestFilierer extends JFrame {

	private JPanel contentPane;
	private JTextField textFNF;

	/**
	 * Launch the application.
	 */
	Connection con = null;
	PreparedStatement pst = null;
	PreparedStatement pst1 = null;
	ResultSet rs ;
	private JTable tableFil;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestFilierer frame = new GestFilierer();
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
	public void load(){
		
		try {
			pst = con.prepareStatement("select * from filliere");
			rs = pst.executeQuery();
			DefaultTableModel dftm = (DefaultTableModel) tableFil.getModel();
			ResultSetMetaData rsmd =(ResultSetMetaData) rs.getMetaData();
			int c = rsmd.getColumnCount();
			dftm.setRowCount(1);
			while(rs.next()){
				Object row[] =  new Object[3];
				for(int i=0 ; i < c ; i++){
					row[i] = rs.getString(i+1);
					
				}
				dftm.addRow(row);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public GestFilierer() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				load();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1188, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = ConnectionMysql.getConnection();
		
		JLabel lblNewLabel_3 = new JLabel("NOM Filliere :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_3.setBounds(12, 243, 138, 33);
		contentPane.add(lblNewLabel_3);
		
		textFNF = new JTextField();
		textFNF.setBounds(135, 243, 208, 33);
		contentPane.add(textFNF);
		textFNF.setColumns(10);
		
		JComboBox combTYp = new JComboBox();
		combTYp.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		combTYp.setBounds(135, 302, 208, 33);
		contentPane.add(combTYp);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom = textFNF.getText();
				String type = combTYp.getSelectedItem().toString();
				try {
					pst = con.prepareStatement("INSERT INTO filliere(nom, type)VALUES(?,?)");
					pst.setString(1, nom);
					pst.setString(2, type);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, " filliere is created");
					load();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(12, 594, 190, 58);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
	              int row = tableFil.getSelectedRow();
	              int id = Integer.parseInt(tableFil.getValueAt(row, 0).toString());
				
				try {
					pst = con.prepareStatement("delete from filliere where id_filiere = '"+id+"' ");
					//pst.setInt(1,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"filliere is delated ");
					load();
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnDelete.setBounds(437, 594, 190, 58);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	              int row = tableFil.getSelectedRow();
	              int id = Integer.parseInt(tableFil.getValueAt(row, 0).toString());
				String nom = textFNF.getText();
				String type = combTYp.getSelectedItem().toString();
				try {
					pst = con.prepareStatement("update filliere set nom =?, type =?  where id_filiere=?");
					pst.setString(1, nom);
					pst.setString(2, type);
					pst.setInt(3,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"filliere is updated");
					load();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(228, 594, 190, 58);
		contentPane.add(btnUpdate);
		
		tableFil = new JTable();
		tableFil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			   int row = tableFil.getSelectedRow();
			    textFNF.setText(tableFil.getValueAt(row, 1).toString());
			    combTYp.setSelectedItem(tableFil.getValueAt(row, 2).toString());
			    
			    
			}
		});
		tableFil.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "NOM", "Type"},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		tableFil.setBounds(445, 120, 682, 348);
		contentPane.add(tableFil);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblType.setBounds(12, 305, 138, 33);
		contentPane.add(lblType);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\light-green-color-solid-background-1920x1080.png"));
		lblNewLabel.setBounds(0, 102, 1170, 607);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GESTION DES FILLIERE");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 15, 1170, 50);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\fond-texture-abstrait.jpg"));
		lblNewLabel_2.setBounds(0, 0, 1188, 688);
		contentPane.add(lblNewLabel_2);
		
	}
}
