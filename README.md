# SecurityWebsocketAngular2

This is a small web demo using Spring Boot + Spring Security for authentication + Websocket + Angular2, I'm using it for playing and learning how Angular2 works (I'm importing Angular2 from a CND at index.html).

So far, there's a authentication process made with Spring Security and a Websocket that returns a counter each 5 seconds and prints it in the user's private area.

## Getting Started

### Prerequisities

You only need:

```
* Java 1.8
* Maven
```

## Running it!

* Run *ServerRunner* for set up the Endpoint
* Access to: http://localhost:9090 and sign in with "user"/"1234", you can access to the user's private area and see the websocket counter
