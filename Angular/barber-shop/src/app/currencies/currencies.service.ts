import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
  providedIn: 'root'
})
export class CurrenciesService {
  private url = "currencies";

  constructor(
    private http: HttpClient,
    private allSRVService: allSRVService
  ) { }

  getAllCurrencies(): Observable<any> {
    return this.http.get<any>(this.allSRVService.url + this.url);
  }

  createCurrency(currency: any): Observable<any> {
    return this.http.post<any>(this.allSRVService.url + this.url, currency);
  }

  deleteCurrency(id: number): Observable<any> {
    return this.http.delete<any>(`${this.allSRVService.url + this.url}/${id}`);
  }
}