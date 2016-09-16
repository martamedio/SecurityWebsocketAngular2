import {Component} from 'angular2/core';
import {AuthenticationService, User} from 'app/login/authentication.service'
import {Router} from 'angular2/router';

@Component({
    selector: 'login-form',
    providers: [AuthenticationService],
    templateUrl: 'app/login/login.component.html'
})

export class LoginComponent {

    public user = new User('','','password');
    public errorMsg = '';

    constructor(private _router: Router,
        private _service:AuthenticationService) {}

    login() {
      if (this.user.name != '' && this.user.password != ''){
      	this._service.login(this.user).subscribe(
                 (data) => { console.log("Authentication OK!")}, 
                 (err)=>{console.log("ERROR"); this.errorMsg = 'Wrong authentication!';},
                 ()=>{localStorage.setItem("user", "true");this._router.navigate(['Home']);}
              );
      }
    }
}

