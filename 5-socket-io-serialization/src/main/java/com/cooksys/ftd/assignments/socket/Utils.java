package com.cooksys.ftd.assignments.socket;

<<<<<<< HEAD:5-socket-io-serialization/src/main/java/com/cooksys/socket/assignment/Utils.java
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.socket.assignment.model.Config;
import com.cooksys.socket.assignment.model.Student;
=======
import com.cooksys.ftd.assignments.socket.model.Config;

import javax.xml.bind.JAXBContext;
>>>>>>> upstream/master:5-socket-io-serialization/src/main/java/com/cooksys/ftd/assignments/socket/Utils.java

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     * @throws JAXBException 
     */
    public static JAXBContext createJAXBContext() throws JAXBException {
    	return JAXBContext.newInstance(Config.class, Student.class);
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     * @throws JAXBException 
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) throws JAXBException {
		Unmarshaller jbu = jaxb.createUnmarshaller();
    	return (Config) jbu.unmarshal(new File(configFilePath));
    }
}
