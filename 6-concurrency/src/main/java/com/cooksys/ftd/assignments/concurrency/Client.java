package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig;

public class Client implements Runnable {
	private ClientConfig config;

	// Should be able to create sub clients that automatically connect to the server
	// Client is the engine that builds client instances
    public Client(ClientConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
    	// Create a new socket
        try {
			Socket socket = new Socket(config.getHost(), config.getPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
