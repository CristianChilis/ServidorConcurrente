package ServidorConcurrente;

import javax.swing.*;
import java.io.*;
import java.net.*;

class Cliente implements Runnable 
{  
	private Socket client;  
	private JTextArea textArea;    
	Cliente(Socket client, JTextArea textArea) 
	{   
		this.client = client;   
		this.textArea = textArea;     
	}  
	
	public void run()
	{    
		String line;    
		BufferedReader in = null;
		PrintWriter out = null;    
		try
		{      
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));      
			out = new PrintWriter(client.getOutputStream(), true);    
		} 
		catch (IOException e) 
		{      
			System.out.println("Falla");      
			System.exit(-1);    
		}    
		
		while(true)
		{      
			try
			{        
				line = in.readLine();
				out.println(line);         
				textArea.append(line);       
			} 
			catch (IOException e) 
			{         
				System.out.println("Error de lectura");         
				System.exit(-1);       
			}    
		}  
	}
}
