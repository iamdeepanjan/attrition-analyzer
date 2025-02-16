import { Component } from '@angular/core';
import { NavigationComponent } from "./header/navigation/navigation.component";

@Component({
  selector: 'app-root',
  imports: [NavigationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
}
