import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AddUserWindow extends JFrame
{
	private JTextField UserID_TF;
	private JTextField UserName_TF;
	private JPasswordField Password_TF;
	private JPasswordField RepPass_TF;
	public AddUserWindow()
	{
		constructInterface();
	}
	
	public void constructInterface()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{79, 127, 145, 0, 0};
		gridBagLayout.rowHeights = new int[]{40, 20, 20, 20, 20, 70, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel UserID_L = new JLabel("User ID");
		UserID_L.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_UserID_L = new GridBagConstraints();
		gbc_UserID_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserID_L.insets = new Insets(0, 0, 5, 5);
		gbc_UserID_L.gridx = 1;
		gbc_UserID_L.gridy = 1;
		getContentPane().add(UserID_L, gbc_UserID_L);
		
		UserID_TF = new JTextField();
		GridBagConstraints gbc_UserID_TF = new GridBagConstraints();
		gbc_UserID_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserID_TF.anchor = GridBagConstraints.NORTH;
		gbc_UserID_TF.insets = new Insets(0, 0, 5, 5);
		gbc_UserID_TF.gridx = 2;
		gbc_UserID_TF.gridy = 1;
		getContentPane().add(UserID_TF, gbc_UserID_TF);
		UserID_TF.setColumns(10);
		
		JLabel UserName_L = new JLabel("User Name");
		UserName_L.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_UserName_L = new GridBagConstraints();
		gbc_UserName_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserName_L.insets = new Insets(0, 0, 5, 5);
		gbc_UserName_L.gridx = 1;
		gbc_UserName_L.gridy = 2;
		getContentPane().add(UserName_L, gbc_UserName_L);
		
		UserName_TF = new JTextField();
		GridBagConstraints gbc_UserName_TF = new GridBagConstraints();
		gbc_UserName_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserName_TF.anchor = GridBagConstraints.NORTH;
		gbc_UserName_TF.insets = new Insets(0, 0, 5, 5);
		gbc_UserName_TF.gridx = 2;
		gbc_UserName_TF.gridy = 2;
		getContentPane().add(UserName_TF, gbc_UserName_TF);
		UserName_TF.setColumns(10);
		
		JLabel Password_L = new JLabel("Password");
		Password_L.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_Password_L = new GridBagConstraints();
		gbc_Password_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_Password_L.insets = new Insets(0, 0, 5, 5);
		gbc_Password_L.gridx = 1;
		gbc_Password_L.gridy = 3;
		getContentPane().add(Password_L, gbc_Password_L);
		
		Password_TF = new JPasswordField();
		GridBagConstraints gbc_Password_TF = new GridBagConstraints();
		gbc_Password_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_Password_TF.anchor = GridBagConstraints.NORTH;
		gbc_Password_TF.insets = new Insets(0, 0, 5, 5);
		gbc_Password_TF.gridx = 2;
		gbc_Password_TF.gridy = 3;
		getContentPane().add(Password_TF, gbc_Password_TF);
		
		JLabel RepPass_L = new JLabel("Repeat Password");
		RepPass_L.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_RepPass_L = new GridBagConstraints();
		gbc_RepPass_L.anchor = GridBagConstraints.SOUTH;
		gbc_RepPass_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_RepPass_L.insets = new Insets(0, 0, 5, 5);
		gbc_RepPass_L.gridx = 1;
		gbc_RepPass_L.gridy = 4;
		getContentPane().add(RepPass_L, gbc_RepPass_L);
		
		JButton AddUser_Btn = new JButton("Add User");
		
		RepPass_TF = new JPasswordField();
		GridBagConstraints gbc_RepPass_TF = new GridBagConstraints();
		gbc_RepPass_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_RepPass_TF.anchor = GridBagConstraints.NORTH;
		gbc_RepPass_TF.insets = new Insets(0, 0, 5, 5);
		gbc_RepPass_TF.gridx = 2;
		gbc_RepPass_TF.gridy = 4;
		getContentPane().add(RepPass_TF, gbc_RepPass_TF);
		GridBagConstraints gbc_AddUser_Btn = new GridBagConstraints();
		gbc_AddUser_Btn.anchor = GridBagConstraints.NORTH;
		gbc_AddUser_Btn.insets = new Insets(0, 0, 0, 5);
		gbc_AddUser_Btn.gridx = 1;
		gbc_AddUser_Btn.gridy = 6;
		getContentPane().add(AddUser_Btn, gbc_AddUser_Btn);
		AddUser_Btn.addActionListener(a -> {
			if(checkInput())
			{
				addRecordToDB();
			}
		});
		
		JButton Close_Btn = new JButton("Close");
		GridBagConstraints gbc_Close_Btn = new GridBagConstraints();
		gbc_Close_Btn.insets = new Insets(0, 0, 0, 5);
		gbc_Close_Btn.anchor = GridBagConstraints.NORTH;
		gbc_Close_Btn.gridx = 2;
		gbc_Close_Btn.gridy = 6;
		getContentPane().add(Close_Btn, gbc_Close_Btn);
		Close_Btn.addActionListener(a -> {
			dispose();
		});
		
		setBounds(100, 100, 512, 332);
		setVisible(true);
	}
	
	public void InvalidUser(String errorName) 
	{
		new InvalidInputWindow(errorName);
	}
	
	boolean checkInput()
	{
		if(String.valueOf(Password_TF.getPassword()).compareTo(String.valueOf(RepPass_TF.getPassword())) != 0)
		{
			InvalidUser("Password does not match with the repeated password.");
			return false;
		}
		else if(UserID_TF.getText().compareTo("") == 0)
		{
			InvalidUser("The User ID cannot be blank.");
			return false;
		}
		else if(UserName_TF.getText().compareTo("") == 0)
		{
			InvalidUser("The User Name cannot be blank.");
			return false;
		}
		else if(String.valueOf(Password_TF.getPassword()).compareTo("") == 0)
		{
			InvalidUser("The Password cannot be blank.");
			return false;
		}
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			
			Statement query = conn.createStatement();
			
			String checkUserExist = "SELECT COUNT(*) AS resCount FROM EMPLOYEE WHERE USER_ID = '" + UserID_TF.getText() + "';";
			ResultSet resultSet = query.executeQuery(checkUserExist);
			resultSet.next();
			if(resultSet.getInt("resCount") >= 1)
			{
				
				InvalidUser("User with the same User ID already exists.");
				return false;
			}
			else 
			{
				userAddSuccess();
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	void addRecordToDB()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			
			Statement query = conn.createStatement();
			
			String addUser = "INSERT INTO EMPLOYEE VALUES('" + UserID_TF.getText() + "', '"+ UserName_TF.getText()+"', '" + String.valueOf(Password_TF.getPassword())+ "');";
			query.executeUpdate(addUser);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void userAddSuccess()
	{
		new UserAdded();
	}
}
