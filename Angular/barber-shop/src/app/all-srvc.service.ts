import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class allSRVService {

    public user: any = null;
    public url: any = 'http://localhost:8080/api/';
    // public url: any = 'https://101.14.1.5:8080/api/';

}
