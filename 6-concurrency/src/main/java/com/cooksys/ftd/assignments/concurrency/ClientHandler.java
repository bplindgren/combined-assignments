package com.cooksys.ftd.assignments.concurrency;

public class ClientHandler implements Runnable {

    @Override
    public void run() {
    	// Server uses the client handler
    	// Create a new socket with a new thread each time a new client tries to connect
    	// BufferedReader input = new BufferedReader(new InputStreamReader(chThread.getInputStream()));
    	
        
    	System.out.println("In the client handler");
    }
}
