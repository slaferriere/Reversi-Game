package view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Socket server = new Socket(args[0], Integer.parseInt(args[1]));
			ObjectOutputStream output = new
				ObjectOutputStream(server.getOutputStream());
			ObjectInputStream input = new 
				ObjectInputStream(server.getInputStream());
			
			System.out.println(input.readObject());
			
			server.close();     
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
