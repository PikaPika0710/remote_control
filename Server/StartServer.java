import javax.swing.JOptionPane;

public class StartServer {
	private static String password = "1234";

	public static void main(String[] args) {
		String port = JOptionPane.showInputDialog("Please enter listening port");
		new InitConnection(Integer.parseInt(port), password);
	}
}
