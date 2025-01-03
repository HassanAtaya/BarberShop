import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ServicesComponent } from './services/services.component';
import { UsersComponent } from './users/users.component';
import { RolesComponent } from './roles/roles.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { CurrenciesComponent } from './currencies/currencies.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'users', component: UsersComponent },
  { path: 'roles', component: RolesComponent },
  { path: 'appointments', component: AppointmentsComponent },
  { path: 'currencies', component: CurrenciesComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }