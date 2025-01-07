import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ServicesComponent } from './services/services.component';
import { UsersComponent } from './users/users.component';
import { RolesComponent } from './roles/roles.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { CurrenciesComponent } from './currencies/currencies.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'users', component: UsersComponent },
  { path: 'roles', component: RolesComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'appointments', component: AppointmentsComponent },
  { path: 'currencies', component: CurrenciesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }