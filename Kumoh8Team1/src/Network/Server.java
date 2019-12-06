package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			//서버소켓 생성
			serverSocket = new ServerSocket(PORT);
			
			//소켓 서버가 종료될 때 까지 무한루프
			while(true)
			{
				Socket socket = serverSocket.accept();
				System.out.println("IP : " + socket.getLocalAddress() + "Port : " + socket.getLocalPort() +
									"의 호스트가 접속");
				try 
				{
					//접속된 클라이언트 소켓을 스레드풀로 넣어줌
					//이후는 스레드 내에서 처리
					threadPool.execute(new ConnectionSocket(socket));
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

//소켓 처리용 클래스
class ConnectionSocket implements Runnable
{
	private Socket socket = null;

	public ConnectionSocket(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()	//클라이언트와 통신
	{
		ServerProtocolManager manager=new ServerProtocolManager(socket);
		while(true)
		{
			manager.workProtocol();
			
		}
	}
}
