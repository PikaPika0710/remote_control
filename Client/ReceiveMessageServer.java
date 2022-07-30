import java.io.DataInputStream;
import java.net.Socket;

public class ReceiveMessageServer extends Thread{
	private Socket socket;

	private static DataInputStream dis;

	public ReceiveMessageServer(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			dis = new DataInputStream(socket.getInputStream());
			while (true) {
				String message = dis.readUTF();
				System.out.println("server>> " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
