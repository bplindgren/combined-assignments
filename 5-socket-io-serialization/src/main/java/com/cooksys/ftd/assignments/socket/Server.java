package com.cooksys.ftd.assignments.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     * @throws JAXBException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
    	Unmarshaller jbu = jaxb.createUnmarshaller();
    	return (Student) jbu.unmarshal(new File(studentFilePath));
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     * @throws IOException 
     * @throws JAXBException 
     */
    public static void main(String[] args) throws IOException, JAXBException {
		// Create jaxb Context
		JAXBContext jaxb = createJAXBContext();
		// Load the config file and return a Config object
		Config config = loadConfig(".\\config\\config.xml", jaxb);
	
    	// Start listening
        ServerSocket ss = new ServerSocket(config.getLocal().getPort());
        System.out.println("Server is Listening");
        Socket s = ss.accept();
		
		// Communication line with client
		BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		// Unmarshall the student xml
		Unmarshaller jbu = jaxb.createUnmarshaller();
		File studentFile = new File(".\\config\\student.xml");
		Student student = (Student) jbu.unmarshal(studentFile);

		// Marshall the student object and send it back to the client
		Marshaller jbm = jaxb.createMarshaller();
		jbm.marshal(student, s.getOutputStream());
		
		// Server shut down
		s.close();
		ss.close();
    }
}
