
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SettingDelay extends JFrame implements ActionListener
{
	private JTextField second;
	private JLabel secondLabel;
	private JButton setButton;
	final static int FRAME_WIDTH=400;
	final static int FRAME_HEIGHT=250; 
	public SettingDelay() 
	{
		setTitle("Delay Setting");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		addWindowListener(new WindowAdapter()
		{
			public void windowClossing()
			{
				System.exit(0);
			}
		});
		setLayout(null);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) dimension.getHeight();
		int screenWidth = (int) dimension.getWidth();
		setLocation(screenWidth/2-FRAME_WIDTH/2,screenHeight/2-FRAME_HEIGHT/2-50);
		
		second= new JTextField(5);
		secondLabel = new JLabel("Seconds");
		setButton = new JButton("SET");
		setButton.addActionListener(this);
		
		secondLabel.setBounds(50, 50, 100, 25);
		second.setBounds(160, 50, 150, 25);
		setButton.setBounds(140, 140,100, 25);
		add(second);
		add(secondLabel);
		add(setButton);
		setResizable(false);
		setVisible(true);
	}	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == setButton)
		{
			String sec = second.getText();  
			if(sec.isEmpty())
			{
				JOptionPane.showMessageDialog(this,	"Please Enter Seconds.");
			}
			int delay = Integer.parseInt(sec);
			delay = delay*1000;
			SocketThread.DELAY = delay;
			this.dispose();
		}
	}
}
