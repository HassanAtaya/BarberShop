import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  menus = [
    { label: 'Users', route: '/users' },
    { label: 'Appointments', route: '/appointments' },
    { label: 'Permissions', route: '/permissions' },
    { label: 'Roles', route: '/roles' }
  ];

  constructor(private router: Router) { }

  onMenuClick(route: string) {
    this.router.navigateByUrl(route);
  }
}
