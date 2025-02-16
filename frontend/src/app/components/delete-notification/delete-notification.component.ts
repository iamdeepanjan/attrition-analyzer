import { Component, inject, OnDestroy } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { NotificationService } from '../../services/notification.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-delete-notification',
  imports: [MatDialogModule, MatButtonModule],
  templateUrl: './delete-notification.component.html',
  styleUrl: './delete-notification.component.scss',
})
export class DeleteNotificationComponent {
  data = inject(MAT_DIALOG_DATA);
  private notificationService = inject(NotificationService);
  private employeeService = inject(EmployeeService);
  private dialogRef = inject(MatDialogRef<DeleteNotificationComponent>);
  private _snackBar = inject(MatSnackBar);

  private deletedNotification: any; // Store the deleted notification

  onConfirmDelete(id: string) {
    this.deletedNotification = this.data;
    this.notificationService.deleteANotification(id).subscribe({
      next: (response) => {
        console.log('Notification deleted successfully');
        this.dialogRef.close(true);
        this.openSnackBar('Notification deleted successfully', 'Undo');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  openSnackBar(message: string, action: string) {
    const snackBarRef = this._snackBar.open(message, action, {
      duration: 3000,
    });
    snackBarRef.onAction().subscribe(() => {
      this.undoDelete();
      this.openUndoSnackBar('Notification restored successfully', 'Done');
    });
  }

  openUndoSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000,
    });
  }  
  undoDelete() {
    if (this.deletedNotification) {
      // Call the API to restore the deleted notification
      this.employeeService.getSingleEmployee(this.deletedNotification.id).subscribe({
        next: (response) => {
          this.notificationService.createANotification(this.deletedNotification.id, this.deletedNotification.comment).subscribe({
            next: (response) => {
              console.log('Notification restored successfully');
              this.notificationService.triggerRefresh();
            },
            error: (err) => {
              console.error('Failed to restore the notification', err);
            },
          });

          this.deletedNotification = null; // Clear temporary storage
        }
      });
    }
  }
}
