import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { allSRVService } from './all-srvc.service';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  constructor(private translate: TranslateService,
    private allSRVService: allSRVService
  ) { }

  getCurrentLanguage(): string {
    return this.translate.currentLang;
  }

  setLanguage(language: any): void {
    let l: any = null;

    if (language?.lang) {
      l = language.lang;
    }
    else if (language) {
      l = language;
    }
    else {
      l = "en";
    }

    this.allSRVService.lang = l;
    this.translate.setDefaultLang(l);
    this.translate.use(l);
    localStorage.setItem('language', l);
  }

}
