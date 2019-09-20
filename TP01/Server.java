import java.net.*;
import java.io.*;
import java.util.*;



/**
 * Single server socket class

 * @author decoopmc
 * @version 1.0
 * @see java.net
 */
public class SingleServer {

	/**
	 *
	 */
	static ServerSocket socket1;

	/**
	 *
	 */
	static Socket connection;

	/**
	 *
	 */
	protected final static int port = 1600;

	/**
	 *
	 */
	static boolean first;

	/**
	 *
	 */
	static StringBuffer message;

	/**
	 *
	 */
	static String timeStamp;


	/**
	 * MAIN
	 */
	public static void main(String[] args) 
	{
		try {
			//INITIALIZING SERVER
			socket1 = new ServerSocket(port);
			System.out.println("SingleSocketServer initialized");
			int character;

			//SERVER LOOP
			while(true) {
				connection = socket1.accept();
				BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
	        	InputStreamReader isr  = new InputStreamReader(is);
	        	message = new StringBuffer();

	        	while((character = isr.read()) != 13) {
	        		message.append((char)character);
	        	}

	        	//Printing received message
	        	System.out.println(message);

	        	//waiting 10 sec for the app to update database
	        	try {
	        		Thread.sleep(10000);
	        	}
	        	catch(Exception e) {
	        		System.out.println("Exception: " + e);
	        	}

	        	//Sending response
	        	timeStamp    = new java.util.Date().toString();
	        	String returnCode = "SingleSocketServer responded at " + timeStamp + (char) 13;
		        write(returnCode);
			}
		}
		catch(IOException e) {}

		//CLOSING CONNECTION
		try {
			connection.close();
		}
		catch(IOException e) {
			System.out.println("IOException: " + e);
		}
	}


	/**
	 * Writing message for clients
	 */
	public static void write(String message)
	{
		try {
			BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
	        OutputStreamWriter osw  = new OutputStreamWriter(os, "US-ASCII");
	        osw.write(message);
	        osw.flush();
		}
		catch(IOException e) {
			System.out.println("IOException: " + e);
		}
	}

}