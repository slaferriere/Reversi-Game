package view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * This class handles the initial setup for the main server that the game
 * can be ran on.
 * 
 * @author Trevor Freudig, Scott LaFerriere
 *
 */
public class Server {
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
			Socket connection = server.accept();
			ObjectOutputStream output = new
				ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream input = new 
				ObjectInputStream(connection.getInputStream());
				
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
