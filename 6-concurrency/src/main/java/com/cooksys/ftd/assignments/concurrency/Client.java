package com.cooksys.ftd.assignments.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig;
import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;

public class Client implements Runnable {
	private ClientConfig config;

	// Should be able to create sub clients that automatically connect to the server
	// Client is the engine that builds client instances
    public Client(ClientConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
        try {
        	// Create a new socket and communicate to server
        	Socket s = new Socket(config.getHost(), config.getPort());
        	
        	// Communication link with server
        	BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        	
//        	System.out.println("made it to client");
        	
        	// Using the List<ClientInstanceConfig> clients, create client instances
        	for (ClientInstanceConfig c : config.getInstances()) {
        		ClientInstance cInst = new ClientInstance(c, new Socket(config.getHost(), config.getPort()));
            	
        		System.out.println("Created a new client");
        		
        		Thread t = new Thread(cInst);
        		t.start();
        	}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
