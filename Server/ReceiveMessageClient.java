import java.io.DataInputStream;
import java.net.Socket;

public class ReceiveMessageClient extends Thread{
	private Socket socket;

	private static DataInputStream dis;
	
	public ReceiveMessageClient(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			dis = new DataInputStream(socket.getInputStream());
			while (true) {
				String message = dis.readUTF();
				System.out.println("client>> " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
