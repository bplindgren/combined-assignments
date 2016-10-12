package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
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
    	
    	// Set up server and client instances    	
    	Server server = new Server(config.getServer());
    	Client client = new Client(config.getClient());
    	
    	// Start the server
    	ServerSocket ss = new ServerSocket(config.getServer().getPort());
    	System.out.println("Server is Listening");
    	
    	
    	List<ClientInstanceConfig> clients = new ArrayList<ClientInstanceConfig>();
    	clients = config.getClient().getInstances();
    	
    	// Using the List<ClientInstanceConfig> clients, 
    	// create client instances
    	for (ClientInstanceConfig c : clients) {
    		ClientInstance cInst = new ClientInstance(c);
    		Thread t = new Thread(cInst);
//    		cInst.start();
    	}
    	
    	
    }
}
