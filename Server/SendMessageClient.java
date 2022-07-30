import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class SendMessageClient extends Thread {
	private Socket socket;

	private static DataOutputStream dos;
	private static Scanner sc = new Scanner(System.in);
	
	public SendMessageClient(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			while (true) {
				String message = sc.nextLine();
				dos.writeUTF(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
