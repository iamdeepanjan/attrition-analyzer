import { Component, inject, OnDestroy } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogModule,
  MatDialogRef
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { NotificationService } from '../../services/notification.service';
import { MatIconModule } from '@angular/material/icon';
import { DeleteNotificationComponent } from '../delete-notification/delete-notification.component';
import { UpdateNotificationComponent } from '../update-notification/update-notification.component';

@Component({
  selector: 'app-notified-employee-dialog',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatIconModule,
    DatePipe,
    CurrencyPipe,
  ],
  templateUrl: './notified-employee-dialog.component.html',
  styleUrl: './notified-employee-dialog.component.scss',
})
export class NotifiedEmployeeDialogComponent{
  data = inject(MAT_DIALOG_DATA);
  private dialog = inject(MatDialog);
  private notificationService = inject(NotificationService);

  openDeleteDialog() {
    const dialogRef = this.dialog.open(DeleteNotificationComponent, {
      data: this.data,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log(`Dialog result: ${result}`);
        this.notificationService.triggerRefresh();
      }
    });
  }

  openUpdateDialog() {
    const dialogRef = this.dialog.open(UpdateNotificationComponent, {
      data: this.data,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log(`Dialog result: ${result}`);
        this.notificationService.triggerRefresh();
      }
    });
  }
}
