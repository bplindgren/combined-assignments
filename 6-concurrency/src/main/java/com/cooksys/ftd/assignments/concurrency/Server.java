package com.cooksys.ftd.assignments.concurrency;

import com.cooksys.ftd.assignments.concurrency.model.config.ServerConfig;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Server implements Runnable {

	private ServerConfig config;
	
    public Server(ServerConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
        throw new NotImplementedException();
    }
}
