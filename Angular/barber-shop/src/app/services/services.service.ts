import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private url = "services";

  constructor(
    private http: HttpClient,
    private allSRVService: allSRVService
  ) { }

  getAllServices(): Observable<any> {
    return this.http.get<any>(this.allSRVService + this.url);
  }

  createService(service: any): Observable<any> {
    return this.http.post<any>(this.allSRVService + this.url, service);
  }

  deleteService(id: number): Observable<any> {
    return this.http.delete<any>(`${this.allSRVService + this.url}/${id}`);
  }
}
