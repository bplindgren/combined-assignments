package com.cooksys.socket.assignment;

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

import com.cooksys.ftd.assignments.socket.Utils;
import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the
     * @param jaxb
     * @return
     * @throws JAXBException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
    	Unmarshaller jbu = jaxb.createUnmarshaller();
    	return (Student) jbu.unmarshal(new File(studentFilePath));
    }

    public static void main(String[] args) throws JAXBException, IOException  {
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
