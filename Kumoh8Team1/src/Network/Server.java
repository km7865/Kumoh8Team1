package Network;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class Server 
{
	private static final int PORT = 5000;
	private static final int THREADPOOL_CAPACITY = 5;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREADPOOL_CAPACITY);
	
	//private static ServerSocket serverSocket;
	
	public static void main(String[] args)
	{
		ServerSocket serverSocket;
		try
		{
			//�������� ����
			serverSocket = new ServerSocket(PORT);
			
			//���� ������ ����� �� ���� ���ѷ���
			while(true)
			{
			
				System.out.println("server�� ip : " + InetAddress.getLocalHost().getAddress() + ", Port : " + PORT);
				Socket socket = serverSocket.accept();
				System.out.println("IP : " + socket.getLocalAddress() + "Port : " + socket.getLocalPort() +
									"�� ȣ��Ʈ�� ����");
				try 
				{
					threadPool.execute(new ConnectionSocket(socket));//���ӵ� Ŭ���̾�Ʈ ������ ������Ǯ�� �־���
									//�Ĵ� ������ ������ ó��
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}//end of while
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

//���� ó���� Ŭ����
class ConnectionSocket implements Runnable
{
	private Socket socket = null;

	public ConnectionSocket(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()	//Ŭ���̾�Ʈ�� ���
	{
		ServerProtocolManager manager=new ServerProtocolManager(socket);
		while(true)
		{
			manager.workProtocol();
		}
	}
}
