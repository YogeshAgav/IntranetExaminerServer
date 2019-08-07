import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.accessibility.Accessible;
import java.beans.*;
import java.awt.event.*;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;
import java.awt.Component.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener.*;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.io.File;


class GraphPanel extends JPanel implements MouseListener,FocusListener,Accessible
{
    private JLabel picture;
    private ImageIcon image,image1;
	int i=0;
    private int clientNo = 0 ;
  	private String osName = "Windows 10";
  	private String osArchi = "x64" ;
  	private String osVersion ="1.0" ;
  	private String ipAdd ="127.0.0.1";
  	private String computerName = "Yogesh" ;
	private String userName;
	static JLabel fullImg;
	static JPanel fullPan;
	static JFrame j;
	int clNo = Server.count;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension toolkit1 = Toolkit.getDefaultToolkit().getScreenSize();
	

  	public GraphPanel( int cln,String osn ,String osv ,String ipa, String cname, String uname )
  	{
		ipAdd = ipa;
		clientNo = cln ;
		osName = osn ;
		osVersion = osv ;
		computerName = cname;
		userName = uname;
        
		setFocusable(true);
        addMouseListener(this);
        addFocusListener(this);
		
		this.setLayout(new BorderLayout() );
  	    picture = new JLabel( new ImageIcon("images/aish.jpg") );

        this.add(picture,BorderLayout.CENTER);
        this.setPreferredSize( new Dimension(190,190) );
        this.setMaximumSize( new Dimension(190,190) );
        this.setMinimumSize( new Dimension(190,190) );
		this.setBorder( BorderFactory.createLineBorder(Color.YELLOW,2) );
		this.setCursor(new Cursor(12)); // hand cursor

  	}

	public void mouseClicked(MouseEvent e)
	{

		j = new JFrame("Client No :"+ clNo);
		//j.setBounds(((int) toolkit1.getWidth()/3)-250,((int) toolkit1.getHeight()/3)-100,((int) toolkit1.getWidth()/3)+500,((int) toolkit1.getHeight()/3)+200);
		//j.setBounds(340,130,1250,770);
		j.setBounds(248,180,900,480);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		fullImg = new JLabel();
		fullImg.setBounds(248,180,900,480);
		
		fullPan = new JPanel();
		fullPan.setBounds(248,180,900,480);
		
    }

    public void mouseEntered(MouseEvent e)
	{
		//Since the user clicked on us, let's get focus!
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE,2) );
		requestFocusInWindow();
    }
    
	public void mouseExited(MouseEvent e)
	{ 
		this.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2) ); 
	}
    
	public void mousePressed(MouseEvent e) { }
    
	public void mouseReleased(MouseEvent e) { }

    public void focusGained(FocusEvent e)
	{
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN,2) );

        ServerGui.clnText.setText( ""+clientNo );
        ServerGui.compNameText.setText( ""+computerName );
        ServerGui.ipAddText.setText( ""+ipAdd );
        ServerGui.osNameText.setText( ""+osName );
        ServerGui.osVersionText.setText( ""+osVersion );
        ServerGui.userNameTxt.setText(userName);
    }

    public void focusLost(FocusEvent e)
	{
		this.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2) );
        ServerGui.clnText.setText( "" );
        ServerGui.compNameText.setText( "" );
        ServerGui.ipAddText.setText( "" );
        ServerGui.osNameText.setText( "" );
        ServerGui.osVersionText.setText("");
    }

  	public void paintComponent(Graphics g){}
    public void setImage(BufferedImage theImage){}

    public void refresh(byte[] imgBytes,int imgSize)
    {
		try
		{
			image = new ImageIcon(toolkit.createImage(imgBytes,0,imgSize).getScaledInstance(picture.getWidth(),picture.getHeight(),Image.SCALE_FAST));				picture.setIcon(image);
			picture.revalidate();
			
			image1 = new ImageIcon(toolkit.createImage(imgBytes,0, imgSize).getScaledInstance(900, 480,Image.SCALE_FAST));
			fullImg.setIcon(image1);
			fullImg.revalidate();
			fullPan.add(fullImg);
			j.add(fullImg);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

    }

    public String getIPAdd()
    { 
		return ipAdd;  
	}
    public String getOS()
    { 
		return( new String( ""+osName+osVersion ) );
	}
    public String getComputerName()
    {
		return computerName ;
	}
  }