import {Component, OnInit } from 'angular2/core';
import { Router }            from 'angular2/router';
import {$WebSocket} from 'app/lib/ng2-websocket';

@Component({
  selector: 'my-websocket',
  templateUrl: 'app/ws/websocket.component.html'
})
export class WebsocketComponent implements OnInit{

ngOnInit() {
    this.counter = 'Connecting...'
    console.log("Waiting for the suscription to WS");
    let ws = new $WebSocket("ws://localhost:9090/message");
    ws.send("Hello, it's me!");
    ws.getDataStream().subscribe(
        res => {
            var count = JSON.parse(res.data).value;
            this.counter = count;
        },
        function(e) {
            console.log('Error: ' + e.message);
        }
    );
}

}