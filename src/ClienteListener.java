import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

public class ClienteListener extends Thread{

	private BufferedReader input;
	private JTextArea areaTexto;
	public ClienteListener(BufferedReader input, JTextArea areaTexto) {
		this.input = input;
		this.areaTexto = areaTexto;
	}
	
	public void run() {
		try {
			String message;
			do {
				message = input.readLine();
				areaTexto.append(message + "\n");
			}while(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
