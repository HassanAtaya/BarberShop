import { Component } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  userName: string = '';
  password: string = '';

  constructor(private loginService: LoginService,
    private router: Router,
    private toastrService: ToastrService
  ) { }

  login(loginForm: any) {
    if (loginForm && (!this.userName || !this.password)) {
      loginForm.controls['userName']?.markAsTouched();
      loginForm.controls['password']?.markAsTouched();
      return;
    }

    this.loginService.login(this.userName, this.password).subscribe(
      response => {
        if (response != null) {
          this.router.navigateByUrl('/home');
          this.toastrService.success("Logged In");
        } else {
          this.toastrService.error("Invalid Username or Password");
        }
      },
      error => {
        console.error('Login failed', error);
      }
    );
  }

}