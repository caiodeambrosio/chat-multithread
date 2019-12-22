import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Servidor {

	static ArrayList<Socket> clientes;
	static ServerSocket server;
	static Socket connection;

	public static void main(String[] args) throws IOException {

		server = new ServerSocket(7000);
		clientes = new ArrayList<Socket>();

		Object[] options = { "OK" };
		JOptionPane.showOptionDialog(null, "Servidor inicializado com sucesso! \nClique em OK para que seus clientes possam se conectar.", "Sucesso", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		while (true) {
			connection = server.accept();
			clientes.add(connection);
			Thread chat = new ServidorCliente(connection, clientes);
			chat.start();
		}

	}

}
