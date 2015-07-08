package ServidorConcurrente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.*;

class SocketThrdServer extends JFrame
{   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lblTextoRecibido = new JLabel("Texto recibido:");   
	JPanel pnlPanelPrincipal = new JPanel();   
	JTextArea textArea = new JTextArea(0,50);   
	ServerSocket server = null;   
	
	SocketThrdServer()
	{   
		pnlPanelPrincipal.setLayout(new BorderLayout());     
		pnlPanelPrincipal.setBackground(Color.white);     
		getContentPane().add(pnlPanelPrincipal);     
		pnlPanelPrincipal.add("North", lblTextoRecibido);     
		pnlPanelPrincipal.add("Center", textArea);
		textArea.setEnabled(false);
	} 
	
	public void listenSocket()
	{    
		try
		{      
			server = new ServerSocket(4444);     
		} catch (IOException e) 
		{      
			System.out.println("No se escucha al puerto 4444");      
			System.exit(-1);    
		}    
		
		while(true)
		{      
			Cliente cliente;      
			try
			{        
				cliente = new Cliente(server.accept(), textArea);        
				Thread t = new Thread(cliente);        
				t.start();      
			} 
			catch (IOException e) 
			{        
				System.out.println("Puerto: 4444");        
				System.exit(-1);      
			}
		}  
	}  
	
	protected void finalize()
	{
		try
		{        
			server.close();    
		} 
		catch (IOException e) 
		{        
			System.out.println("No se puede cerrar el socket");        
			System.exit(-1);    
		}  	
	} 
	
	public static void main(String[] args)
	{        
		SocketThrdServer frame = new SocketThrdServer();frame.setTitle("Servidor");        
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
	}
}
			
			
		
		
		
	
