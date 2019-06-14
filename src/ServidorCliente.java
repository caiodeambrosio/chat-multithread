import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorCliente extends Thread{
	private ArrayList <Socket> clients;
	private BufferedReader inputClient;
	private Socket socket;
	
	public ServidorCliente(Socket socket, ArrayList<Socket> clients) throws IOException {
		this.clients = clients;
		this.socket = socket;
		System.out.println(socket.getRemoteSocketAddress() + " Entrou!");
		this.inputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run() {
		try {
			String message;
			do {
				message = this.inputClient.readLine();
				sendToAll(message);
			}while(!(message.equalsIgnoreCase("sair")));			
		} catch (IOException e) {
			System.out.println(socket.getRemoteSocketAddress() + " Saiu!");
		}
	}
	
	public void sendToAll(String message) throws IOException {
		for (Socket client : clients) {
			PrintStream socketOutPut = new PrintStream(client.getOutputStream());
			socketOutPut.println(message);
		}
	}

}
