import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class UserAdded extends JFrame
{
	public UserAdded()
	{
		try
		{
			UserAddedSuccess();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void UserAddedSuccess() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 251, 0};
		gridBagLayout.rowHeights = new int[]{48, 25, 82, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel InvalidUser_L = new JLabel("User Added Successfully");
		InvalidUser_L.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_InvalidUser_L = new GridBagConstraints();
		gbc_InvalidUser_L.anchor = GridBagConstraints.NORTH;
		gbc_InvalidUser_L.insets = new Insets(0, 0, 5, 0);
		gbc_InvalidUser_L.gridx = 1;
		gbc_InvalidUser_L.gridy = 1;
		getContentPane().add(InvalidUser_L, gbc_InvalidUser_L);
		
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
