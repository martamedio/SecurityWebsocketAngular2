import {Component, OnInit } from 'angular2/core';
import { Router }            from 'angular2/router';
import {$WebSocket} from './ng2-websocket';

@Component({
  selector: 'my-websocket',
  templateUrl: 'app/websocket.component.html'
})
export class WebsocketComponent implements OnInit{

ngOnInit(){
    this.counter = 'Connecting...';
    console.log("Tryting suscription to Websocket");
    this.ws = new $WebSocket("ws://localhost:9090/message");
    this.ws.send("Hello, it's me!");
    this.ws.getDataStream().subscribe(
      res => {
        var count = JSON.parse(res.data).value;
        this.counter = count;
      },
      function(e) { console.log('Error: ' + e.message); },
      function() { console.log('Connected to WS!'); }
      );
      console.log("WS");
      }

}
