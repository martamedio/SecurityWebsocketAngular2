package com.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.handler.MessagesHandler;

/**
 * Counter service implementation that returns a counter (+1) each 5 seconds
 */
@Component
public class CounterServiceImpl {

	/**
	 * Counter
	 * */
    private AtomicInteger counter = new AtomicInteger(0);

    /**
     * Websocket Handler to return the counter value
     * */
    @Autowired
    MessagesHandler handler;

    /**
     * Scheduled service that sends (each 5 seconds) through the websocket the counter value
     * */
    @Scheduled(fixedDelay = 5000)
    public void sendCounterUpdate() {
    	handler.serviceCallback(counter.incrementAndGet());
    }

    /**
     * Getter of the counter
     * */
    Integer getValue() {
        return counter.get();
    }

}