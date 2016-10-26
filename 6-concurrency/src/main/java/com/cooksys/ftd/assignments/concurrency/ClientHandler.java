package com.cooksys.ftd.assignments.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

//Server uses the client handler
public class ClientHandler implements Runnable {

	private final Socket socket;
	private final BufferedReader input;
	private final BufferedWriter output;
	private final String clientIdentifier;
	
	private static final String IDENTITY = "IDENTITY";
	private static final String TIME = "TIME";

	
	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		clientIdentifier = "Client " + socket.getRemoteSocketAddress() + " " + Thread.currentThread().getId();
	}
	
    @Override
    public void run() {    	
    	try {
    		while (!socket.isClosed()) {
	    		String message = input.readLine();
	    		
	    		if (IDENTITY.equals(message)) {
	    			output.write("Your identity is: " + clientIdentifier + "\n");
	    			output.flush();
	    		} else if (TIME.equals(message)) {
	    			Date date = new Date();
	    			output.write("Hey " + clientIdentifier + " the time is " + date.toString() + "\n");
	    			output.flush();
	    		}
    		}
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
}
