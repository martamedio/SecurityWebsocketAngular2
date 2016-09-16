import {Component} from 'angular2/core';
import {AuthenticationService} from 'app/login/authentication.service'
import { RouteConfig, ROUTER_DIRECTIVES } from 'angular2/router';
import { WebsocketComponent } from 'app/ws/websocket.component';

@RouteConfig([
    { path: '/Websocket', name: 'Websocket', component: WebsocketComponent, useAsDefault: true },

])
@Component({
    selector: 'login-form',
    providers: [AuthenticationService],
    directives: [ROUTER_DIRECTIVES],
    templateUrl: 'app/private/header.component.html'
})

export class PrivateComponent {

    constructor(
        private _service:AuthenticationService){}

    ngOnInit(){
        this._service.checkCredentials();
    }

    logout() {
        this._service.logout();
    }
}