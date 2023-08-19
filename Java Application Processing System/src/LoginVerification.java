import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

public class LoginVerification extends JFrame
{
	public boolean isValidUser = false;
	public LoginVerification(String u_ID, String u_Password)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			Statement query = conn.createStatement();
			String checkUserExist = "SELECT COUNT(*) AS resCount FROM EMPLOYEE WHERE USER_ID = '" + u_ID + "' AND USER_PASSWORD = '" + u_Password + "'";
			ResultSet resultSet = query.executeQuery(checkUserExist);
			resultSet.next();
			if(resultSet.getInt("resCount") == 1)
			{
				dispose();
				System.out.println("Welcome User");
				new Loan_Entry_Window(u_ID);
				isValidUser = true;
			}
			else 
			{
				InvalidUser();
				isValidUser = false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void InvalidUser() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30, 90, 30, 30, 30, 30, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 0, 30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel InvalidUser_L = new JLabel("Invalid User");
		InvalidUser_L.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_InvalidUser_L = new GridBagConstraints();
		gbc_InvalidUser_L.insets = new Insets(0, 0, 5, 0);
		gbc_InvalidUser_L.gridx = 5;
		gbc_InvalidUser_L.gridy = 2;
		getContentPane().add(InvalidUser_L, gbc_InvalidUser_L);
		
		JButton Ok_Btn = new JButton("OK");
		GridBagConstraints gbc_Ok_Btn = new GridBagConstraints();
		gbc_Ok_Btn.weighty = 0.0;
		gbc_Ok_Btn.weightx = 0.0;
		gbc_Ok_Btn.gridx = 5;
		gbc_Ok_Btn.gridy = 4;
		getContentPane().add(Ok_Btn, gbc_Ok_Btn);
		
		setBounds(100, 100, 350, 250);
		setVisible(true);
		
		Ok_Btn.addActionListener(e -> 
		{
			dispose();
		});
	}
}
