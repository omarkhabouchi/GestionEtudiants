import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ConnectionMysql {
 Connection con = null;
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection	con = DriverManager.getConnection("jdbc:mysql://localhost/gestionetud","root","");
		    return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
}
