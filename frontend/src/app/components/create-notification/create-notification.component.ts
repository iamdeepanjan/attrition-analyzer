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
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-create-notification',
  templateUrl: './create-notification.component.html',
  styleUrl: './create-notification.component.scss',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    ReactiveFormsModule,
  ],
})
export class CreateNotificationComponent implements OnInit {
  data = inject(MAT_DIALOG_DATA);
  notificationForm!: FormGroup;
  private dialogRef = inject(MatDialogRef<CreateNotificationComponent>);
  private fb = inject(FormBuilder);
  private notificationService = inject(NotificationService);
  private employeeService = inject(EmployeeService);
  private _snackBar = inject(MatSnackBar);

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
      const employeeId = this.data.id;
      const comment = this.notificationForm.value.comment;
      this.employeeService.getSingleEmployee(employeeId).subscribe({
        next: (response) => {
          setTimeout(() => {
            this.notificationService
              .createANotification(employeeId, comment)
              .subscribe({
                next: (response) => {
                  console.log('Notification created successfully');
                  this.dialogRef.close(true);
                  this.openSnackBar(
                    'Notification created successfully',
                    'Done'
                  );
                },
                error: (err) => {
                  console.error('Failed to create notification', err);
                },
              });
          }, 300);
        },
        error: (err) => {
          console.error('Failed to get employee', err);
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
