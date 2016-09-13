import {Component} from 'angular2/core';
import {AuthenticationService} from './authentication.service'
import { RouteConfig, ROUTER_DIRECTIVES } from 'angular2/router';
import { WebsocketComponent } from './websocket.component';

@RouteConfig([
    { path: '/Websocket', name: 'Websocket', component: WebsocketComponent, useAsDefault: true },

])
@Component({
    selector: 'login-form',
    providers: [AuthenticationService],
    directives: [ROUTER_DIRECTIVES],
    template: `

            <div class="container" >
                <div class="content">
                    <span>This is a private area, you have successfully logged in!!</span>
                    <a (click)="logout()" href="#">Click Here to logout</a>
                </div>
            </div>
                <nav>

			    </nav>
    		<router-outlet></router-outlet>
    	`
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