
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import utils.WriteDiary;

public class StartClient {
	public static void main(String args[]) {
		String ip = JOptionPane.showInputDialog("Please enter server ip");
		String port = JOptionPane.showInputDialog("Please enter server port");
		//String password = JOptionPane.showInputDialog("Please enter password");
		String password = "1234";
		initialize(ip, Integer.parseInt(port), password);
	}

	public static void initialize(String ip, int port, String password) {
		try {

			Socket socket = new Socket(ip, port);
			Socket socket2 = new Socket(ip, port+1);
			System.out.println("Connecting to the Server");
			
			String message = "Connecting to the server: ip = " + ip + " ,port = " + port + ") - " + LocalDateTime.now();
			WriteDiary.writeDiary(message);

			new Authenticate(socket, socket2).checkPassword(password);

		} catch (Exception ex) {
			WriteDiary.writeDiary(">>>>Connect failed");
			ex.printStackTrace();
		}
	}
}
