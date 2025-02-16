import { Component, inject, OnInit } from '@angular/core';
import { CommonService } from '../../services/common.service';
import { UserProfileService } from '../../services/user-profile.service';
import { UserDTO } from './UserDTO';
import { MatCardModule } from '@angular/material/card';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-user-profile',
  imports: [MatCardModule, DatePipe],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss'
})
export class UserProfileComponent implements OnInit {
  constructor() { }
  user!: UserDTO;
  error: string = '';
  private userService = inject(UserProfileService);
  private commonService = inject(CommonService);

  ngOnInit(): void {
    this.commonService.changeTitle("HR Profile");
    this.fetchUserDetails();
  }

  fetchUserDetails(): void {
    this.userService.getUserById().subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (err) => {
        this.error = 'Failed to load user details';
        console.error(err);
      },
    });
  }

}
