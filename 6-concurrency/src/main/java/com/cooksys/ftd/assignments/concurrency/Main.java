package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import com.cooksys.ftd.assignments.concurrency.model.config.Config;

public class Main {

    /**
     * First, load a {@link com.cooksys.ftd.assignments.concurrency.model.config.Config} object from
     * the <project-root>/config/config.xml file.
     *
     * If the embedded {@link com.cooksys.ftd.assignments.concurrency.model.config.ServerConfig} object
     * is not disabled, create a {@link Server} object with the server config and spin off a thread to run it.
     *
     * If the embedded {@link com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig} object
     * is not disabled, create a {@link Client} object with the client config and spin off a thread to run it.
     * @throws JAXBException 
     * @throws IOException 
     */
    public static void main(String[] args) throws JAXBException, IOException {
    	// Unmarshall the config xml
    	Path path = Paths.get(".\\config\\config.xml");
    	Config config = Config.load(path);
    	
    	// Set up server and start a new thread   	
    	Server server = new Server(config.getServer());
    	if (!config.getServer().isDisabled()) {
	    	Thread serverThread = new Thread(server);
	    	serverThread.start();
    	}

    	// Set up client and start a new thread
    	Client client = new Client(config.getClient());
    	if (!config.getServer().isDisabled()) {
    		Thread clientThread = new Thread(client);
    		clientThread.start();
    	}
    }
}
