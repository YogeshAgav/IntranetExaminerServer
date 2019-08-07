import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	static int count = 0;
	static ServerGui gui = null;
	public static void main(String args[])
	{
		new Login();
		int port = 6066;
		ServerSocket serverSoc = null;
		Socket soc = null;
		SocketThread socThread = null;
		try 
		{
			//Server accepting client request and assingning thread.
			serverSoc = new ServerSocket(port);
			while(true)
			{
				soc = serverSoc.accept();
				socThread = new SocketThread(soc,gui);
				socThread.start();
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}