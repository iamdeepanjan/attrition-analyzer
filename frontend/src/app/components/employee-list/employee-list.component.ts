import {
  Component,
  inject,
  OnInit,
  AfterViewInit,
  ViewChild,
  OnDestroy,
} from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from '../../services/employee.service';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, Sort, MatSortModule } from '@angular/material/sort';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { EmployeeDialogComponent } from '../employee-dialog/employee-dialog.component';
import { NotificationService } from '../../services/notification.service';
import { Subscription } from 'rxjs';
import { CommonService } from '../../services/common.service';

@Component({
  selector: 'app-employee-list',
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, MatButtonModule],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.scss',
})
export class EmployeeListComponent implements OnInit, AfterViewInit {
  employees: MatTableDataSource<Employee> = new MatTableDataSource<Employee>();
  displayedColumns: string[] = [];
  notifiedEmployeeIds: Set<string> = new Set<string>();
  private employeeService = inject(EmployeeService);
  private notificationService = inject(NotificationService);
  private _liveAnnouncer = inject(LiveAnnouncer);
  private breakpointObserver = inject(BreakpointObserver);
  private dialog = inject(MatDialog);
  private refreshSubscription!: Subscription;
  private commonService = inject(CommonService);
  

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit() {
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe((result) => {
        if (result.matches) {
          this.displayedColumns = ['Name', 'Attrition', 'Action'];
        } else {
          this.displayedColumns = [
            'EmployeeID',
            'Name',
            'Gender',
            'Department',
            'JobRole',
            'Attrition',
            'Action',
          ];
        }
      });
    this.loadAllEmployees();
    this.loadNotifications();
    this.refreshSubscription = this.employeeService.refresh$.subscribe(() => {
      this.loadAllEmployees();
      this.loadNotifications();
    });
    this.commonService.changeTitle("All Employees Data");
  }

  loadAllEmployees() {
    this.employeeService.getEmployees().subscribe((data) => {
      this.employees.data = data;
    });
  }

  loadNotifications() {
    this.notificationService
      .getAllNotifications()
      .subscribe((notifications) => {
        this.notifiedEmployeeIds = new Set(
          notifications.map((notification) => notification.id)
        );
      });
  }

  isEmployeeNotified(employeeId: string): boolean {
    return this.notifiedEmployeeIds.has(employeeId);
  }

  ngAfterViewInit() {
    this.employees.paginator = this.paginator;
    this.employees.sort = this.sort;
    setTimeout(() => {
      this.sort.sort({ id: 'Attrition', start: 'asc', disableClear: false });
    });
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  openDialog(employee: Employee) {
    const dialogRef = this.dialog.open(EmployeeDialogComponent, {
      data: employee,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log(`Dialog result: ${result}`);
      }
      this.loadAllEmployees();
    });
  }
}
