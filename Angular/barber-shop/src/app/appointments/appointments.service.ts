import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
  providedIn: 'root'
})
export class AppointmentsService {
  private url = "appointments";

  constructor(
    private http: HttpClient,
    private allSRVService: allSRVService
  ) { }

  getAllAppointments(): Observable<any> {
    return this.http.get<any>(this.allSRVService.url + this.url);
  }

  createAppointment(appointment: any): Observable<any> {
    return this.http.post<any>(this.allSRVService.url + this.url, appointment);
  }

  deleteAppointment(id: number): Observable<any> {
    return this.http.delete<any>(`${this.allSRVService.url + this.url}/${id}`);
  }
}