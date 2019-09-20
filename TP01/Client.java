import java.net.*;
import java.io.*;



/**
 * Client socket class

 * @author decoopmc
 * @version 1.0
 * @see java.net
 */
public class Client {

	/**
	 * Connection host
	 */
	private static String host = "localhost";

	/**
	 * Connection port
	 */
	private static int port = 1600;

	/**
	 * Simple TimeStamp to track dates from messages
	 */
	private static String timeStamp = "";


	/**
	 * MAIN
	 */
	public static void main(String[] args) 
	{
		/* INITIALIZING CLIENT */
		StringBuffer instr = new StringBuffer();
		System.out.println("Client initialized");

		/* ESTABLISHING CONNECTION */
		try {
			InetAddress address     = InetAddress.getByName(host);		// address object containing the couple (name,host)
			Socket connection       = new Socket(address, port); 		// instanciate a socket connection

			//Writing message
			timeStamp      = new java.util.Date().toString();
	    	String message = "Calling the Socket Server on "+ host + " port " + port + " at " + timeStamp +  (char) 13;
			write("", connection);
		}
		catch(IOException e) {}
	}


	/**
	 * Write in socket
	 */
	public static void write(String message, Socket connection)
	{
		try {
			BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream()); 	// container for writing to the socket
			OutputStreamWriter osw  = new OutputStreamWriter(os, "US-ASCII");					// instanciate a writer
		    
		    // Write across the socket connection and flush the buffer
		    osw.write(message);
		    osw.flush();
		}
		catch(IOException e) {
			System.out.println("IOException: " + e);
		}
		
	}


	/**
	 * Read from socket
	 */
	public void read()
	{
		
	}


}