
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import utils.WriteDiary;

class Authenticate {
	private Socket socket;
	private Socket socket2;

	Authenticate(Socket socket, Socket socket2) {
		this.socket = socket;
		this.socket2 = socket2;
	}

	public void checkPassword(String password) {

		DataOutputStream dos = null;
		DataInputStream dis = null;

		String verify = "";
		String width = "";
		String height = "";

		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			dos.writeUTF(password);
			verify = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if ("valid".equals(verify)) {
			System.out.println("Connected server");
			try {
				width = dis.readUTF();
				height = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			CreateFrame frame = new CreateFrame(socket, socket2, width, height);
			frame.start();
			WriteDiary.writeDiary(">>>> Connect successfully");
		} else {
			WriteDiary.writeDiary(">>>> Connect failed -- wrong password");
			new JOptionPane("Is valid password", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		}

	}


}
