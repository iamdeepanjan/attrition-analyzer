import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { NotifiedEmployee } from '../components/notified-employees/notified-employees-datasource';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  constructor() {}

  private http = inject(HttpClient);
  private url: string = 'http://localhost:8082/api/v2';

  private createdByHrId:number = parseInt(localStorage.getItem('userId') || '0');

  private refreshSubject = new BehaviorSubject<void>(undefined);

  // Observable to listen for refresh events
  refresh$ = this.refreshSubject.asObservable();

  // Method to trigger a refresh event
  triggerRefresh() {
    this.refreshSubject.next();
  }

  getAllNotifications() : Observable<NotifiedEmployee[]> {
    return this.http.get<NotifiedEmployee[]>(`${this.url}/allNotification/${this.createdByHrId}`);
  }

  getSingleNotification(id: string): Observable<NotifiedEmployee> {
    return this.http.get<NotifiedEmployee>(`${this.url}/aNotification/${id}`);
  }

  createANotification(id: string, comment: string): Observable<NotifiedEmployee> {
    const payload = { comment: comment, createdByHrId: this.createdByHrId };
    return this.http.post<NotifiedEmployee>(`${this.url}/aNotification/${id}`, payload);
  }

  updateANotification(id: string, comment: string): Observable<NotifiedEmployee> {
    const payload = { comment: comment, createdByHrId: this.createdByHrId };
    return this.http.put<NotifiedEmployee>(`${this.url}/aNotification/${id}`, payload);
  }

  deleteANotification(id: string): Observable<any> {
    return this.http.delete(`${this.url}/aNotification/${id}`);
  }
}
