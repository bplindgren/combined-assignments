package com.cooksys.ftd.assignments.file;

<<<<<<< HEAD:4-file-io-serialization/src/main/java/com/cooksys/serialization/assignment/Main.java
=======
import com.cooksys.ftd.assignments.file.model.Contact;
import com.cooksys.ftd.assignments.file.model.Instructor;
import com.cooksys.ftd.assignments.file.model.Session;
import com.cooksys.ftd.assignments.file.model.Student;

import javax.xml.bind.JAXBContext;
>>>>>>> upstream/master:4-file-io-serialization/src/main/java/com/cooksys/ftd/assignments/file/Main.java
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.serialization.assignment.model.Contact;
import com.cooksys.serialization.assignment.model.Instructor;
import com.cooksys.serialization.assignment.model.Session;
import com.cooksys.serialization.assignment.model.Student;

public class Main {

    /**
     * Creates a {@link Student} object using the given studentContactFile.
     * The studentContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param studentContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return a {@link Student} object built using the {@link Contact} data in the given file
     */
    public static Student readStudent(File studentContactFile, JAXBContext jaxb) {
        try {
        	// Create the jaxbUnmarshaller
	    	Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
	    	// Read the file
	        Contact contact = (Contact) jaxbUnmarshaller.unmarshal(studentContactFile);
	        
	        // Create the new student object and set its contact
	        Student student = new Student();
	        student.setContact(contact);
	        
	        return student;
	        
        } catch (JAXBException e) {
        	e.printStackTrace();
        }
		return null;
    }

    /**
     * Creates a list of {@link Student} objects using the given directory of student contact files.
     *
     * @param studentDirectory the directory of student contact files to use
     * @param jaxb the JAXB context to use
     * @return a list of {@link Student} objects built using the contact files in the given directory
     */
    public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) {
    	// Create a new list of students to return
    	List<Student> students = new ArrayList<Student>();
    	
    	// Iterate over each file in the directory
    	for (File file : studentDirectory.listFiles()) {
    		// Read it and add the student to the students list
    		Student student = readStudent(file, jaxb);
    		students.add(student);
        }
    	
    	return students;
    }

    /**
     * Creates an {@link Instructor} object using the given instructorContactFile.
     * The instructorContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param instructorContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return an {@link Instructor} object built using the {@link Contact} data in the given file
     */
    public static Instructor readInstructor(File instructorContactFile, JAXBContext jaxb) {
        try {
        	// Create the jaxbUnmarshaller
	    	Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
	    	// Read the file
	        Contact contact = (Contact) jaxbUnmarshaller.unmarshal(instructorContactFile);
	        
	        // Create the new instructor object and set its contact
	        Instructor instructor = new Instructor();
	        instructor.setContact(contact);
	        
	        return instructor;
	        
        } catch (JAXBException e) {
        	e.printStackTrace();
        }
		return null;
    }

    /**
     * Creates a {@link Session} object using the given rootDirectory. A {@link Session}
     * root directory is named after the location of the {@link Session}, and contains a directory named
     * after the start date of the {@link Session}. The start date directory in turn contains a directory named
     * `students`, which contains contact files for the students in the session. The start date directory
     * also contains an instructor contact file named `instructor.xml`.
     *
     * @param rootDirectory the root directory of the session data, named after the session location
     * @param jaxb the JAXB context to use
     * @return a {@link Session} object built from the data in the given directory
     */
    public static Session readSession(File rootDirectory, JAXBContext jaxb) {
    	// Create a new session
        Session session = new Session();
        
        // Set the session's location
        session.setLocation(rootDirectory.getName());
  
        // Loop over this location's start dates
    	for (File file : rootDirectory.listFiles()) {
    		// Set the start date
    		session.setStartDate(file.getName());
    		
    		// Set the session's instructor
    		File instructorFile = new File(file.getAbsoluteFile() + "\\instructor.xml");
    		session.setInstructor(readInstructor(instructorFile, jaxb));
    		
    		// Set the session's students
    		File studentsDirectory = new File(file.getAbsolutePath() + "\\students");
    		session.setStudents(readStudents(studentsDirectory, jaxb));
        }
    	return session;
    }

    /**
     * Writes a given session to a given XML file
     *
     * @param session the session to write to the given file
     * @param sessionFile the file to which the session is to be written
     * @param jaxb the JAXB context to use
     * @throws JAXBException 
     */
    public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) {
        try {
        	// Create jaxb Marshaller
			Marshaller jaxbMarshaller = jaxb.createMarshaller();
			
			// set the output to be nicely formatted
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// marshal the session object to the session file
			jaxbMarshaller.marshal(session, sessionFile);
			
			// print the xml out to the console
			jaxbMarshaller.marshal(session, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
        
    }

    /**
     * Main Method Execution Steps:
     * 1. Configure JAXB for the classes in the com.cooksys.serialization.assignment.model package
     * 2. Read a session object from the <project-root>/input/memphis/ directory using the methods defined above
     * 3. Write the session object to the <project-root>/output/session.xml file.
     *
     * JAXB Annotations and Configuration:
     * You will have to add JAXB annotations to the classes in the com.cooksys.serialization.assignment.model package
     *
     * Check the XML files in the <project-root>/input/ directory to determine how to configure the {@link Contact}
     *  JAXB annotations
     *
     * The {@link Session} object should marshal to look like the following:
     *      <session location="..." start-date="...">
     *           <instructor>
     *               <contact>...</contact>
     *           </instructor>
     *           <students>
     *               ...
     *               <student>
     *                   <contact>...</contact>
     *               </student>
     *               ...
     *           </students>
     *      </session>
     */
    public static void main(String[] args) {
    	// readSession & writeSession
    	try {
    		// Create file and JAXBContext arguments
    		File file = new File("C:\\code\\java\\combined-assignments\\4-file-io-serialization\\input\\memphis");
    		JAXBContext jaxb = JAXBContext.newInstance(Session.class, Contact.class);
    		
    		// Create the session object
    		Session session = readSession(file, jaxb);
    		
    		// Create the new XML file
    		File sessionFile = new File("C:\\code\\java\\combined-assignments\\4-file-io-serialization\\output\\session.xml");
    		writeSession(session, sessionFile, jaxb);
    		
    	} catch (JAXBException e){
    		e.printStackTrace();
    	}
  
    }
}
