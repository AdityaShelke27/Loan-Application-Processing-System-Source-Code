import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Loan_Entry_Window 
{
	private String officer_id;
	private JFrame frame;
	private JTextField LoanApplicantName_TF, LoanApplicationAmount_TF, POI_TF;
	private JLabel MinAvailLoan_L, MaxAvailLoan_L;
	private JComboBox<String> LoanProduct_CB, POI_CB, LoanType_CB, POA_CB, Occupation_CB, Status_CB, LoanSelect_CB;
	private static JPanel ApprovePanel;
	private JTextArea Address_TA;
	private long minAmount, maxAmount;
	private long[][] loans = {
								{200000, 500000},
								{10000, 3000000},
								{50000, 5000000},
								{100000, 50000000},
								{20000, 2000000}
							 };
	private static JTable dataTable;
    private DefaultTableModel tableModel;
	private ArrayList<String[]> data;
	private int tableRowHeight = 30, tableColumnWidth = 150;
	private JScrollPane scrollPane;
	private String[] columnStrings = {"Application ID", "Applicant Name", "Loan Type", "POI Type", "POI ID", "POA Type", "Address", "Loan Product", "Loan Amount", "Occupation", "Status", "Officer Name", "Action"};
	private JLabel ApproveAuth_L;
	private static JButton ApproveSave_Btn, ApproveCancel_Btn;
	public static int currentRowNum;
	public static boolean whatToDo;
	private static JTextArea textArea;
	public static String authName;
	private static JComboBox<String> ApproveAuth_CB;
	
	public Loan_Entry_Window(String offID) {
		officer_id = offID;
		initialize();
		try 
		{
			frame.setVisible(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 792, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel loanEntryJPanel = new JPanel();
		JPanel loanSanctionJPanel = new JPanel();
		frame.getContentPane().add(tabbedPane);
		tabbedPane.add("Loan Entry", loanEntryJPanel);
		tabbedPane.add("Loan Sanction", loanSanctionJPanel);
		GridBagLayout gbl_loanSanctionJPanel = new GridBagLayout();
		gbl_loanSanctionJPanel.columnWidths = new int[]{237, 122, 39, 56, 307, 0};
		gbl_loanSanctionJPanel.rowHeights = new int[]{31, 38, 234, 117, 0};
		gbl_loanSanctionJPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_loanSanctionJPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		loanSanctionJPanel.setLayout(gbl_loanSanctionJPanel);
		
		JLabel LoanSelect_L = new JLabel("Loan Product");
		LoanSelect_L.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_LoanSelect_L = new GridBagConstraints();
		gbc_LoanSelect_L.anchor = GridBagConstraints.EAST;
		gbc_LoanSelect_L.fill = GridBagConstraints.VERTICAL;
		gbc_LoanSelect_L.insets = new Insets(0, 0, 5, 5);
		gbc_LoanSelect_L.gridx = 0;
		gbc_LoanSelect_L.gridy = 0;
		loanSanctionJPanel.add(LoanSelect_L, gbc_LoanSelect_L);
		
		LoanSelect_CB = new JComboBox<String>();
		String[] loanProd = {"All", "Home Loan", "Personal Loan", "Auto Loan", "Business Loan", "Student Loan"};
		LoanSelect_CB.setModel(new DefaultComboBoxModel<String>(loanProd));
		LoanSelect_CB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_LoanSelect_CB = new GridBagConstraints();
		gbc_LoanSelect_CB.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoanSelect_CB.insets = new Insets(0, 0, 5, 5);
		gbc_LoanSelect_CB.gridx = 1;
		gbc_LoanSelect_CB.gridy = 0;
		loanSanctionJPanel.add(LoanSelect_CB, gbc_LoanSelect_CB);
		LoanSelect_CB.addActionListener(arg ->
		{
			filterItems();
		});
		
		data = new ArrayList<>();
        tableModel = new DefaultTableModel(data.toArray(new String[0][]), columnStrings);
        dataTable = new JTable(tableModel);
		
		JLabel Status_L = new JLabel("Status");
		Status_L.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_Status_L = new GridBagConstraints();
		gbc_Status_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_Status_L.insets = new Insets(0, 0, 5, 5);
		gbc_Status_L.gridx = 3;
		gbc_Status_L.gridy = 0;
		loanSanctionJPanel.add(Status_L, gbc_Status_L);
		
		Status_CB = new JComboBox<String>();
		Status_CB.setModel(new DefaultComboBoxModel<String>(new String[] {"Pending", "Sanctioned", "Rejected"}));
		GridBagConstraints gbc_Status_CB = new GridBagConstraints();
		gbc_Status_CB.anchor = GridBagConstraints.WEST;
		gbc_Status_CB.insets = new Insets(0, 0, 5, 0);
		gbc_Status_CB.gridx = 4;
		gbc_Status_CB.gridy = 0;
		loanSanctionJPanel.add(Status_CB, gbc_Status_CB);
		Status_CB.addActionListener(arg ->
		{
			filterItems();
		});
		scrollPane = new JScrollPane(dataTable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		loanSanctionJPanel.add(scrollPane, gbc_scrollPane);
		
		ApprovePanel = new JPanel();
		GridBagConstraints gbc_ApprovePanel = new GridBagConstraints();
		gbc_ApprovePanel.fill = GridBagConstraints.BOTH;
		gbc_ApprovePanel.gridwidth = 5;
		gbc_ApprovePanel.gridx = 0;
		gbc_ApprovePanel.gridy = 3;
		loanSanctionJPanel.add(ApprovePanel, gbc_ApprovePanel);
		GridBagLayout gbl_ApprovePanel = new GridBagLayout();
		gbl_ApprovePanel.columnWidths = new int[]{61, 46, 99, 241, 61, 89, 0, 0};
		gbl_ApprovePanel.rowHeights = new int[]{23, 14, 23, 23, 0, 0};
		gbl_ApprovePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_ApprovePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		ApprovePanel.setLayout(gbl_ApprovePanel);
		ApprovePanel.setVisible(false);
		
		ApproveAuth_L = new JLabel("Approve Authority");
		ApproveAuth_L.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_ApproveAuth_L = new GridBagConstraints();
		gbc_ApproveAuth_L.anchor = GridBagConstraints.EAST;
		gbc_ApproveAuth_L.fill = GridBagConstraints.VERTICAL;
		gbc_ApproveAuth_L.insets = new Insets(0, 0, 5, 5);
		gbc_ApproveAuth_L.gridwidth = 2;
		gbc_ApproveAuth_L.gridx = 1;
		gbc_ApproveAuth_L.gridy = 0;
		ApprovePanel.add(ApproveAuth_L, gbc_ApproveAuth_L);
		
		ApproveAuth_CB = new JComboBox<String>();
		GridBagConstraints gbc_ApproveAuth_CB = new GridBagConstraints();
		gbc_ApproveAuth_CB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ApproveAuth_CB.anchor = GridBagConstraints.SOUTH;
		gbc_ApproveAuth_CB.insets = new Insets(0, 0, 5, 5);
		gbc_ApproveAuth_CB.gridx = 3;
		gbc_ApproveAuth_CB.gridy = 0;
		ApprovePanel.add(ApproveAuth_CB, gbc_ApproveAuth_CB);
		
		JLabel lblNewLabel = new JLabel("Notes :-");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		ApprovePanel.add(lblNewLabel, gbc_lblNewLabel);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridheight = 3;
		gbc_textArea.gridwidth = 2;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		ApprovePanel.add(textArea, gbc_textArea);
		
		ApproveSave_Btn = new JButton("Save");
		GridBagConstraints gbc_ApproveSave_Btn = new GridBagConstraints();
		gbc_ApproveSave_Btn.anchor = GridBagConstraints.NORTH;
		gbc_ApproveSave_Btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_ApproveSave_Btn.insets = new Insets(0, 0, 5, 5);
		gbc_ApproveSave_Btn.gridx = 5;
		gbc_ApproveSave_Btn.gridy = 2;
		ApprovePanel.add(ApproveSave_Btn, gbc_ApproveSave_Btn);
		ApproveSave_Btn.addActionListener(a -> {
			if(whatToDo)
			{
				processLoan(currentRowNum, true);
				createDataTable();
			}
			else 
			{
				processLoan(currentRowNum, false);
				createDataTable();
			}
		});
		
		ApproveCancel_Btn = new JButton("Cancel");
		GridBagConstraints gbc_ApproveCancel_Btn = new GridBagConstraints();
		gbc_ApproveCancel_Btn.insets = new Insets(0, 0, 5, 5);
		gbc_ApproveCancel_Btn.anchor = GridBagConstraints.NORTH;
		gbc_ApproveCancel_Btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_ApproveCancel_Btn.gridx = 5;
		gbc_ApproveCancel_Btn.gridy = 3;
		ApprovePanel.add(ApproveCancel_Btn, gbc_ApproveCancel_Btn);
		ApproveCancel_Btn.addActionListener(a -> {
			ApprovePanel.setVisible(false);
		});
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{38, 50, 45, 25, 68, 159, 71, 118, 125, 0};
		gridBagLayout.rowHeights = new int[]{30, 23, 43, 20, 20, 20, 20, 20, 95, 46, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		loanEntryJPanel.setLayout(gridBagLayout);
		
		JLabel LoanType_L = new JLabel("Loan Type");
		GridBagConstraints gbc_LoanType_L = new GridBagConstraints();
		gbc_LoanType_L.anchor = GridBagConstraints.WEST;
		gbc_LoanType_L.insets = new Insets(0, 0, 5, 5);
		gbc_LoanType_L.gridx = 1;
		gbc_LoanType_L.gridy = 1;
		loanEntryJPanel.add(LoanType_L, gbc_LoanType_L);
		
		LoanType_CB = new JComboBox<String>();
		LoanType_CB.setModel(new DefaultComboBoxModel<String>(new String[] {"Secure", "Unsecure"}));
		GridBagConstraints gbc_LoanType_CB = new GridBagConstraints();
		gbc_LoanType_CB.anchor = GridBagConstraints.NORTHWEST;
		gbc_LoanType_CB.insets = new Insets(0, 0, 5, 5);
		gbc_LoanType_CB.gridwidth = 2;
		gbc_LoanType_CB.gridx = 2;
		gbc_LoanType_CB.gridy = 1;
		loanEntryJPanel.add(LoanType_CB, gbc_LoanType_CB);
		
		JLabel LoanProduct_L = new JLabel("Loan Product");
		GridBagConstraints gbc_LoanProduct_L = new GridBagConstraints();
		gbc_LoanProduct_L.anchor = GridBagConstraints.EAST;
		gbc_LoanProduct_L.insets = new Insets(0, 0, 5, 5);
		gbc_LoanProduct_L.gridx = 7;
		gbc_LoanProduct_L.gridy = 1;
		loanEntryJPanel.add(LoanProduct_L, gbc_LoanProduct_L);
		
		LoanProduct_CB = new JComboBox<String>();
		LoanProduct_CB.setModel(new DefaultComboBoxModel<String>(new String[] {"Home Loan", "Personal Loan", "Auto Loan", "Business Loan", "Student Loan"}));
		GridBagConstraints gbc_LoanProduct_CB = new GridBagConstraints();
		gbc_LoanProduct_CB.anchor = GridBagConstraints.SOUTH;
		gbc_LoanProduct_CB.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoanProduct_CB.insets = new Insets(0, 0, 5, 0);
		gbc_LoanProduct_CB.gridx = 8;
		gbc_LoanProduct_CB.gridy = 1;
		loanEntryJPanel.add(LoanProduct_CB, gbc_LoanProduct_CB);
		LoanProduct_CB.addActionListener(arg ->
		{
			allotLoanCoverage(LoanProduct_CB.getSelectedIndex());
		});
		
		JLabel LoanApplicantName_L = new JLabel("Loan Applicant Name");
		GridBagConstraints gbc_LoanApplicantName_L = new GridBagConstraints();
		gbc_LoanApplicantName_L.anchor = GridBagConstraints.WEST;
		gbc_LoanApplicantName_L.insets = new Insets(0, 0, 5, 5);
		gbc_LoanApplicantName_L.gridwidth = 2;
		gbc_LoanApplicantName_L.gridx = 1;
		gbc_LoanApplicantName_L.gridy = 3;
		loanEntryJPanel.add(LoanApplicantName_L, gbc_LoanApplicantName_L);
		
		LoanApplicantName_TF = new JTextField();
		GridBagConstraints gbc_LoanApplicantName_TF = new GridBagConstraints();
		gbc_LoanApplicantName_TF.anchor = GridBagConstraints.NORTH;
		gbc_LoanApplicantName_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoanApplicantName_TF.insets = new Insets(0, 0, 5, 5);
		gbc_LoanApplicantName_TF.gridwidth = 2;
		gbc_LoanApplicantName_TF.gridx = 4;
		gbc_LoanApplicantName_TF.gridy = 3;
		loanEntryJPanel.add(LoanApplicantName_TF, gbc_LoanApplicantName_TF);
		LoanApplicantName_TF.setColumns(10);
		
		JLabel LoanApplicationAmount_L = new JLabel("Loan Application Amount");
		LoanApplicationAmount_L.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_LoanApplicationAmount_L = new GridBagConstraints();
		gbc_LoanApplicationAmount_L.anchor = GridBagConstraints.WEST;
		gbc_LoanApplicationAmount_L.insets = new Insets(0, 0, 5, 5);
		gbc_LoanApplicationAmount_L.gridx = 7;
		gbc_LoanApplicationAmount_L.gridy = 4;
		loanEntryJPanel.add(LoanApplicationAmount_L, gbc_LoanApplicationAmount_L);
		
		LoanApplicationAmount_TF = new JTextField();
		GridBagConstraints gbc_LoanApplicationAmount_TF = new GridBagConstraints();
		gbc_LoanApplicationAmount_TF.anchor = GridBagConstraints.NORTH;
		gbc_LoanApplicationAmount_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_LoanApplicationAmount_TF.insets = new Insets(0, 0, 5, 0);
		gbc_LoanApplicationAmount_TF.gridx = 8;
		gbc_LoanApplicationAmount_TF.gridy = 4;
		loanEntryJPanel.add(LoanApplicationAmount_TF, gbc_LoanApplicationAmount_TF);
		LoanApplicationAmount_TF.setColumns(10);
		
		JLabel POI_L = new JLabel("POI (Proof Of Identity)");
		GridBagConstraints gbc_POI_L = new GridBagConstraints();
		gbc_POI_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_POI_L.insets = new Insets(0, 0, 5, 5);
		gbc_POI_L.gridwidth = 3;
		gbc_POI_L.gridx = 1;
		gbc_POI_L.gridy = 5;
		loanEntryJPanel.add(POI_L, gbc_POI_L);
		
		POI_CB = new JComboBox<String>();
		POI_CB.setModel(new DefaultComboBoxModel<String>(new String[] {"PAN", "AADHAR", "OTHER"}));
		GridBagConstraints gbc_POI_CB = new GridBagConstraints();
		gbc_POI_CB.anchor = GridBagConstraints.NORTHWEST;
		gbc_POI_CB.insets = new Insets(0, 0, 5, 5);
		gbc_POI_CB.gridx = 4;
		gbc_POI_CB.gridy = 5;
		loanEntryJPanel.add(POI_CB, gbc_POI_CB);
		
		POI_TF = new JTextField();
		GridBagConstraints gbc_POI_TF = new GridBagConstraints();
		gbc_POI_TF.anchor = GridBagConstraints.NORTH;
		gbc_POI_TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_POI_TF.insets = new Insets(0, 0, 5, 5);
		gbc_POI_TF.gridx = 5;
		gbc_POI_TF.gridy = 5;
		loanEntryJPanel.add(POI_TF, gbc_POI_TF);
		POI_TF.setColumns(10);
		
		MinAvailLoan_L = new JLabel("");
		GridBagConstraints gbc_MinAvailLoan_L = new GridBagConstraints();
		gbc_MinAvailLoan_L.fill = GridBagConstraints.VERTICAL;
		gbc_MinAvailLoan_L.insets = new Insets(0, 0, 5, 5);
		gbc_MinAvailLoan_L.gridx = 7;
		gbc_MinAvailLoan_L.gridy = 5;
		loanEntryJPanel.add(MinAvailLoan_L, gbc_MinAvailLoan_L);
		
		MaxAvailLoan_L = new JLabel("");
		GridBagConstraints gbc_MaxAvailLoan_L = new GridBagConstraints();
		gbc_MaxAvailLoan_L.fill = GridBagConstraints.VERTICAL;
		gbc_MaxAvailLoan_L.insets = new Insets(0, 0, 5, 0);
		gbc_MaxAvailLoan_L.gridx = 8;
		gbc_MaxAvailLoan_L.gridy = 5;
		loanEntryJPanel.add(MaxAvailLoan_L, gbc_MaxAvailLoan_L);
		
		JLabel POA_L = new JLabel("POA (Proof Of Address)");
		GridBagConstraints gbc_POA_L = new GridBagConstraints();
		gbc_POA_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_POA_L.insets = new Insets(0, 0, 5, 5);
		gbc_POA_L.gridwidth = 3;
		gbc_POA_L.gridx = 1;
		gbc_POA_L.gridy = 6;
		loanEntryJPanel.add(POA_L, gbc_POA_L);
		
		POA_CB = new JComboBox<String>();
		POA_CB.setModel(new DefaultComboBoxModel<String>(new String[] {"Current Address", "Permanent Address"}));
		GridBagConstraints gbc_POA_CB = new GridBagConstraints();
		gbc_POA_CB.anchor = GridBagConstraints.NORTHWEST;
		gbc_POA_CB.insets = new Insets(0, 0, 5, 5);
		gbc_POA_CB.gridwidth = 2;
		gbc_POA_CB.gridx = 4;
		gbc_POA_CB.gridy = 6;
		loanEntryJPanel.add(POA_CB, gbc_POA_CB);
		
		JLabel Occupation_L = new JLabel("Occupation");
		GridBagConstraints gbc_Occupation_L = new GridBagConstraints();
		gbc_Occupation_L.anchor = GridBagConstraints.NORTH;
		gbc_Occupation_L.fill = GridBagConstraints.HORIZONTAL;
		gbc_Occupation_L.insets = new Insets(0, 0, 5, 5);
		gbc_Occupation_L.gridx = 7;
		gbc_Occupation_L.gridy = 7;
		loanEntryJPanel.add(Occupation_L, gbc_Occupation_L);
		
		Occupation_CB = new JComboBox<String>();
		Occupation_CB.setModel(new DefaultComboBoxModel<String>(new String[] {"Salaried", "Self - Employed"}));
		GridBagConstraints gbc_Occupation_CB = new GridBagConstraints();
		gbc_Occupation_CB.anchor = GridBagConstraints.NORTHEAST;
		gbc_Occupation_CB.insets = new Insets(0, 0, 5, 0);
		gbc_Occupation_CB.gridx = 8;
		gbc_Occupation_CB.gridy = 7;
		loanEntryJPanel.add(Occupation_CB, gbc_Occupation_CB);
		
		JLabel Address_L = new JLabel("Address");
		GridBagConstraints gbc_Address_L = new GridBagConstraints();
		gbc_Address_L.anchor = GridBagConstraints.NORTHWEST;
		gbc_Address_L.insets = new Insets(0, 0, 5, 5);
		gbc_Address_L.gridx = 1;
		gbc_Address_L.gridy = 8;
		loanEntryJPanel.add(Address_L, gbc_Address_L);
		
		Address_TA = new JTextArea();
		Address_TA.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_Address_TA = new GridBagConstraints();
		gbc_Address_TA.fill = GridBagConstraints.BOTH;
		gbc_Address_TA.insets = new Insets(0, 0, 5, 5);
		gbc_Address_TA.gridwidth = 3;
		gbc_Address_TA.gridx = 3;
		gbc_Address_TA.gridy = 8;
		loanEntryJPanel.add(Address_TA, gbc_Address_TA);
		
		JButton AddApplication_Btn = new JButton("Add Application");
		GridBagConstraints gbc_AddApplication_Btn = new GridBagConstraints();
		gbc_AddApplication_Btn.anchor = GridBagConstraints.NORTH;
		gbc_AddApplication_Btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddApplication_Btn.insets = new Insets(0, 0, 0, 5);
		gbc_AddApplication_Btn.gridx = 5;
		gbc_AddApplication_Btn.gridy = 10;
		loanEntryJPanel.add(AddApplication_Btn, gbc_AddApplication_Btn);
		AddApplication_Btn.addActionListener(e -> 
		{
			addApplication();
			createDataTable();
		});
		
		JButton Clear_Btn = new JButton("Clear");
		GridBagConstraints gbc_Clear_Btn = new GridBagConstraints();
		gbc_Clear_Btn.anchor = GridBagConstraints.NORTH;
		gbc_Clear_Btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_Clear_Btn.insets = new Insets(0, 0, 0, 5);
		gbc_Clear_Btn.gridx = 7;
		gbc_Clear_Btn.gridy = 10;
		loanEntryJPanel.add(Clear_Btn, gbc_Clear_Btn);
		Clear_Btn.addActionListener(a -> {
			LoanApplicantName_TF.setText("");
			LoanApplicationAmount_TF.setText("");
			POI_TF.setText("");
			Address_TA.setText("");
		});
		
		createDataTable();
		
	}
	
	public static void processLoan(int rowNum, boolean isApprove)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			Statement query = conn.createStatement();
			System.out.println(dataTable.getValueAt(rowNum, 0).toString());
			int id = Integer.parseInt(dataTable.getValueAt(rowNum, 0).toString());
			String sanctionApplication = "";
			if(isApprove)
			{
				sanctionApplication = "Update Applications set status = 1 where application_id = " + id;
			}
			else 
			{
				sanctionApplication = "Update Applications set status = 2 where application_id = " + id;
			}
			query.executeUpdate(sanctionApplication);
			
			String note = "Update Applications set Notes = '"+ textArea.getText() +"' where application_id = " + id;
			query.executeUpdate(note);
			ApprovePanel.setVisible(false);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static String[] getOfficers(String authname)
	{
		ArrayList<String> officers = new ArrayList<>();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			
			Statement query = conn.createStatement();
			
			String addApplication = "Select USER_ID from EMPLOYEE WHERE USER_ID != '" + authname + "';";
			ResultSet res = query.executeQuery(addApplication);
			while(res.next())
			{
				officers.add(res.getString(1));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return officers.toArray(new String[officers.size()]);
	}
	public void filterItems(){
		try {
			String selectedProduct = (String) LoanSelect_CB.getSelectedItem();
	        String selectedStatus = (String) Status_CB.getSelectedItem();
	        ArrayList<String[]> filteredData = new ArrayList<>();
	        for (String[] row : data) {
	            String product = row[7];
	            String status = row[10];
	            if ((selectedProduct.equals("All") || product.equals(selectedProduct)) && status.equals(selectedStatus)) {
	            	filteredData.add(row);
	            	String[] colStrings = {"Application ID", "Applicant Name", "Loan Type", "POI Type", 
	            			"POI ID", "POA Type", "Address", "Loan Product", "Loan Amount", "Occupation", "Status", "Officer Name", "Notes"};
	            	if(status == "Sanctioned" || status == "Rejected"){
	            		tableModel.setColumnIdentifiers(colStrings);
	            	}
	            	else {
	            		tableModel.setColumnIdentifiers(columnStrings);
	                    dataTable.getColumnModel().getColumn(columnStrings.length - 1).setCellRenderer(new ButtonPanelRenderer());
	                    dataTable.getColumnModel().getColumn(columnStrings.length - 1).setCellEditor(new ButtonPanelEditor());
					}
	            }
	        }
	        tableModel.setRowCount(0);
	        for (String[] row : filteredData) {
	            tableModel.addRow(row);
	        }
	        dataTable.setRowHeight(tableRowHeight);
	        dataTable.getColumnModel().getColumn(dataTable.getColumnCount() - 1).setPreferredWidth(tableColumnWidth);
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void allotLoanCoverage(int loanType)
	{
		minAmount = loans[loanType][0];
		maxAmount = loans[loanType][1];
		
        String s = formatAmount(Long.toString(minAmount));
        String s2 = formatAmount(Long.toString(maxAmount));
        
		MinAvailLoan_L.setText("Min ₹" + s);
		MaxAvailLoan_L.setText("Max ₹ " + s2);
	}
	
	public String formatAmount(String s)
	{
		StringBuilder sb = new StringBuilder(s);
		if(sb.length() > 3)
		{
			sb.insert(sb.length() - 3, ',');
		}
		boolean canDo = true;
		for(int i = sb.length() - 5; i > 0; i--)
		{
			canDo = !canDo;
			
			if(canDo)
			{
				sb.insert(i, ',');
			}
		}
		
		return sb.toString();
	}
	
	public boolean checkPOI()
	{
		switch(POI_CB.getSelectedIndex())
		{
			case 0:
				if(!doPANCheck())
				{
					String panCorrectionString = "The format for PAN Number is Incorrect";
					new InvalidInputWindow(panCorrectionString);
					
					return false;
				}
				break;
			
			case 1:
				if(!doAADHARCheck())
				{
					String aadharCorrectionString = "The format for AADHAR Number is Incorrect";
					new InvalidInputWindow(aadharCorrectionString);
					
					return false;
				}
				break;
				
			case 2:
				break;
		}
		
		return true;
	}
	
	public boolean doAADHARCheck()
	{
		String aadharCheckString = "\\d{12}";
		Pattern pattern = Pattern.compile(aadharCheckString);
		Matcher matcher = pattern.matcher(POI_TF.getText());
		if(matcher.matches())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean doPANCheck()
	{
		String panCheckString = "[A-Z]{5}\\d{4}[A-Z]{1}";
		Pattern pattern = Pattern.compile(panCheckString);
		Matcher matcher = pattern.matcher(POI_TF.getText());
		if(matcher.matches())
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean checkApplicationAmount()
	{
		try 
		{
			long amount = Long.parseLong(LoanApplicationAmount_TF.getText());
			
			if(!(amount >=  minAmount && amount <= maxAmount))
			{
				String amountCorrectionString = "The amount must be between the given Minimum and Maximum Loan Amount.";
				new InvalidInputWindow(amountCorrectionString);
				
				return false;
			}
		} 
		catch (NumberFormatException e) 
		{
			String amountCorrectionString = "The amount must be in number.";
			new InvalidInputWindow(amountCorrectionString);
			
			return false;
		}
		
		return true;
	}
	
	public void addApplication()
	{
		if(!checkPOI())
		{
			return;
		}
		else if(!checkApplicationAmount())
		{
			return;
		}
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			
			Statement query = conn.createStatement();
			
			String addApplication = "INSERT INTO `applications`(`applicant_name`, `loan_type`, `poi_type`, `poi_id`, `poa_type`, `address`, `loan_product`, `loan_amount`, `occupation`, `status`, `officer_id`) VALUES ('"+LoanApplicantName_TF.getText()+"','"+LoanType_CB.getSelectedItem()+"','"+POI_CB.getSelectedItem()+"','"+POI_TF.getText()+"','"+POA_CB.getSelectedItem()+"','"+Address_TA.getText()+"','"+LoanProduct_CB.getSelectedItem()+"','"+Integer.parseInt(LoanApplicationAmount_TF.getText())+"','"+Occupation_CB.getSelectedItem()+"','"+0+"','"+officer_id+"')";
			query.executeUpdate(addApplication);
			
			String getID = "SELECT LAST_INSERT_ID()"; 
			
			ResultSet resultSet = query.executeQuery(getID);
			resultSet.next();
			new ApplicationAdded(resultSet.getString(1));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void createDataTable()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LoanApplicationDB", "root", "");
			Statement query = conn.createStatement();
			String showAllData = "SELECT * FROM APPLICATIONS";
			ResultSet resultSet = query.executeQuery(showAllData);
			ResultSetMetaData mData = (ResultSetMetaData) resultSet.getMetaData();
			data.clear();
			while(resultSet.next())
			{
				String[] row = new String[mData.getColumnCount() + 1];
				for(int i = 1; i <= mData.getColumnCount(); i++)
				{
					if(i == mData.getColumnCount() - 2)
					{
						System.out.println(resultSet.getString(i));
						switch(resultSet.getString(i))
						{
							case "0":
								row[i - 1] = "Pending";
								break;
							
							case "1":
								row[i - 1] = "Sanctioned";
								break;
							
							case "2":
								row[i - 1] = "Rejected";
						}
					}
					else 
					{
						row[i - 1] = resultSet.getString(i);
					}
				}
				data.add(row);
			}
	        filterItems();
	        
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	static class ButtonPanelRenderer extends JPanel implements TableCellRenderer {
	    private JButton button1;
	    private JButton button2;
	    public ButtonPanelRenderer() {
	        setOpaque(true);
	        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
	        button1 = new JButton("Sanction");
	        button2 = new JButton("Reject");
	        add(button1);
	        add(button2);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        return this;
	    }
	}

	static class ButtonPanelEditor extends DefaultCellEditor {
	    private JPanel panel;
	    private JButton button1;
	    private JButton button2;
	    private String label;

	    public ButtonPanelEditor() {
	        super(new JTextField());
	        panel = new JPanel();
	        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
	        button1 = new JButton("Sanction");
	        button2 = new JButton("Reject");
	        panel.add(button1);
	        panel.add(button2);
	        button1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fireEditingStopped();
	                currentRowNum =  dataTable.getSelectedRow();
	                whatToDo = true;
	                ApprovePanel.setVisible(true);
	                String s = dataTable.getValueAt(currentRowNum, 11).toString();
	                ApproveAuth_CB.setModel(new DefaultComboBoxModel<String>(getOfficers(s)));
	            }
	        });
	        button2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fireEditingStopped();
	                currentRowNum =  dataTable.getSelectedRow();
	                whatToDo = false;
	                ApprovePanel.setVisible(true);
	            }
	        });
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        return panel;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        return label;
	    }
	}
}