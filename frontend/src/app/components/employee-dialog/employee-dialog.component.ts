import { Component, inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { CreateNotificationComponent } from '../create-notification/create-notification.component';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employee-dialog',
  imports: [MatDialogModule, MatButtonModule, DatePipe, CurrencyPipe],
  templateUrl: './employee-dialog.component.html',
  styleUrl: './employee-dialog.component.scss',
})
export class EmployeeDialogComponent {
  data = inject(MAT_DIALOG_DATA);
  private dialog = inject(MatDialog);
  private employeeService = inject(EmployeeService);

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateNotificationComponent, {
      data: this.data,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log(`Dialog result: ${result}`);
        this.employeeService.triggerRefresh();
      }
    });
  }
}
