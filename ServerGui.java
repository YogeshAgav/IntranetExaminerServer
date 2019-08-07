/*This is Server site GUI
 *Starts on 06/03/2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.Component.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener.*;
import javax.swing.border.LineBorder;

public class ServerGui extends JFrame
 {
	JButton sendButton;
	JButton stopButton;
	JButton shutdownButton;
	JButton filesharingButton;
	JButton blockButton;
	JButton unblockButton;
	JButton warningButton;
	JButton replyButton;

	static JTextField warningField;
	static JTextField clnText;
	static JTextField compNameText;
	static JTextField ipAddText;
	static JTextField osNameText;
	static JTextField osVersionText;
	static JTextField userNameTxt;

	JLabel cnoLabel;
	JLabel cnameLabel;
	JLabel OsLabel;
	JLabel cipLabel;
	JLabel usrName;
	JLabel osVer;
	JLabel label = new JLabel();

	protected JPanel contentPane = null;
	static JPanel cPanel = null;
	JPanel panel = new JPanel();

	JMenu subMenu1;

	protected Action startSendImageAction;
	static JScrollPane scroll=null;
	static protected GridBagLayout gridbag = new GridBagLayout();
	static protected GridBagConstraints cons = new GridBagConstraints();

	ServerGui() 
	{

		this.setTitle("INTRANET EXAMINER");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) dimension.getWidth(),(int) dimension.getHeight() - 30);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		menuBar();
		createCenterPanel();
		createBttomPanel();
		createTopPanel();

		this.setVisible(true);
	}

	public void menuBar()
	{
		// menuBar
		JMenuBar menuBar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit");

		// menuBar-MONITOR
		JMenu monitor = new JMenu("Monitor");
		monitor.setMnemonic(KeyEvent.VK_M);
		monitor.getAccessibleContext().setAccessibleDescription(
				"Menu Which has Option Related to the Monitoring the Clients ");
		monitor.setToolTipText("Menu Which has Option Related to the Monitoring the Clients ");
		menuBar.add(monitor);

		// MONITOR-START SENDING C:/Users/YGS/Desktop/srd/IMAGES
		JMenuItem MenuItem_start = new JMenuItem("Start Sending Images");
		MenuItem_start.setMnemonic(KeyEvent.VK_I);
		MenuItem_start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		MenuItem_start
				.setToolTipText("Set Task to Client To Start Sending Images");
		MenuItem_start.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.SEND_IMAGE;
			}
		});
		monitor.add(MenuItem_start);

		// MONITOR-
		JMenuItem MenuItem_stop = new JMenuItem("Stop Sending Images");
		MenuItem_stop.setMnemonic(KeyEvent.VK_S);
		MenuItem_stop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));
		MenuItem_stop
				.setToolTipText("Set Task to Client To Stop Sending Images");
		MenuItem_stop.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.NOTHING;
			}
		});
		monitor.add(MenuItem_stop);

		// MONITOR-SENDING MESSAGE
		JMenu Menu_send = new JMenu("Sharing");

		JMenuItem Menu_file = new JMenuItem("File-Sharing");
		Menu_file.setMnemonic(KeyEvent.VK_F);
		Menu_file.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.ALT_MASK));
		Menu_file.setToolTipText("Click here to Send a File to the Client");
		Menu_file.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.FILE_SHARING;
			}
		});
		Menu_send.add(Menu_file);

		monitor.add(Menu_send);

		// MONITOR-ACTIONS
		JMenu Menu_action = new JMenu("Actions");

		JMenuItem Menu_hang = new JMenuItem("Block Client");
		Menu_hang.setMnemonic(KeyEvent.VK_H);
		Menu_hang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.CTRL_MASK));
		Menu_hang.setToolTipText("Block Client ");

		JMenuItem Menu_shutdown = new JMenuItem("Shutdown");
		Menu_shutdown.setMnemonic(KeyEvent.VK_D);
		Menu_shutdown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		Menu_shutdown.setToolTipText(" Shut Down the  Clients machine");
		Menu_shutdown.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.SHUTDOWN;
			}
		});

		Menu_action.add(Menu_hang);
		Menu_action.add(Menu_shutdown);

		monitor.add(Menu_action);

		// MONITOR-EXIT

		JMenuItem MenuItem_exit = new JMenuItem("Exit", icon);
		MenuItem_exit.setMnemonic(KeyEvent.VK_F4);
		MenuItem_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.ALT_MASK));
		MenuItem_exit.setToolTipText("Exit");

		MenuItem_exit.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				System.exit(0);
			}
		});
		monitor.add(MenuItem_exit);

		// PERFORMANCE
		JMenu performance = new JMenu("Performance");
		performance.setMnemonic(KeyEvent.VK_P);
		performance
				.getAccessibleContext()
				.setAccessibleDescription(
						"Menu Which has Option Related to the Performance of the Clients ");
		performance
				.setToolTipText("Menu Which has Option Related to the Performance of the Clients ");
		menuBar.add(performance);

		JMenuItem MenuItem_performance = new JMenuItem("Performance of Client",
				icon);
		MenuItem_performance.setMnemonic(KeyEvent.VK_P);
		MenuItem_performance.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		MenuItem_performance
				.setToolTipText(" Show the Details about Clients Performance");
		MenuItem_performance.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				PerformanceDetails pd = new PerformanceDetails();
			}
		});
		performance.add(MenuItem_performance);

		// SETTINGS

		JMenu settings = new JMenu("Settings");
		settings.setMnemonic(KeyEvent.VK_S);
		settings.getAccessibleContext().setAccessibleDescription(
				"To Set the  Server GUI ");
		settings.setToolTipText("Menu to Set the  Server GUI ");

		JMenuItem MenuItem_delay = new JMenuItem("Setting Delay", icon);
		MenuItem_delay.setMnemonic(KeyEvent.VK_S);
		MenuItem_delay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		MenuItem_delay.setToolTipText(" Show you the Delay Setting Dialog");
		MenuItem_delay.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SettingDelay sd = new SettingDelay();
			}
		});
		menuBar.add(settings);
		settings.add(MenuItem_delay);

		// HELP
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.getAccessibleContext().setAccessibleDescription(" HELP  ");
		menuBar.add(help);

		JMenuItem MenuItem_aboutus = new JMenuItem("About Us", icon);

		MenuItem_aboutus.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		MenuItem_aboutus.getAccessibleContext().setAccessibleDescription(
				" Help About the Product ");
		MenuItem_aboutus.setToolTipText(" Help About the Product ");

		help.add(MenuItem_aboutus);
		MenuItem_aboutus.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionPane.showMessageDialog(null,"Project by :\n\n\tYogesh Agav               yogesh.agav@gmail.com\n\tSumeet Bandgar       sumeetbandgar@gmail.com\n\tSanket Dhamane      sanket339@gmail.com");
			}
		});
		setJMenuBar(menuBar);
	}

	public void createCenterPanel()
	{
		warningButton = new JButton("SEND MESSAGE");
		warningButton.setBounds(30, 65, 150, 35);

		warningButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.SEND_WARNING;
			}
		});
		this.add(warningButton);

		warningField = new JTextField(20);
		warningField.setBounds(200, 65, 1680, 35);
		this.add(warningField);

		// PANEL_CENTER
		JPanel panel_warning = new JPanel();
		panel_warning.setLayout(new BorderLayout());
		panel_warning.setBackground(Color.WHITE);

		panel_warning.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(80, 360, 30, 360),
				BorderFactory.createRaisedBevelBorder()));

		cPanel = new JPanel();
		cPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		cPanel.setLayout(gridbag);
		//cPanel.setPreferredSize(new Dimension(1000,1000));

		cPanel.setBackground(new Color(198, 239, 255));
		panel.add(label);
		panel.revalidate();
		//cPanel.add(panel);
		// parent.remove(label);
		
		/*scroll = new JScrollPane(cPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(100, 100));
		scroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		*/
		Dimension imgPanel = Toolkit.getDefaultToolkit().getScreenSize();
		
		scroll = new JScrollPane(cPanel);
		//scroll.setBounds(340,130,1250,770);
		scroll.setBounds(240,130,900,470);
		//scroll.setBounds(((int) imgPanel.getWidth()/3)-230,((int) imgPanel.getHeight()/3)-200,((int) imgPanel.getWidth()/3)+450,((int) imgPanel.getHeight()/3)+400);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension());
		contentPane.add(scroll, BorderLayout.CENTER);

		// panel_warning.add(p3,BorderLayout.CENTER);

		JPanel p1 = new JPanel();
		//p1.setBounds(0,350,300,280);
		p1.setBounds(0,250,280,220);
		//p1.setBounds(((int) imgPanel.getWidth()/3)-775,((int) imgPanel.getHeight()/3)-200,((int) imgPanel.getWidth()/3)+450,((int) imgPanel.getHeight()/3)+400);
		ImageIcon pic = new ImageIcon(
				"IMAGES/globe1.gif");
		ImageIcon leftGif = new ImageIcon(pic.getImage().getScaledInstance(150,
				150, Image.SCALE_DEFAULT));
		//ImageIcon leftGif = new ImageIcon(pic.getImage().getScaledInstance(260,
				//260, Image.SCALE_DEFAULT));
		p1.add(new JLabel(leftGif));
		this.add(p1);

		JPanel p2 = new JPanel();
		//p2.setBounds(1600,350,300,280);
		p2.setBounds(1105,250,280,220);
		ImageIcon pic2 = new
		ImageIcon("IMAGES/globe1.gif");
		ImageIcon rightGif = new ImageIcon(pic.getImage().getScaledInstance(
				150,150, Image.SCALE_DEFAULT));
		//ImageIcon rightGif = new ImageIcon(pic.getImage().getScaledInstance(
			//	260,260, Image.SCALE_DEFAULT));
		p2.add(new JLabel(rightGif));
		this.add(p2);


		panel_warning.setBackground(new Color(198, 239, 255));
		contentPane.add(panel_warning, BorderLayout.CENTER);
	}

	public void createBttomPanel() {
		// DOWN PANEL
		JPanel panel_details = new JPanel();

		panel_details.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(1, 1, 1, 1),
				BorderFactory.createRaisedBevelBorder()));

		panel_details.add(new JLabel("Client No :-"));
		clnText = new JTextField(3);
		clnText.setEditable(false);
		panel_details.add(clnText);
		
		panel_details.add(new JLabel("IP Address :-"));
		ipAddText = new JTextField(15);
		ipAddText.setEditable(false);
		panel_details.add(ipAddText);

		panel_details.add(new JLabel("Computer Name :-"));
		compNameText = new JTextField(10);
		compNameText.setEditable(false);
		panel_details.add(compNameText);
		
		panel_details.add(new JLabel("User Name :-"));
		userNameTxt = new JTextField(10);
		userNameTxt.setEditable(false);
		panel_details.add(userNameTxt);
		
		panel_details.add(new JLabel("OS Name :-"));
		osNameText = new JTextField(10);
		osNameText.setEditable(false);
		panel_details.add(osNameText);
		
		panel_details.add(new JLabel("OS Version :-"));
		osVersionText = new JTextField(10);
		osVersionText.setEditable(false);
		panel_details.add(osVersionText);
		
		
		// panel_details.setBackground(Color.YELLOW);
		this.add(panel_details);

		panel_details.setBackground(new Color(97, 210, 254));
		contentPane.add(panel_details, BorderLayout.SOUTH);

	}

	public void createTopPanel()
	{
		JPanel panel_buttons = new JPanel();
		// TOP PANEL
		panel_buttons.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(1, 1, 1, 1),
				BorderFactory.createRaisedBevelBorder()));


		// *******************************************send button
		// ****************************************

		ImageIcon pic5 = new ImageIcon(
				"IMAGES/start_send_image.gif");
		sendButton = new JButton(pic5);

		sendButton.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.SEND_IMAGE;
			}
		});
		// sendButton.getAccessibleContext().setAccessibleDescription(
		// "Start Sending Images");
		sendButton.setToolTipText("Start Sending Images");
		panel_buttons.add(sendButton);

		// *******************************stop button
		// **********************************************

		ImageIcon pic6 = new ImageIcon(
				"IMAGES/stop_send_image.gif");
		stopButton = new JButton(pic6);
		stopButton.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.NOTHING;
			}
		});
		stopButton.setToolTipText("Stop Sending Images");
		panel_buttons.add(stopButton);

		// *************************shutdown
		// button************************************************************

		ImageIcon pic7 = new ImageIcon(
		"IMAGES/shut_down.gif");
		shutdownButton = new JButton(pic7);
		shutdownButton.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.SHUTDOWN;
			}
		});
		shutdownButton.setToolTipText("Shutdown Client Machine");
		panel_buttons.add(shutdownButton);

		// **********************************File sharing
		// button**********************************8

		
		ImageIcon pic8 = new ImageIcon(
				"IMAGES/file_image.gif");
		filesharingButton = new JButton(pic8);
		filesharingButton.setToolTipText("Send File");
		filesharingButton.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.FILE_SHARING;
			}
		});
		panel_buttons.add(filesharingButton);

		// ************************** block
		// button***************************************

		blockButton = new JButton("BLOCK");
		blockButton.setToolTipText("Block Client");
		ImageIcon pic9 = new ImageIcon(
				"IMAGES/keyboard_lock.gif");
		blockButton = new JButton(pic9);
		blockButton.setToolTipText("Block Client");
		blockButton.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.BLOCK_CLIENT;
			}
		});
		panel_buttons.add(blockButton);

		// **********************************unblock
		// button*******************************

		
		ImageIcon pic10 = new ImageIcon(
				"IMAGES/keyboard_unlock.gif");
		unblockButton = new JButton(pic10);
		unblockButton.setToolTipText("Unblock Client");
		unblockButton.addActionListener(new ActionListener()
		{
			// @Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				SocketThread.task = SocketThread.UNBLOCK_CLIENT;
			}
		});
		panel_buttons.add(unblockButton);
		panel_buttons.setBackground(new Color(1, 146, 255));
		contentPane.add(panel_buttons, BorderLayout.NORTH);

	}
}
