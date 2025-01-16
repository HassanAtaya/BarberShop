import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { allSRVService } from './all-srvc.service';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from './login/login.service';
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from './language.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'barber-shop';
  selectedMenu: string | null = null;
  direction: string = 'ltr';
  userLanguage: any = "en";

  constructor(
    public allSRVService: allSRVService,
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService,
    private translate: TranslateService,
    private languageService: LanguageService
  ) {
    this.translate.setDefaultLang('en');
    this.translate.use('en');
    if (localStorage.getItem('language')) {
      this.userLanguage = localStorage.getItem('language');
    }
    this.languageService.setLanguage(this.userLanguage);
    this.allSRVService.loadMenus();
  }

  ngOnInit() {
    if (this.userLanguage === 'ar') {
      this.direction = 'rtl';
    } else {
      this.direction = 'ltr';
    }
  }

  onMenuClick(route: string) {
    this.selectedMenu = route;
    this.router.navigateByUrl(route);
  }

  logout() {
    this.loginService.logout().subscribe((response: any) => {
      if (response?.text === "Logout successful.") {
        this.toastr.success(response.text);
        this.router.navigateByUrl("/login");
        this.allSRVService.user = null;
        this.allSRVService.credentialsDTO = null;
        this.allSRVService.setStorage("user", null);
        this.allSRVService.setStorage("credentialsDTO", null);
      } else {
        this.toastr.error(response?.text);
      }
    },
      error => {
        console.error('Login failed', error);
        this.toastr.error("Login failed, check console");
      }
    );
  }
}
