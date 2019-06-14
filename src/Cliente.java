import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Cliente extends JFrame implements ActionListener {
    Socket socket ;
	JTextArea areaTexto;
	JTextField msg;
    JButton btn;
	JLabel rotulo;
	PrintStream output;
	BufferedReader input;
	String name;
	
    public Cliente() {
    	
    	name = JOptionPane.showInputDialog("Qual o seu nome?");	    
	    btn = new JButton("Enviar");
	    btn.addActionListener(this);
	    msg = new JTextField(20);
	    areaTexto = new JTextArea(15, 30);
	    areaTexto.setEditable(false);
	    
	    Container tela = getContentPane();
	    JScrollPane painelRolagem = new JScrollPane(areaTexto);

	    JPanel pCentro = new JPanel();
	    pCentro.add(painelRolagem);
	    
	    JPanel pInferior = new JPanel();
	    pInferior.add(msg);
	    pInferior.add(btn);
	    
	    tela.add(BorderLayout.CENTER, pCentro);
	    tela.add(BorderLayout.SOUTH, pInferior);
	    
	    this.setTitle("Seja bem vindo " + name + "!");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);  
	}
    
    public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.conectar();
	}

    void conectar()
    {
        try {
        	
        	socket = new Socket("127.0.0.1", 7000);
    		output = new PrintStream(socket.getOutputStream());
    		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    		Thread clienteRecebe = new ClienteListener(input, areaTexto);
    		clienteRecebe.start();  
    		
        } catch (IOException e) {
            System.out.println("Falha na Conexao... .. ." + " IOException: " + e);
        }
    }


	@Override
	public void actionPerformed(ActionEvent event) {
		Object fonte = event.getSource();  
        
        if (fonte == btn){  
                String message = msg.getText();  
                if(!message.equals("")) {
                	output.println(name + ": " + message);	
                    msg.setText(new String(""));	
                }  
        }  
	}
	
	
}