import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { LoginRequest } from '../hr-profile/user-login/LoginRequest';
import { LoginResponse } from '../hr-profile/user-login/LoginResponse';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private url = 'http://localhost:8084/api/v4/login';
  private http = inject(HttpClient);
  constructor() {}

  login(loginRequest:LoginRequest):Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.url, loginRequest);
  }

  saveUserInLocalStorage(jwtResponse:LoginResponse) : void {
    localStorage.setItem("userId", jwtResponse.userId.toString());
    localStorage.setItem("user", jwtResponse.user.name.toString());
    localStorage.setItem("username", jwtResponse.user.username.toString());
    localStorage.setItem("token", jwtResponse.token);
  }

  getBearerToken() {
    return localStorage.getItem('token');
  }

  clearUserFromLocalStorage() : void {
    localStorage.clear();
  }

  isLoggedIn() : boolean {
    return localStorage.getItem("token") !== null;
  }
}
