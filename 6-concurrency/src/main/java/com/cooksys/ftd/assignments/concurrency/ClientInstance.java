package com.cooksys.ftd.assignments.concurrency;

import java.util.List;
import java.util.ArrayList;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.message.Request;

public class ClientInstance implements Runnable {

    private ClientInstanceConfig config;

	public ClientInstance(ClientInstanceConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
    	List<Request> requests = new ArrayList<Request>();
        requests = config.getRequests();
    }
}
