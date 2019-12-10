package GUI;

import Network.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyWindowListener extends WindowAdapter {
	private Socket socket;
	private ObjectOutputStream writer;
	
	public MyWindowListener(Socket s, ObjectOutputStream oos) {
		socket = s;
		writer = oos;
	}
	public void windowClosing(WindowEvent e) {
		try {
			writer.writeObject(new Protocol(0,0));
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        System.exit(0);
}
}
