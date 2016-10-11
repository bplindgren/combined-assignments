package com.cooksys.ftd.assignments.concurrency;

import java.io.File;

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
     */
    public static void main(String[] args) {
    	File configFile = new File(".\\config\\config.xml");
    	Config config = new Config(Config.load(configFile));
    }
}
