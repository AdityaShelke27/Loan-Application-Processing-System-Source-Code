import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class ApplicationAdded extends JFrame
{
	public ApplicationAdded(String appID)
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
			
			ApplicationAddedSuccess(appID);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void ApplicationAddedSuccess(String appID) 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 316, 0};
		gridBagLayout.rowHeights = new int[]{48, 25, 25, 34, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel InvalidUser_L = new JLabel("Application Added Successfully");
		InvalidUser_L.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_InvalidUser_L = new GridBagConstraints();
		gbc_InvalidUser_L.anchor = GridBagConstraints.NORTHWEST;
		gbc_InvalidUser_L.insets = new Insets(0, 0, 5, 0);
		gbc_InvalidUser_L.gridx = 1;
		gbc_InvalidUser_L.gridy = 1;
		getContentPane().add(InvalidUser_L, gbc_InvalidUser_L);
		
		JLabel ApplicationID = new JLabel("Application ID :- " + appID);
		GridBagConstraints gbc_ApplicationID = new GridBagConstraints();
		gbc_ApplicationID.fill = GridBagConstraints.VERTICAL;
		gbc_ApplicationID.insets = new Insets(0, 0, 5, 0);
		gbc_ApplicationID.gridx = 1;
		gbc_ApplicationID.gridy = 2;
		getContentPane().add(ApplicationID, gbc_ApplicationID);
		
		JButton Ok_Btn = new JButton("OK");
		GridBagConstraints gbc_Ok_Btn = new GridBagConstraints();
		gbc_Ok_Btn.anchor = GridBagConstraints.NORTH;
		gbc_Ok_Btn.gridx = 1;
		gbc_Ok_Btn.gridy = 4;
		getContentPane().add(Ok_Btn, gbc_Ok_Btn);
		
		Ok_Btn.addActionListener(e -> 
		{
			dispose();
		});
		
		setBounds(100, 100, 400, 250);
		setVisible(true);
	}
}
