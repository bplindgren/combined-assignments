package com.cooksys.ftd.assignments.concurrency;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ClientHandler implements Runnable {

    @Override
    public void run() {
    	// Server uses the client handler
    	// Create a new socket with a new thread each time a new client tries to connect
        throw new NotImplementedException();
        // t = new Thread(new ClientInstance);
    }
}
