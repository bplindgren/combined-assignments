package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.cooksys.ftd.assignments.concurrency.model.config.ServerConfig;

public class Server implements Runnable {

	private ServerConfig config;
	
    public Server(ServerConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
		try {
			// Create a new ServerSocket
			ServerSocket ss = new ServerSocket(config.getPort());
			System.out.println("server is listening");
			
			while (true) {
				// Create a socket that allows it to receive attempted connections from the client
		    	Socket s = ss.accept();
	    	
				// Create new ClientHandler Threads if an attempted connection is made
		    	ClientHandler ch = new ClientHandler(s);
		    	Thread chThread = new Thread(ch);
		    	chThread.start();
	    	}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
}
