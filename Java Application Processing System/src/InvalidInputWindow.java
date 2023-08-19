import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class InvalidInputWindow extends JFrame
{
	public boolean isValidUser = false;
	public InvalidInputWindow(String errorName)
	{
		try
		{/*
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			
			Statement query = conn.createStatement();
			
			String checkUserExist = "SELECT COUNT(*) AS resCount FROM EMPLOYEE WHERE USER_ID = '" + u_ID + "' AND USER_NAME = '" + u_Name + "' AND USER_PASSWORD = '" + u_Password + "'";
			ResultSet resultSet = query.executeQuery(checkUserExist);
			resultSet.next();
			if(resultSet.getInt("resCount") == 1)
			{
				dispose();
				System.out.println("Welcome User");
				new Loan_Entry_Window();
				isValidUser = true;
			}
			else 
			{
				InvalidUser();
				isValidUser = false;
			}*/
			
			InvalidUser(errorName);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void InvalidUser(String errorName) 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 251, 0};
		gridBagLayout.rowHeights = new int[]{48, 25, 82, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel InvalidUser_L = new JLabel("Invalid Input");
		InvalidUser_L.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_InvalidUser_L = new GridBagConstraints();
		gbc_InvalidUser_L.anchor = GridBagConstraints.NORTH;
		gbc_InvalidUser_L.insets = new Insets(0, 0, 5, 0);
		gbc_InvalidUser_L.gridx = 1;
		gbc_InvalidUser_L.gridy = 1;
		getContentPane().add(InvalidUser_L, gbc_InvalidUser_L);
		
		JLabel ErrorName = new JLabel(errorName);
		ErrorName.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_ErrorName = new GridBagConstraints();
		gbc_ErrorName.fill = GridBagConstraints.BOTH;
		gbc_ErrorName.insets = new Insets(0, 0, 5, 0);
		gbc_ErrorName.gridx = 1;
		gbc_ErrorName.gridy = 2;
		getContentPane().add(ErrorName, gbc_ErrorName);
		
		JButton Ok_Btn = new JButton("OK");
		GridBagConstraints gbc_Ok_Btn = new GridBagConstraints();
		gbc_Ok_Btn.anchor = GridBagConstraints.NORTH;
		gbc_Ok_Btn.gridx = 1;
		gbc_Ok_Btn.gridy = 3;
		getContentPane().add(Ok_Btn, gbc_Ok_Btn);
		
		Ok_Btn.addActionListener(e -> 
		{
			dispose();
		});
		
		setBounds(100, 100, 350, 250);
		setVisible(true);
	}
}
