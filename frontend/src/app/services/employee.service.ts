import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Employee } from '../components/employee-list/employee';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  constructor() {}

  private http = inject(HttpClient);
  private url: string = 'http://localhost:8765/api/v1';

  private refreshSubject = new BehaviorSubject<void>(undefined);

  refresh$ = this.refreshSubject.asObservable();

  triggerRefresh() {
    this.refreshSubject.next();
  }

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.url}/allEmployees`);
  }

  getAttritionEmployee(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.url}/allAttritionEmployees`);
  }

  getSingleEmployee(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.url}/allEmployees/${id}`);
  }
}
