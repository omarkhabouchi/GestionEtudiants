import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MenuAdministrateur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrateur frame = new MenuAdministrateur();
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
	public MenuAdministrateur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1088, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\images.png"));
		btnNewButton.setBounds(102, 136, 210, 200);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gestionEtudiant GEtud = new gestionEtudiant();
				GEtud.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\t\u00E9l\u00E9chargement.png"));
		button.setBounds(414, 136, 210, 200);
		contentPane.add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\images.jpg"));
		button_1.setBounds(707, 140, 219, 192);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\t\u00E9l\u00E9chargement (3).png"));
		button_2.setBounds(102, 431, 210, 200);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\t\u00E9l\u00E9chargement (1).png"));
		button_3.setBounds(414, 431, 210, 200);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("New button");
		button_4.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\t\u00E9l\u00E9chargement (2).png"));
		button_4.setBounds(707, 431, 210, 200);
		contentPane.add(button_4);
		
		JLabel lblGestionUsers = new JLabel("Gestion Users");
		lblGestionUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionUsers.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestionUsers.setBounds(102, 349, 210, 33);
		contentPane.add(lblGestionUsers);
		
		JLabel lblGestionDesEtudiants = new JLabel("Gestion des Etudiants");
		lblGestionDesEtudiants.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesEtudiants.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestionDesEtudiants.setBounds(414, 349, 210, 33);
		contentPane.add(lblGestionDesEtudiants);
		
		JLabel lblGestionDesFilliere = new JLabel("Gestion des Filliere");
		lblGestionDesFilliere.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesFilliere.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestionDesFilliere.setBounds(707, 349, 210, 33);
		contentPane.add(lblGestionDesFilliere);
		
		JLabel lblGestionDesAbsences = new JLabel("Gestion des Absences");
		lblGestionDesAbsences.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesAbsences.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestionDesAbsences.setBounds(102, 647, 210, 33);
		contentPane.add(lblGestionDesAbsences);
		
		JLabel lblGestionDesMatiere = new JLabel("Gestion des Matiere");
		lblGestionDesMatiere.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesMatiere.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestionDesMatiere.setBounds(414, 644, 210, 33);
		contentPane.add(lblGestionDesMatiere);
		
		JLabel lblGestionDesNotes = new JLabel("Gestion des Notes");
		lblGestionDesNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDesNotes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestionDesNotes.setBounds(707, 644, 210, 33);
		contentPane.add(lblGestionDesNotes);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\light-green-color-solid-background-1920x1080.png"));
		lblNewLabel.setBounds(0, 86, 1070, 607);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GESTION DES ETUDIANTS");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 15, 1070, 50);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\omark\\Desktop\\fond-texture-abstrait.jpg"));
		lblNewLabel_2.setBounds(0, 0, 1070, 88);
		contentPane.add(lblNewLabel_2);
	}
}
