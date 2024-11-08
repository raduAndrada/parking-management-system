import { Component, OnInit } from '@angular/core';
import { AuthService } from '../core/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(public  authService:  AuthService) { }

  ngOnInit() {
  }
  
  login(username, password) {
    this.authService.login(username, password).subscribe( () => {
      console.log("login successful");
    })
  }


}
