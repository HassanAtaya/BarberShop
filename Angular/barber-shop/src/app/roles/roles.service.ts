import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
  providedIn: 'root'
})
export class RolesService {
  private url = "roles";

  constructor(
    private http: HttpClient,
    private allSRVService: allSRVService
  ) { 
    
  }

  getAllRoles(): Observable<any> {
    return this.http.get<any>(this.allSRVService.url + this.url);
  }

  createRole(role: any): Observable<any> {
    return this.http.post<any>(this.allSRVService.url + this.url, role);
  }

  deleteRole(id: number): Observable<any> {
    return this.http.delete<any>(`${this.allSRVService.url + this.url}/${id}`);
  }
}