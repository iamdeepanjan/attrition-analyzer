import { Component, inject, OnInit } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NotificationService } from '../../services/notification.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-update-notification',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    ReactiveFormsModule,
  ],
  templateUrl: './update-notification.component.html',
  styleUrl: './update-notification.component.scss',
})
export class UpdateNotificationComponent implements OnInit {
  data = inject(MAT_DIALOG_DATA);
  notificationForm!: FormGroup;
  private dialogRef = inject(MatDialogRef<UpdateNotificationComponent>);
  private fb = inject(FormBuilder);
  private notificationService = inject(NotificationService);
  private _snackBar = inject(MatSnackBar);

  constructor() {
    const id = this.data.id;
    this.notificationService.getSingleNotification(id).subscribe({
      next: (response) => {
        this.notificationForm.patchValue({
          comment: response.comment,
        });
      },
    });
  }

  ngOnInit(): void {
    this.notificationForm = this.fb.group({
      comment: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  get comment() {
    return this.notificationForm.get('comment');
  }

  onSubmit(): void {
    if (this.notificationForm.valid) {
      const id = this.data.id;
      const comment = this.notificationForm.value.comment;
      this.notificationService.updateANotification(id, comment).subscribe({
        next: (response) => {
          console.log('Notification updated successfully');
          this.dialogRef.close(true);
          this.openSnackBar('Notification updated successfully', 'Done');
        },
        error: (err) => {
          console.error('Failed to update notification', err);
        },
      });
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000,
    });
  }
}
