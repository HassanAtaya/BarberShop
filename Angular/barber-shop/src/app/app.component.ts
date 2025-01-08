import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { allSRVService } from './all-srvc.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'barber-shop';

  menus = [
    { label: 'Users', route: '/users' },
    { label: 'Appointments', route: '/appointments' },
    { label: 'Permissions', route: '/permissions' },
    { label: 'Roles', route: '/roles' }
  ];

  constructor(
    public allSRVService: allSRVService,
    private router: Router
  ) { 

  }

  onMenuClick(route: string) {
    this.router.navigateByUrl(route);
  }
}
