import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { NotifiedEmployeesComponent } from './components/notified-employees/notified-employees.component';
import { UserProfileComponent } from './hr-profile/user-profile/user-profile.component';
import { UserLoginComponent } from './hr-profile/user-login/user-login.component';
import { UserRegistrationComponent } from './hr-profile/user-registration/user-registration.component';
import { canactivaterouteGuard } from './canactivateroute.guard';

export const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'profile', component: UserProfileComponent, canActivate: [canactivaterouteGuard] },
    { path: 'employee-list', component: EmployeeListComponent, canActivate: [canactivaterouteGuard] },
    { path: 'notified-employees', component: NotifiedEmployeesComponent, canActivate: [canactivaterouteGuard]},
    { path: 'login', component: UserLoginComponent },
    { path: 'register', component: UserRegistrationComponent }
];
