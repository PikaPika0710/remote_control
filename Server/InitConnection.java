import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InitConnection {

	private int port;
	private String password;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	InitConnection(int port, String password) {
		this.port = port;
		this.password = password;
		Robot robot = null;
		Rectangle rectangle = null;
		ServerSocket server = null;
		ServerSocket server2 = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		String width = "";
		String height = "";
		try {
			System.out.println("Awaiting Connection from Client...");
			server = new ServerSocket(port);
			server2 = new ServerSocket(port+1);

			GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			width = "" + dim.getWidth();
			height = "" + dim.getHeight();
			rectangle = new Rectangle(dim);
			robot = new Robot(gDev);


			while (true) {
				Socket socket = server.accept();
				Socket socket2 = server2.accept();
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				// String username=password.readUTF();
				String pass = dis.readUTF();

				if (pass.equals(password)) {
					System.out.println("Client connected");
					dos.writeUTF("valid");
					dos.writeUTF(width);
					dos.writeUTF(height);
					new SendScreen(socket, robot, rectangle).start();
					new ReceiveEvents(socket, robot).start();
					
					System.out.println("============Chat============");
					new SendMessageClient(socket2).start();
					new ReceiveMessageClient(socket2).start();
				} else {
					dos.writeUTF("Invalid");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
