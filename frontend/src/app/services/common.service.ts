import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() { }

  private title = new BehaviorSubject<string>("Attrition Analyzer");

  getTitle() {
    return this.title.asObservable();
  }

  changeTitle(newTitle: string) {
    this.title.next(newTitle);
  }
}
