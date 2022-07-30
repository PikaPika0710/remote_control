
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

class CreateFrame extends Thread {

	private Socket cSocket;
	private Socket cSocket2;
	private String width;
	private String height;

	private static JPanel cPanel = new JPanel();
	
	public CreateFrame(Socket cSocket, Socket cSocket2, String width, String height) {
		this.width = width;
		this.height = height;
		this.cSocket = cSocket;
		this.cSocket2 = cSocket2;
	}


	public void drawGUI() {
		JFrame frame = new JFrame();
		JDesktopPane desktop = new JDesktopPane();
		JInternalFrame interFrame = new JInternalFrame("Server Screen", true, true, true);

				
		frame.add(desktop, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH); // CHECK THIS LINE
		frame.setVisible(true);
		interFrame.setLayout(new BorderLayout());
		interFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
		interFrame.setSize(100, 100);
		desktop.add(interFrame);

		try {
			interFrame.setMaximum(true);
		} catch (PropertyVetoException ex) {
			ex.printStackTrace();
		}

		cPanel.setFocusable(true);
		interFrame.setVisible(true);

	}

	public void run() {
		
		ObjectInputStream oin = null;
		drawGUI();

		try {
			oin = new ObjectInputStream(cSocket.getInputStream());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// Start receiving screenshots
		new ReceiveScreen(oin, cPanel).start();
		;
		// Start sending events to the client
		new SendEvents(cSocket, cPanel, width, height);
		
		System.out.println("============Chat============");
		new SendMessageServer(cSocket2).start();
		new ReceiveMessageServer(cSocket2).start();
	}
}
