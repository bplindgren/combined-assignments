package com.cooksys.ftd.assignments.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.message.Request;

public class ClientInstance implements Runnable {

	private ClientInstanceConfig config;
	private Socket socket;
	
	private static Logger logger = LoggerFactory.getLogger(ClientInstance.class);

	public ClientInstance(ClientInstanceConfig config) {
		this.config = config;
	}

	public ClientInstance(ClientInstanceConfig config, Socket socket) {
		this.config = config;
		this.socket = socket;
	}

	@Override
	public void run() {
		logger.info("In the CI run method");
		BufferedReader input;
		BufferedWriter output;

		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// // Get the delay that this client instance will have
			int delay = config.getDelay();

			// Get the requests that this client instance will make
			List<Request> requests = new ArrayList<Request>();
			requests = config.getRequests();

			for (Request r : requests) {
				output.write(r.getType().name() + "\n");
				output.flush();
				System.out.println(input.readLine());
				try {
					Thread.sleep(delay * 2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
