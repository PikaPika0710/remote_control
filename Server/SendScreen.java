import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

class SendScreen extends Thread {


	private Socket socket;
	private Robot robot;
	private Rectangle rectangle;
	
	private static boolean continueLoop = true;
	private static ObjectOutputStream oos;

	public SendScreen(Socket socket, Robot robot, Rectangle rect) {
		this.socket = socket;
		this.robot = robot;
		this.rectangle = rect;
	}

	public void run() {
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			//oos.writeObject(rectangle);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		while (continueLoop) {
			BufferedImage image = robot.createScreenCapture(rectangle);
			ImageIcon imageIcon = new ImageIcon(image);

			try {
				oos.writeObject(imageIcon);
				oos.reset();
			} catch (IOException ex) {
				ex.printStackTrace();
				break;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
