import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class allSRVService {
    public url: any = 'http://localhost:8080/api/';
    // public url: any = 'https://101.14.1.5:8080/api/';

    public user: any = null;
    public credentialsDTO: any = null;

    public languageUrl: any = "languages";

    constructor(
        private http: HttpClient
    ) {

    }

    setStorage(key: any, value: any): void {
        if (typeof value === 'object') {
            localStorage.setItem(key, JSON.stringify(value));
        } else {
            localStorage.setItem(key, String(value));
        }
    }

    getStorage(key: any): any | null {
        const storedValue = localStorage.getItem(key);
        if (storedValue) {
            try {
                return JSON.parse(storedValue);
            } catch (error) {
                return storedValue;
            }
        }
        return null;
    }

    getAllLanguages() {
        return this.http.get(this.url + this.languageUrl + "/getAllLanguages");
    }

}
