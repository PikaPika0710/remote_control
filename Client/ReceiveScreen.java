
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ReceiveScreen extends Thread {
	private ObjectInputStream oin;
	private JPanel cPanel;
	private boolean continueLoop = true;

	public ReceiveScreen(ObjectInputStream oin, JPanel cPanel) {
		this.oin = oin;
		this.cPanel = cPanel;
	}

	public void run() {
		try {
			while (continueLoop) {
				ImageIcon imageIcon = (ImageIcon) oin.readObject();
				Image image = imageIcon.getImage();

				image = image.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);

				Graphics graphics = cPanel.getGraphics();
				graphics.drawImage(image, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
			}

		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
