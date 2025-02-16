import {
  AfterViewInit,
  Component,
  inject,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';
import { NotifiedEmployee } from './notified-employees-datasource';
import { NotificationService } from '../../services/notification.service';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SlicePipe } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { NotifiedEmployeeDialogComponent } from '../notified-employee-dialog/notified-employee-dialog.component';
import { Subscription } from 'rxjs';
import { CommonService } from '../../services/common.service';

@Component({
  selector: 'app-notified-employees',
  templateUrl: './notified-employees.component.html',
  styleUrl: './notified-employees.component.scss',
  standalone: true,
  imports: [
    MatGridListModule,
    MatCardModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIconModule,
    SlicePipe,
  ],
})
export class NotifiedEmployeesComponent implements OnInit, AfterViewInit, OnDestroy {
  cols: number = 3;
  notifiedEmployees: NotifiedEmployee[] = [];
  displayedEmployees: NotifiedEmployee[] = []; // Paginated employees
  pageSize = 18;
  currentPage = 0;
  private notificationService = inject(NotificationService);
  private dialog = inject(MatDialog);
  private breakpointObserver = inject(BreakpointObserver);
  private commonService = inject(CommonService);
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  private refreshSubscription!: Subscription;

  ngOnInit() {
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe((result) => {
        this.cols = result.matches ? 1 : 3;
      });
    this.loadAllNotifiedEmployees();
    this.refreshSubscription = this.notificationService.refresh$.subscribe(() => {
      this.loadAllNotifiedEmployees();
    });
    this.commonService.changeTitle("Notified Employees");
  }
  loadAllNotifiedEmployees() {
    this.notificationService.getAllNotifications().subscribe((data) => {
      this.notifiedEmployees = data;
      this.updateDisplayedEmployees();
    });
  }
  ngAfterViewInit(): void {
    this.paginator.page.subscribe(() => this.onPageChange());
  }

  ngOnDestroy() {
    if (this.refreshSubscription) {
      this.refreshSubscription.unsubscribe();
    }
  }

  onPageChange() {
    this.currentPage = this.paginator.pageIndex;
    this.pageSize = this.paginator.pageSize;
    this.updateDisplayedEmployees();
  }

  updateDisplayedEmployees() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.displayedEmployees = this.notifiedEmployees.slice(
      startIndex,
      endIndex
    );
  }

  openDialog(employee: NotifiedEmployee) {
    const dialogRef = this.dialog.open(NotifiedEmployeeDialogComponent, {
      data: employee,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log(`Dialog result: ${result}`);
      }
      
      this.loadAllNotifiedEmployees();
    });
  }
}
