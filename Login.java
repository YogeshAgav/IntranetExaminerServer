// Login Page Module
// 09-03-2016

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends Frame implements ActionListener
{
	JTextField tfUserName;
	JLabel UserName;
	JPasswordField tfPassword;
	JLabel Password;
	JButton loginButton;
	JButton Retry,exitButton;
	JLabel InvalidUserName;
	JButton OKButton;
	Frame f,f1;
	//constructor 
	ServerGui serverUI;
	public Login()
	{
		
		Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int) d1.getWidth()/3,(int)d1.getHeight()/3,(int) d1.getWidth()/3,(int)d1.getHeight()/3);
		//this.setBounds(700,450,450,250);
		this.addWindowListener(new WindowAdapter() 
		{ 
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
          	}
		});
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.addWindowListener(new LoginWindowListner(this));
		this.setLayout(null);
		this.setTitle("LOGIN");
		
		UserName = new JLabel("User Name");
		UserName.setBounds(100,75,100,20);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(200,75,140,25);
		
		Password = new JLabel("Password");
		Password.setBounds(100,110,100,20);
		
		tfPassword = new JPasswordField(); 
		tfPassword.setBounds(200,110,140,25);
		//tfPassword.setEchoChar('*');
		
		loginButton = new JButton("Login");
		loginButton.setBounds(115,160,100,30);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(240,160,100,30);
		
		this.add(UserName);
		this.add(tfUserName);
		this.add(tfPassword);
		this.add(Password);
		this.add(loginButton);
		this.add(exitButton);
		
		loginButton.addActionListener(this);
		exitButton.addActionListener(this);
	
		this.setVisible(true);
	}

		@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==loginButton)
		{
			String userName = tfUserName.getText();  
			String password = tfPassword.getText();           
			if(userName.isEmpty())
			{
				JOptionPane.showMessageDialog(this,	"Please Enter User Name");
			}
			else if(password.isEmpty())
			{
				JOptionPane.showMessageDialog(this,	"Please Enter Password");
			}
			else if(userName.equals("admin")&& password.equals("admin"))
			{
				JOptionPane.showMessageDialog(this, "Logged in Successfully !!!");
				this.dispose();
				Server.gui = new ServerGui();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invalid User Name or Password");
			}
		}
		if(e.getSource()==exitButton)
		{
			System.exit(0);
		}
	}
		
    
	
}