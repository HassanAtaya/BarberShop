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
  showProfile: boolean = false;
  languages: any = [];
  langSelected: any;

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

    this.getAllLanguages();
  }

  onMenuClick(route: string) {
    this.selectedMenu = route;
    this.router.navigateByUrl(route);
  }

  profile() {
    setTimeout(() => {
      this.langSelected = this.allSRVService.getStorage('language');
    }, 1000);
    
    this.showProfile = true;
  }

  getAllLanguages() {
    this.allSRVService.getAllLanguages().subscribe((response: any) => {
      if (response) {
        this.languages = response;
        this.langSelected = this.languages.find(
          (language: any) => language.name === this.allSRVService.user.languageName
        );
      }
    }, (error: any) => {
      console.error(this.translate.instant('ERROR_GETTING_LANGUAGES'), error);
    });
  }

  closeModal() {
    this.showProfile = false;
  }  

  onLanguageChange(event: any) {
    if (this.allSRVService?.user?.id > 0 && this.langSelected?.id > 0) {
      this.allSRVService.updateLanguageByUserID(this.allSRVService.user.id, this.langSelected.id).subscribe((response: any) => {
        if(String(response?.text).includes("Successfully")) {
          this.toastr.success(response.text);
          this.allSRVService.user.languageName = this.langSelected.name;
          this.allSRVService.setStorage('user', this.allSRVService.user);
          this.allSRVService.setStorage('language', this.langSelected.name);
          window.location.reload();
        }
        else {
          this.toastr.error(response.text);
        }
      });
    }
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
