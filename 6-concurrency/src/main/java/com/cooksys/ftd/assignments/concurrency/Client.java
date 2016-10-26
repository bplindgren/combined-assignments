package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig;
import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;

public class Client implements Runnable {
	private ClientConfig config;
	private static final String PARALLEL = "PARALLEL";
	private static final String SEQUENTIAL = "SEQUENTIAL";

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
        	
        	// Using the List<ClientInstanceConfig> clients, create client instances
        	for (ClientInstanceConfig c : config.getInstances()) {
        		ClientInstance cInst = new ClientInstance(c, s);
        		System.out.println("Created a new client");
        		
        		// Create a new thread
        		Thread t = new Thread(cInst);
        		System.out.println(t.getName());
        		t.start();
//        		if ((PARALLEL).equals(config.getSpawnStrategy())) {
//	        		t.start();
//        		} else if ((SEQUENTIAL).equals(config.getSpawnStrategy())) {
//        			t.run();
//        		}
        	}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
