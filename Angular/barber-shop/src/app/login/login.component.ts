import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LoginService } from './login.service';
import { ToastrService } from 'ngx-toastr';
import { allSRVService } from '../all-srvc.service';
import { LanguageService } from '../language.service';
import { TranslateService } from '@ngx-translate/core';

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
    private toastrService: ToastrService,
    private languageService: LanguageService,
    private translate: TranslateService
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

    this.loginService.login(this.userName, this.password).subscribe(
      (response: any) => {
        if (response?.id > 0 && response.token) {
          const loginSuccessMessage = this.translate.instant('LOGIN.LOGGED_IN');
          this.toastrService.success(loginSuccessMessage);
          this.allSRVService.user = response;
          this.allSRVService.credentialsDTO = {
            userName: response.userName,
            token: response.token
          };

          this.allSRVService.setStorage('user', this.allSRVService.user);
          this.allSRVService.setStorage('credentialsDTO', this.allSRVService.credentialsDTO);
          this.allSRVService.setStorage('language', this.allSRVService.user?.languageName);
          this.languageService.setLanguage(this.allSRVService?.user?.languageName);
          this.allSRVService.loadMenus();
        } else {
          const invalidMessage = this.translate.instant('INVALID_USERNAME_PASSWORD');
          this.toastrService.error(invalidMessage);
          this.allSRVService.user = null;
          this.allSRVService.credentialsDTO = null;
          this.allSRVService.setStorage('user', null);
          this.allSRVService.setStorage('credentialsDTO', null);
          this.allSRVService.setStorage('language', null);
          this.languageService.setLanguage(null);
        }
      },
      error => {
        console.error(this.translate.instant('LOGIN_FAILED'), error);
        this.allSRVService.user = null;
      }
    );
  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
  }
}
