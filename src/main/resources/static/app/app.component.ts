import {Component} from 'angular2/core';
import {LoginComponent} from './login.component';
import {PrivateComponent} from './private.component';
import {WebsocketComponent} from './websocket.component';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'my-app',
    directives: [LoginComponent, ROUTER_DIRECTIVES],
    template: `
            <router-outlet></router-outlet>
        `
})
@RouteConfig([
    { path: '/home/...', name: 'Home', component: PrivateComponent,  useAsDefault: true  },
    { path: '/', name: 'Login', component: LoginComponent },
    { path: '/Websocket', name: 'Websocket', component: WebsocketComponent },


])
export class AppComponent {}

