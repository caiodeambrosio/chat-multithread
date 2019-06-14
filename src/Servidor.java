import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	static ArrayList<Socket> clientes;
	static ServerSocket server;
	static Socket connection;

	public static void main(String[] args) throws IOException {

		server = new ServerSocket(7000);
		clientes = new ArrayList<Socket>();

		while (true) {
			connection = server.accept();
			clientes.add(connection);
			Thread chat = new ServidorCliente(connection, clientes);
			chat.start();
		}

	}

}
