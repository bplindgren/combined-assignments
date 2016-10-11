package com.cooksys.ftd.assignments.concurrency.model.config;

import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

    /**
     * Loads a {@link Config} object from the given xml file path
     *
     * @param path the path at which an xml configuration can be found
     * @return the unmarshalled {@link Config} object
     * @throws JAXBException 
     */
    public static Config load(Path path) throws JAXBException {
        JAXBContext jaxb = JAXBContext.newInstance(Config.class);
        Unmarshaller jbu = new jaxb.createUnmarshaller();
    }

    /**
     * server configuration
     */
    @XmlElement
    private ServerConfig server;

    /**
     * client configuration
     */
    @XmlElement
    private ClientConfig client;

    public ServerConfig getServer() {
        return server;
    }

    public void setServer(ServerConfig server) {
        this.server = server;
    }

    public ClientConfig getClient() {
        return client;
    }

    public void setClient(ClientConfig client) {
        this.client = client;
    }
}
