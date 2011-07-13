package form;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.*;
import uties.*;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class frmSettings extends JDialog {

	private static final long serialVersionUID = 1L;
	private final String title = "Setting";
	
	private JPanel jContentPane;
	private JLabel jLabel, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8;
	private JButton cmdChooseFolder;
	private JTextField txtLicense, txtApplicationID, txtUsername, txtHostServer, txtPriceServer, 
			txtTimeRefresh, txtDebugFlag, txtFileDirectory;
	private JButton cmdOk, cmdExit;
	private JFileChooser chooser;
	private JPasswordField txtPassword;

	public frmSettings() {
		initialize();
	}

	public frmSettings(Frame owner) {
		super(owner);
		initialize();
	}

	private void initialize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(419, 349);
		setContentPane(getJContentPane());
		setTitle(title);
		setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2);
		setResizable(false);	
		setModal(true);
		LoadSettings();
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(13, 255, 108, 17));
			jLabel8.setText("Debug Flag");
			
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(12, 223, 109, 18));
			jLabel7.setText("Refresh Time");
			
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(14, 192, 97, 15));
			jLabel6.setText("Price Server");
			
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(14, 162, 96, 16));
			jLabel5.setText("Host Server");
			
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(15, 133, 97, 15));
			jLabel4.setText("API Password");
			
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(14, 103, 93, 16));
			jLabel3.setText("API Username ");
			
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(14, 74, 94, 16));
			jLabel2.setText("Application ID");
			
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 44, 93, 16));
			jLabel1.setText("License");
			
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(13, 16, 100, 16));
			jLabel.setText("Directory");
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTxtFileDirectory(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getCmdChooseFolder(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getTxtLicense(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getTxtApplicationID(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getTxtUsername(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getCmdOk(), null);
			jContentPane.add(getCmdExit(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getTxtHostServer(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getTxtPriceServer(), null);
			jContentPane.add(getTxtPassword(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(getTxtTimeRefresh(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(getTxtDebugFlag(), null);
		}
		
		return jContentPane;
	}

	private JTextField getTxtFileDirectory() {
		if (txtFileDirectory == null) {
			txtFileDirectory = new JTextField();
			txtFileDirectory.setBounds(new Rectangle(125, 14, 229, 20));
		}
		
		return txtFileDirectory;
	}

	private JButton getCmdChooseFolder() {
		if (cmdChooseFolder == null) {
			cmdChooseFolder = new JButton();
			cmdChooseFolder.setBounds(new Rectangle(368, 13, 32, 20));
			cmdChooseFolder.setText("...");
			
			cmdChooseFolder.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						 ShowDirChooser();
					}
				}
			);
		}
		
		return cmdChooseFolder;
	}

	private JTextField getTxtLicense() {
		if (txtLicense == null) {
			txtLicense = new JTextField();
			txtLicense.setBounds(new Rectangle(125, 43, 229, 20));
		}
		
		return txtLicense;
	}

	private JTextField getTxtApplicationID() {
		if (txtApplicationID == null) {
			txtApplicationID = new JTextField();
			txtApplicationID.setBounds(new Rectangle(125, 73, 229, 20));
		}
		
		return txtApplicationID;
	}

	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setBounds(new Rectangle(124, 102, 230, 20));
		}
		
		return txtUsername;
	}

	private JButton getCmdOk() {
		if (cmdOk == null) {
			cmdOk = new JButton();
			cmdOk.setBounds(new Rectangle(141, 282, 95, 25));
			cmdOk.setText("Accept");
			
			cmdOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(
						txtFileDirectory.getText().trim().isEmpty()
						|| txtApplicationID.getText().trim().isEmpty()
						|| txtLicense.getText().trim().isEmpty()
						|| txtUsername.getText().trim().isEmpty()
						|| txtPassword.getPassword().toString().trim().isEmpty()
						|| txtHostServer.getText().trim().isEmpty()
						|| txtPriceServer.getText().trim().isEmpty()
						|| txtTimeRefresh.getText().trim().isEmpty()
						|| txtDebugFlag.getText().trim().isEmpty()
					) {
						JOptionPane.showMessageDialog(
							null, "You have to input all values!", "Message", JOptionPane.WARNING_MESSAGE
						);
						return;
					}
					
					if(!Uties.CheckInteger(txtTimeRefresh.getText().trim())) {
						JOptionPane.showMessageDialog(
							null, "Input value must be integer!", "Message", JOptionPane.WARNING_MESSAGE
						);
						return;
					}
					
					if(!Uties.CheckInteger(txtDebugFlag.getText().trim())) {
						JOptionPane.showMessageDialog(
							null, "Input value must be integer!", "Message", JOptionPane.WARNING_MESSAGE
						);
						return;
					}
					
					if(
						JOptionPane.showConfirmDialog(
							null, "Do you really want to save?", "Message", JOptionPane.YES_NO_OPTION) == 0
					) {
						SaveSettings();
					}
				}
			});
		}
		
		return cmdOk;
	}

	private JButton getCmdExit() {
		if (cmdExit == null) {
			cmdExit = new JButton();
			cmdExit.setBounds(new Rectangle(249, 282, 92, 25));
			cmdExit.setText("Exit");
			
			cmdExit.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						Exit();
					}
				}
			);
		}
		
		return cmdExit;
	}
	
	private JTextField getTxtHostServer() {
		if (txtHostServer == null) {
			txtHostServer = new JTextField();
			txtHostServer.setBounds(new Rectangle(124, 160, 229, 21));
		}
		
		return txtHostServer;
	}

	private JTextField getTxtPriceServer() {
		if (txtPriceServer == null) {
			txtPriceServer = new JTextField();
			txtPriceServer.setBounds(new Rectangle(124, 191, 229, 20));
		}
		
		return txtPriceServer;
	}

	private JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
			txtPassword.setBounds(new Rectangle(124, 131, 228, 18));
		}
		
		return txtPassword;
	}

	private JTextField getTxtTimeRefresh() {
		if (txtTimeRefresh == null) {
			txtTimeRefresh = new JTextField();
			txtTimeRefresh.setBounds(new Rectangle(124, 222, 230, 19));
		}
		
		return txtTimeRefresh;
	}

	private JTextField getTxtDebugFlag() {
		if (txtDebugFlag == null) {
			txtDebugFlag = new JTextField();
			txtDebugFlag.setBounds(new Rectangle(124, 254, 229, 18));
		}
		
		return txtDebugFlag;
	}
	
	private void ShowDirChooser() {
		chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		 
		chooser.setAcceptAllFileFilterUsed(false);

		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
			txtFileDirectory.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	private void SaveSettings() {
		ConfigInfo info = new ConfigInfo();
		
		info.FileDirectory = txtFileDirectory.getText().trim();
		info.ApplicationID = txtApplicationID.getText().trim();
		info.License = txtLicense.getText().trim();
		info.Username = txtUsername.getText().trim();
		info.Password = new String(txtPassword.getPassword()).trim();
		info.HostServer =  txtHostServer.getText().trim();
		info.PriceServer =  txtPriceServer.getText().trim();
		info.TimeRefresh = Integer.parseInt(txtTimeRefresh.getText().trim());
		info.DebugFlag = Integer.parseInt(txtDebugFlag.getText().trim());
		
		if(Config.SaveSettings(info)) {
			JOptionPane.showMessageDialog(null, "Saved successfully");
		} else {
			JOptionPane.showMessageDialog(
				null, "There are some errors during saving process. Please check again!", "Messave", 
				JOptionPane.WARNING_MESSAGE
			);
		}
	}
	
	private void LoadSettings() {
		ConfigInfo info = Config.LoadSettings();
		if(info==null) {
			return;
		}
		
		txtFileDirectory.setText(info.FileDirectory);
		txtApplicationID.setText(info.ApplicationID);
		txtLicense.setText(info.License);
		txtUsername.setText(info.Username);
		txtPassword.setText(info.Password);
		txtHostServer.setText(info.HostServer);
		txtPriceServer.setText(info.PriceServer);
		txtTimeRefresh.setText(Integer.toString(info.TimeRefresh));
		txtDebugFlag.setText(Integer.toString(info.DebugFlag));
	}

	private void Exit() {		
		setVisible(false);
		dispose();
	}
}
