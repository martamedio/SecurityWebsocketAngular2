import {Component} from 'angular2/core';
import {AuthenticationService, User} from './authentication.service'
import {Router} from 'angular2/router';

@Component({
    selector: 'login-form',
    providers: [AuthenticationService],
    templateUrl: 'app/login.component.html'
})

export class LoginComponent {

    public user = new User('','','password');
    public errorMsg = '';

    constructor(private _router: Router,
        private _service:AuthenticationService) {}

    login() {
    	this._service.login(this.user).subscribe(
               (data) => { console.log(data)},  //changed
               (err)=>{console.log("ERROR"); this.errorMsg = 'Wrong authentication!';},
               ()=>{console.log("Done");  localStorage.setItem("user", "true");this._router.navigate(['Home']);}
            );
    }
}

