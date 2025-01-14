import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { allSRVService } from '../all-srvc.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userName: string = '';
  password: string = '';
  passwordVisible: boolean = false;
  @ViewChild('userNameInput') userNameInput!: ElementRef;

  constructor(
    public allSRVService: allSRVService,
    private loginService: LoginService,
    private toastrService: ToastrService
  ) {

  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    if (this.userNameInput) {
      this.userNameInput.nativeElement.focus();
    }
  }

  login(loginForm: any) {
    if (loginForm && (!this.userName || !this.password)) {
      loginForm.controls['userName']?.markAsTouched();
      loginForm.controls['password']?.markAsTouched();
      return;
    }

    this.loginService.login(this.userName, this.password).subscribe((response: any) => {
      if (response?.id > 0 && response.token) {
        this.toastrService.success("Logged In");
        this.allSRVService.user = response;
        this.allSRVService.credentialsDTO = {
          userName: response.userName,
          token: response.token
        };

        this.allSRVService.setStorage("user", this.allSRVService.user);
        this.allSRVService.setStorage("credentialsDTO", this.allSRVService.credentialsDTO);
      } else {
        this.toastrService.error("Invalid Username or Password");
        this.allSRVService.user = null;
        this.allSRVService.credentialsDTO = null;
        this.allSRVService.setStorage("user", null);
        this.allSRVService.setStorage("credentialsDTO", null);
      }
    },
      error => {
        console.error('Login failed', error);
        this.allSRVService.user = null;
      }
    );
  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
  }
}
