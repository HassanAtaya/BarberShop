import { Component, OnInit } from '@angular/core';
import { RolesService } from './roles.service';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { allSRVService } from '../all-srvc.service';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.scss']
})
export class RolesComponent implements OnInit {
  roles: any[] = [];
  roleForm: any = {
    name: ''
  };

  constructor(
    private rolesService: RolesService,
    private toast: ToastrService,
    private translate: TranslateService,
    public allSRVService: allSRVService
  ) {

  }

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles() {
    this.rolesService.getAllRoles().subscribe(
      data => {
        this.roles = data;
      },
      error => {
        console.error(this.translate.instant('ERROR_LOADING_ROLES'), error);
      }
    );
  }

  createRole(role: any) {
    if (!(String(role?.name).length > 0)) {
      const warningMessage = this.translate.instant('ROLE_SHOULD_BE_FILLED');
      this.toast.warning(warningMessage);
    }
    else {
      this.rolesService.createRole(role).subscribe(
        response => {
          this.loadRoles();
          this.resetForm();
          const successMessage = this.translate.instant('ROLE_CREATED');
          this.toast.success(successMessage);
        },
        error => {
          this.toast.error(error?.error);
          console.error(this.translate.instant('ERROR_CREATING_ROLE'), error);
        }
      );
    }
  }

  deleteRole(id: number) {
    this.rolesService.deleteRole(id).subscribe(
      response => {
        this.loadRoles();
      },
      error => {
        console.error(this.translate.instant('ERROR_DELETING_ROLE'), error);
        this.toast.error(error?.error);
      }
    );
  }

  resetForm() {
    this.roleForm = {
      name: ''
    };
  }
}
