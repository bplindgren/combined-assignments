package com.cooksys.socket.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.Utils;
import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

/**
 * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
 * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
 * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
 * a {@link Server} listening on the given host and port.
 *
 * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
 * over the socket as xml, and should unmarshal that object before printing its details to the console.
 */
public class Client extends Utils {

    public static void main(String[] args) throws UnknownHostException, IOException, JAXBException  {
    	// Needs to know the host and port to connect to
    	JAXBContext jaxb = createJAXBContext();
		// Load the config file and return a Config object
		Config config = loadConfig(".\\config\\config.xml", jaxb);

    	// Create a new socket and communicate to server
    	Socket s = new Socket(config.getRemote().getHost(), config.getLocal().getPort());
    	
    	// Communication link with server
    	BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

    	// Unmarshall the student xml
    	Unmarshaller jbu = jaxb.createUnmarshaller();
    	Student student = (Student) jbu.unmarshal(input);

    	// Print out the student object to the console
    	System.out.println(student.getClass());
    	System.out.println(student.toString());
    	
    	// Close the socket
    	s.close();    	
    }
}
