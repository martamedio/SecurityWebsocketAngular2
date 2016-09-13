import {Injectable} from 'angular2/core';
import {Router} from 'angular2/router';
import {Http, Headers} from 'angular2/http';
import 'rxjs/add/operator/map';
export class User {
    constructor(
        public email: string,
        public password: string,
        public grantType: string) {}
}

@Injectable()
export class AuthenticationService {

    constructor(private _router: Router, private _http: Http) {}

    logout() {
        console.log("Logout");
        localStorage.removeItem("user");
        this._router.navigate(['Login']);
    }

    login(user) {

        let headers = new Headers();
        headers.append('Authorization', 'Basic YWRtaW46YWRtaW4='); // Base64 Client for Spring Security ("admin")
        headers.append('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');

        let creds = "username="+user.name+"&password="+user.password+"&grant_type="+user.grantType;

         return  this._http.post('http://localhost:9090/oauth/token', creds, {headers: headers})
            .map(res => res.json());

    }

    checkCredentials() {
        console.log("Checking Credentials!");
        if (localStorage.getItem("user") === null) {
            this._router.navigate(['Login']);
        }
    }

}
