import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { RegisterRequest } from '../hr-profile/user-registration/RegisterRequest';
import { Observable } from 'rxjs';
import { UserDTO } from '../hr-profile/user-profile/UserDTO';

@Injectable({
  providedIn: 'root',
})
export class UserProfileService {
  constructor() {}
  private http = inject(HttpClient);

  private url = 'http://localhost:8083/api/v3';


  registerUser(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post<any>(`${this.url}/register`, registerRequest);
  }

  getUserById(): Observable<UserDTO> {
    const userId = parseInt(localStorage.getItem('userId') || '0');
    return this.http.get<UserDTO>(`${this.url}/users/${userId}`);
  }
}
