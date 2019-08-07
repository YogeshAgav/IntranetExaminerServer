import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.event.*;
import java.awt.Component.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener.*;
import java.awt.event.KeyEvent;
import java.awt.*;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.*;

import java.net.InetAddress;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.imageio.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

import java.lang.Thread;



public class SocketThread extends Thread {
	static final int NOTHING = 0;
	static final int SEND_IMAGE = 1;
	static final int SHUTDOWN = 2;
	static final int SEND_WARNING = 3;
	static final int BLOCK_CLIENT = 4;
	static final int UNBLOCK_CLIENT = 5;
	static final int SEND_MULTILINE_WARNING = 6;
	
	static int watchingPerformance = 1;
	static final int FILE_SHARING = 7;
	byte bytes[] = new byte[1024];
	static int DELAY = 250;
	int bytesCame;
	int i=0;
	int clNo;
	static String dirName;
	ServerGui gui = null;
	DataInputStream in;
	PrintWriter printWriter = null;
	static int task = SEND_IMAGE;
	ClientList Head = null;
	ClientList iterator = null;
	BufferedReader br;
	SocketThread(Socket soc, ServerGui gui)
	{
		this.gui = gui;
		if(Head == null)
		{	
			Head = new ClientList();
			Head.soc= soc;
			Head.next = null;
			iterator = Head;
		}
		
		while(iterator.next != null)
		{
			iterator = iterator.next;
		}
		iterator.next = new ClientList();
		iterator = iterator.next;
		iterator.soc = soc;
		iterator.next = null;
		try 
		{
			in = new DataInputStream(iterator.soc.getInputStream());
			br = new BufferedReader(new InputStreamReader(iterator.soc.getInputStream()));
			dirName = new String("Client" );
			clNo = Server.count; 
			clNo++;
			File file = new File("D:\\"+dirName+clNo);
			
			if (!file.exists())
	        {
	            file.mkdir();
	        }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		int totalNoOfBytesRecieved = 100;
		JLabel picture = new JLabel();
		StringBuffer tempData = new StringBuffer("");
		String osName="";
		String osVersion="";
		String computerName="";
		String userName="";
		try
		{
			computerName=br.readLine();
			userName=br.readLine();
			osName = br.readLine();
			osVersion = br.readLine();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		String ipadd = iterator.soc.getInetAddress().toString();
		System.out.println(ipadd);
		final String performanceText = new String(" From Client No "
				+clNo  + "    IP Add : " + ipadd
				+ "      Bytes Recieved  :   ");
		final JLabel performanceLabel = new JLabel(performanceText + "      ");
		PerformanceDetails.allLabelPane.add(performanceLabel);
		
		GraphPanel gp = new GraphPanel(++Server.count, osName,osVersion, ipadd, computerName,userName);
			
		JPanel thumbnail = new JPanel();
		thumbnail.setLayout(new BorderLayout());
		thumbnail.add(gp, BorderLayout.CENTER);
		thumbnail.add(new JLabel("                     Client No " + Server.count),BorderLayout.SOUTH);
		thumbnail.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		ServerGui.cons.fill = GridBagConstraints.HORIZONTAL;
		ServerGui.cons.gridx = (Server.count - 1) % 4;
		ServerGui.cons.gridy = (Server.count - 1) / 4;
		ServerGui.gridbag.setConstraints(thumbnail, ServerGui.cons);
		ServerGui.cPanel.add(thumbnail);
		ServerGui.cPanel.validate();
		ServerGui.scroll.validate();
		
		try
		{
			printWriter = new PrintWriter(iterator.soc.getOutputStream());
			
			while(true)
			{
				if(task != NOTHING)
				{
					printWriter.println(new String(""+task));
					printWriter.flush();
					
					switch(task)
					{
						
						case 1: //Receiving image
								int imgSize = in.readInt();
								//System.out.println("Image Size" + imgSize);
								byte[] imgBytes = new byte[imgSize];
								bytesCame = 0;
								while (bytesCame < imgSize)
								{
									bytesCame += in.read(imgBytes, bytesCame, in.available());
								}
								//System.out.println("Image Came");
								gp.refresh(imgBytes,imgSize);
								System.out.println("Exception 2");
								Toolkit toolkit = Toolkit.getDefaultToolkit();
								ImageIcon image1 = new ImageIcon(toolkit.createImage(imgBytes,0, imgSize));
								
								BufferedImage bimg = new BufferedImage(image1.getIconWidth(), image1.getIconHeight(), BufferedImage.TYPE_INT_RGB);
								bimg.getGraphics().drawImage(image1.getImage(), 0, 0, null);

								if(DELAY >=60000)
								{
									synchronized(this)
									{
										String path1 = "D:\\"+dirName+Server.count+"\\img"+i+".jpeg";
										System.out.println(path1);
										File imgFile = new File(path1);
										ImageIO.write(bimg,"jpeg",imgFile);
									}
									i++;
								}
								
								totalNoOfBytesRecieved += (int) (imgSize / 1000);
								
								if (watchingPerformance == 0) 
								{
									performanceLabel.setText(performanceText + "  "+ totalNoOfBytesRecieved + "  KBytes");
								}
								
								break;
						
						case 2: // shutdown client machine
								task = SEND_IMAGE;
								break;
						
						case 3: // Send Single line message
								printWriter.println(ServerGui.warningField.getText());
								printWriter.flush();
								
								task = SEND_IMAGE;
								break;
						case 4: // Blocking client
								break;
								
						case 5:	// Unblocking client
								break;

						case 6:	//something
								break;

						case 7:	//file sharing
								
								final JFileChooser fc = new JFileChooser();
								int returnVal = fc.showOpenDialog(gui);
								if (returnVal == JFileChooser.APPROVE_OPTION) 
								{
									File inputFile = fc.getSelectedFile();
									FileInputStream inFileStr = new FileInputStream(inputFile);
									int c;
									// first send the file name
									printWriter.println(inputFile.getName());
									// now send the full file
									while ((c = inFileStr.read()) != -1)
										printWriter.println(c);
									
									printWriter.println(-1); // indicating the end of file transfer
									inFileStr.close();
									// prompting at the server side displaying the message
									JOptionPane.showMessageDialog(gui," File is Send to the Client ");
								} 
								else 
								{
									printWriter.println("___999");
								}
								
								task = SEND_IMAGE;
								
								break;
						
						default:
								task = SEND_IMAGE;
							
					
					}
					
				}
				
				Thread.sleep(DELAY);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

}