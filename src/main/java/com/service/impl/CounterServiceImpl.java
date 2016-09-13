package com.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.handler.MessagesHandler;

/**
 *
 */
@Component
public class CounterServiceImpl {

    private AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    MessagesHandler handler;

    @Scheduled(fixedDelay = 5000)
    public void sendCounterUpdate() {
    	handler.serviceCallback(counter.incrementAndGet());
    }

    Integer getValue() {
        return counter.get();
    }

}