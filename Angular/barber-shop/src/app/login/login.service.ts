import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(
        private http: HttpClient,
        private allSRVService: allSRVService
    ) {

    }

    login(userName: any, password: any): Observable<any> {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Access-Control-Allow-Origin': '*'
        });

        return this.http.post<any>(this.allSRVService.url + "login",
            { userName, password },
            { headers }
        );
    }

    logout(): Observable<any> {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Access-Control-Allow-Origin': '*'
        });

        return this.http.post<any>(this.allSRVService.url + "logout",
            this.allSRVService.credentialsDTO,
            { headers }
        );
    }
}
