import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { allSRVService } from './all-srvc.service';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'barber-shop';
  selectedMenu: string | null = null;
  menus = [
    { label: 'Users', route: '/users' },
    { label: 'Permissions', route: '/permissions' },
    { label: 'Roles', route: '/roles' }
  ];

  constructor(
    public allSRVService: allSRVService,
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService
  ) { 

  }

  onMenuClick(route: string) {
    this.selectedMenu = route;
    this.router.navigateByUrl(route);
  }

  logout() {
    this.loginService.logout().subscribe((response: any) => {
        if (response?.text == "Logout successful.") {
          this.toastr.success(response.text);
          this.router.navigateByUrl("/login");
          this.allSRVService.user = null;
          this.allSRVService.credentialsDTO = null;
          this.allSRVService.setStorage("user", null);
          this.allSRVService.setStorage("credentialsDTO", null);
        } else {
          this.toastr.error(response?.text);
        }
      },
      error => {
        console.error('Login failed', error);
        this.toastr.error("Login failed, check console");
      }
    );
  }
}
