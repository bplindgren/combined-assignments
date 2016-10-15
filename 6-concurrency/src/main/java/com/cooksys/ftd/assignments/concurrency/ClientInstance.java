package com.cooksys.ftd.assignments.concurrency;

import java.util.List;
import java.net.Socket;
import java.util.ArrayList;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.message.Request;

public class ClientInstance implements Runnable {

    private ClientInstanceConfig config;
    private Socket socket;

	public ClientInstance(ClientInstanceConfig config) {
        this.config = config;
    }

	public ClientInstance(ClientInstanceConfig config, Socket socket) {
        this.config = config;
    }
	
    @Override
    public void run() {
    	// Set up a socket
//    	Socket socket = new Socket(config.getHost(), config.getPort());
    	
    	// Get the delay that this client instance will have
    	int delay = config.getDelay();
    	
    	// Get the requests that this client instance will make
    	List<Request> requests = new ArrayList<Request>();
        requests = config.getRequests();
    }
}
