import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { allSRVService } from '../all-srvc.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {

  constructor(
    private allSRVService: allSRVService,
    private router: Router
  ) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let user: any = this.allSRVService.getStorage("user");
    let token: any = this.allSRVService.getStorage("credentialsDTO");

    if (user && token && route?.routeConfig?.path) {
      this.allSRVService.user = this.allSRVService.getStorage("user");
      this.allSRVService.credentialsDTO = this.allSRVService.getStorage("credentialsDTO");
    }
    else {
      this.router.navigate(['/login']);
    }

    return true;
  }

}
