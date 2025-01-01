import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './login.service';  // Import your LoginService

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService) {
    this.loginForm = this.fb.group({
      userName: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit(): void { }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { userName, password } = this.loginForm.value;
      console.log('Login attempt:', userName, password);

      this.loginService.login(userName, password).subscribe(
        (response) => {
          console.log('Login successful', response);
        },
        (error) => {
          console.error('Login failed', error);
        }
      );
    }
  }
}
