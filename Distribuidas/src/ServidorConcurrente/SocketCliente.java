package ServidorConcurrente;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

class SocketCliente extends JFrame implements ActionListener 
{   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel txtTextoEnviar = new JLabel("Texto a enviar:");   
	JButton btnEnviar = new JButton("Enviar");   
	JPanel 	pnlPanelPrincipal = new JPanel();  
	JTextField txtFieldEscribir = new JTextField(20);   
	Socket socket = null;   
	PrintWriter out = null;   
	BufferedReader in = null;   
	
	SocketCliente()
	{              
		btnEnviar.addActionListener(this);     
		pnlPanelPrincipal.setLayout(new BorderLayout());     
		pnlPanelPrincipal.setBackground(Color.white);     
		getContentPane().add(pnlPanelPrincipal);     
		pnlPanelPrincipal.add("North", txtTextoEnviar);     
		pnlPanelPrincipal.add("Center", txtFieldEscribir);     
		pnlPanelPrincipal.add("South", btnEnviar);   
	} 
	
	public void actionPerformed(ActionEvent event)
	{     
		Object source = event.getSource();     
		if(source == btnEnviar)
		{          
			String text = txtFieldEscribir.getText();          
			out.println(text);  
			txtFieldEscribir.setText(new String(""));
			       
			try
			{  
				String line = in.readLine();          
				System.out.println("Texto recibido :" + line);       
			} 
			catch (IOException e)
			{ 
				System.out.println("Error de lectura");         
				System.exit(1);       
			}     
		}  
	}    
	
	public void listenSocket()
	{     
		try
		{       
			socket = new Socket("localhost", 4444);       
			out = new PrintWriter(socket.getOutputStream(), true);       
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));     
		} 
		catch (UnknownHostException e) 
		{       
			System.out.println("Host desconocido");       
			System.exit(1);     
		} 
		catch  (IOException e) 
		{       
			System.out.println("No I/O");       
			System.exit(1);     
		}  
	}   
	
	public static void main(String[] args)
	{        
		SocketCliente frame = new SocketCliente();frame.setTitle("Cliente");        
		WindowListener l = new WindowAdapter() 
		{                
			public void windowClosing(WindowEvent e) 
			{                        
				System.exit(0);                
			}        	
		};
		frame.addWindowListener(l);        
		frame.pack();        
		frame.setVisible(true);
		frame.listenSocket();
		frame.setLocation(125, 125);
	}
}