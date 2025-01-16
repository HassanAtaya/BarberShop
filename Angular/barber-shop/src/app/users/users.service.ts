import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private apiUrl: any = null;

  constructor(
    private http: HttpClient,
    private allSRVService: allSRVService
  ) {
    this.apiUrl = this.allSRVService.url + "users";
  }

  // Get all users
  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Create a user
  saveUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + "/saveUser", user);
  }

  // Update user (same API as create for simplicity, or you can use a separate PUT request)
  updateUser(user: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${user.id}`, user);
  }

  // Delete user
  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  // Get user by username (for login or validation)
  getUserByUsername(userName: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${userName}`);
  }
}