package com.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Controller for Websocket.
 */
@Component
public class MessagesHandler extends TextWebSocketHandler {

	/**
	 * Websocket Session.
	 */
	private Set<WebSocketSession> sessions = new HashSet<>();

	public void serviceCallback(int counter) {

		try {
			List<WebSocketSession> oldSessions = new ArrayList<>();
			for (WebSocketSession session: sessions) {
				if (session != null) {
					if (session.isOpen()) {
						session.sendMessage(new TextMessage("{\"value\": \"" + counter + "\"}"));
					}else{
						oldSessions.add(session);
					}
				}
			}
			sessions.removeAll(oldSessions);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


	@Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("New connection established");
		sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
            session.close();
        } else {
            System.out.println("Received: " + message.getPayload());
        }
    }

}
