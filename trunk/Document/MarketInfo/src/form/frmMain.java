package form;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import uties.*;

public class frmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String title = "MarketsInfo";
	
	private JPanel jContentPane;
	private JButton cmdRun, cmdStop, cmdSettings, cmdExit, cmdHelp;
	private JTextArea txtLog;
	private JScrollPane scrollPane;
	private ConfigInfo configInfo; 
	private ExternalFeed externalFeed;
	
	public frmMain() {
		initialize();
	}

	private void initialize() {
		setSize(631, 501);
		setContentPane(getJContentPane());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);
		setTitle(title);
		setResizable(false);		
		
		txtLog.setEditable(false);
		cmdRun.setEnabled(true);
		cmdSettings.setEnabled(true);
		cmdStop.setEnabled(false);
		cmdExit.setEnabled(true);		
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getCmdRun(), null);
			jContentPane.add(getCmdStop(), null);
			jContentPane.add(getCmdSettings(), null);
			jContentPane.add(getCmdExit(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getCmdHelp(), null);
		}
		
		return jContentPane;
	}
	
	private JButton getCmdRun() {
		if (cmdRun == null) {
			cmdRun = new JButton();
			cmdRun.setBounds(new Rectangle(14, 426, 100, 29));
			cmdRun.setText("Run");
			
			cmdRun.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						AddLog("Start connection to Patsystems");
						cmdStop.setEnabled(true);
						cmdRun.setEnabled(false);
						cmdSettings.setEnabled(false);
						cmdHelp.setEnabled(false);
						Run();
					}
				}
			);
		}
		
		return cmdRun;
	}

	private JButton getCmdStop() {
		if (cmdStop == null) {
			cmdStop = new JButton();
			cmdStop.setBounds(new Rectangle(143, 426, 100, 28));
			cmdStop.setText("Stop");
			
			cmdStop.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {					
						cmdStop.setEnabled(false);
						externalFeed.stop();
						AddLog("Program has been stopped");
					}
				}
			);
		}
		
		return cmdStop;
	}

	private JButton getCmdSettings() {
		if (cmdSettings == null) {
			cmdSettings = new JButton();
			cmdSettings.setBounds(new Rectangle(277, 426, 110, 31));
			cmdSettings.setText("Configuration");
			
			cmdSettings.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						frmSettings frm = new frmSettings();
						frm.setVisible(true);
						configInfo = Config.LoadSettings();
					}
				}
			);
		}
		return cmdSettings;
	}
	
	private JButton getCmdHelp() {
		if (cmdHelp == null) {
			cmdHelp = new JButton();
			cmdHelp.setBounds(new Rectangle(404, 425, 89, 31));
			cmdHelp.setText("Help");	
			
			cmdHelp.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JOptionPane.showMessageDialog(
							null, "Get market information from Patsystems."
						);
					}
				}
			);
		}
		
		return cmdHelp;
	}
	
	private JButton getCmdExit() {
		if (cmdExit == null) {
			cmdExit = new JButton();
			cmdExit.setBounds(new Rectangle(525, 425, 88, 33));
			cmdExit.setText("Exit");
			
			cmdExit.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {					
						if(
							JOptionPane.showConfirmDialog(
								null, "Do you really want to quit?", "Notification", JOptionPane.YES_NO_OPTION
							) == 0
						) {
							Exit();						
						}
					}
				}
			);
		}
		
		return cmdExit;
	}
	
	private JScrollPane getJScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(new Rectangle(14, 13, 598, 398));
			scrollPane.setViewportView(getTxtLog());
		}
		
		return scrollPane;
	}
	
	private JTextArea getTxtLog() {
		if(txtLog == null) {
			txtLog = new JTextArea();
		}
		
		return txtLog;
	}

	public void AddLog(String message) {
		if(txtLog.getLineCount()>=500) txtLog.setText("");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   	    Date date = new Date();
   	    txtLog.setText(dateFormat.format(date) + "   " + message + "\n\r" + txtLog.getText());
	}
	
	private void Exit() {		
		dispose();
		System.exit(0);
	}
	
	private void Run() {
		configInfo = Config.LoadSettings();
		externalFeed = new ExternalFeed(this, configInfo);
		externalFeed.start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					frmMain thisClass = new frmMain();
					thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					thisClass.setVisible(true);
				}
			}
		);
	}
}