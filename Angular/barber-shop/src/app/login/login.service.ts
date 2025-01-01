import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    // private loginUrl = 'http://localhost:8080/api/login';
    private loginUrl = 'http://52.58.132.247:8080/api/login';

    constructor(private http: HttpClient) { }

    login(userName: string, password: string): Observable<any> {
        const headers = new HttpHeaders().set('Content-Type', 'application/json');
        return this.http.post(this.loginUrl, { userName, password }, { headers });
    }

}
