import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frame;
	private JLabel UserID_L;
	private JTextField UserID_TF;
	private JLabel UserPassword_L;
	private JPasswordField UserPassword_Pass;
	private JButton Login_Btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{75, 118, 4, 89, 0, 0};
		gridBagLayout.rowHeights = new int[]{58, 20, 57, 20, 72, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		UserID_L = new JLabel("User ID");
		GridBagConstraints gbc_UserID_L = new GridBagConstraints();
		gbc_UserID_L.fill = GridBagConstraints.BOTH;
		gbc_UserID_L.insets = new Insets(0, 0, 5, 5);
		gbc_UserID_L.gridx = 1;
		gbc_UserID_L.gridy = 1;
		frame.getContentPane().add(UserID_L, gbc_UserID_L);
		
		UserID_TF = new JTextField();
		GridBagConstraints gbc_UserID_TF = new GridBagConstraints();
		gbc_UserID_TF.anchor = GridBagConstraints.NORTH;
		gbc_UserID_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserID_TF.insets = new Insets(0, 0, 5, 5);
		gbc_UserID_TF.gridwidth = 2;
		gbc_UserID_TF.gridx = 2;
		gbc_UserID_TF.gridy = 1;
		frame.getContentPane().add(UserID_TF, gbc_UserID_TF);
		UserID_TF.setColumns(10);
		
		UserPassword_L = new JLabel("Password");
		GridBagConstraints gbc_UserPassword_L = new GridBagConstraints();
		gbc_UserPassword_L.fill = GridBagConstraints.BOTH;
		gbc_UserPassword_L.insets = new Insets(0, 0, 5, 5);
		gbc_UserPassword_L.gridx = 1;
		gbc_UserPassword_L.gridy = 3;
		frame.getContentPane().add(UserPassword_L, gbc_UserPassword_L);
		
		Login_Btn = new JButton("Login");
		Login_Btn.addActionListener(e -> {
			CallLoginVerification(UserID_TF.getText(), String.valueOf(UserPassword_Pass.getPassword()));
		});
		
		UserPassword_Pass = new JPasswordField();
		GridBagConstraints gbc_UserPassword_Pass = new GridBagConstraints();
		gbc_UserPassword_Pass.anchor = GridBagConstraints.NORTH;
		gbc_UserPassword_Pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserPassword_Pass.insets = new Insets(0, 0, 5, 5);
		gbc_UserPassword_Pass.gridwidth = 2;
		gbc_UserPassword_Pass.gridx = 2;
		gbc_UserPassword_Pass.gridy = 3;
		frame.getContentPane().add(UserPassword_Pass, gbc_UserPassword_Pass);
		GridBagConstraints gbc_Login_Btn = new GridBagConstraints();
		gbc_Login_Btn.anchor = GridBagConstraints.NORTH;
		gbc_Login_Btn.insets = new Insets(0, 0, 0, 5);
		gbc_Login_Btn.gridwidth = 2;
		gbc_Login_Btn.gridx = 1;
		gbc_Login_Btn.gridy = 5;
		frame.getContentPane().add(Login_Btn, gbc_Login_Btn);
		
		JButton AddUser_Btn = new JButton("Add User");
		AddUser_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddUserWindow();
			}
		});
		GridBagConstraints gbc_AddUser_Btn = new GridBagConstraints();
		gbc_AddUser_Btn.insets = new Insets(0, 0, 0, 5);
		gbc_AddUser_Btn.anchor = GridBagConstraints.NORTH;
		gbc_AddUser_Btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddUser_Btn.gridx = 3;
		gbc_AddUser_Btn.gridy = 5;
		frame.getContentPane().add(AddUser_Btn, gbc_AddUser_Btn);
	}
	
	public void CallLoginVerification(String u_ID, String u_Password)
	{
		LoginVerification lv = new LoginVerification(u_ID, u_Password);
		
		if(lv.isValidUser)
		{
			frame.setVisible(false);
		}
	}
}
