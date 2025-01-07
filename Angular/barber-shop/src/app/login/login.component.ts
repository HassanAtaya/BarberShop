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
    private loginService: LoginService,
    private router: Router,
    private toastrService: ToastrService,
    private allSRVService: allSRVService
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
        if (response?.id > 0) {
          this.router.navigateByUrl('/home');
          this.toastrService.success("Logged In");
          this.allSRVService.user = response;
        } else {
          this.toastrService.error("Invalid Username or Password");
          this.allSRVService.user = null;
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
