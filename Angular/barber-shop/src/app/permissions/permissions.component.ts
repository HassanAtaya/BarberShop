import { Component, OnInit } from '@angular/core';
import { PermissionsService } from './permissions.service';
import { ToastrService } from 'ngx-toastr';
import { RolesService } from '../roles/roles.service';
import { TranslateService } from '@ngx-translate/core';
import { allSRVService } from '../all-srvc.service';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.scss']
})
export class PermissionsComponent implements OnInit {

  permissions: any[] = [];
  roles: any[] = [];
  rolePermissions: any[] = [];
  allPermissions: any[] = [];
  selectedRole: any = null;
  showRoleDialog = false;
  newPermissionName: any;

  constructor(
    private permissionsService: PermissionsService,
    private toast: ToastrService,
    private rolesService: RolesService,
    private translate: TranslateService,
    public allSRVService: allSRVService
  ) {

  }

  ngOnInit(): void {
    this.loadPermissions();

    this.rolesService.getAllRoles().subscribe((response: any) => {
      if (response) {
        this.roles = response;
      }
    }, (error: any) => {
      console.error(this.translate.instant('ERROR_GETTING_ROLES'), error);
    });
  }

  loadPermissions() {
    this.permissionsService.getPermissions().subscribe((data) => {
      this.permissions = data;
    });
  }

  onAdd() {
    if (this.newPermissionName) {
      this.permissionsService.addPermission(this.newPermissionName).subscribe(() => {
        this.loadPermissions();

        const successMessage = this.translate.instant('PERMISSION_ADDED', { permissionName: this.newPermissionName });
        this.toast.success(successMessage);
        this.newPermissionName = null;
      }, (error: any) => {
        this.toast.error(error?.error);
      });
    }
    else {
      const errorMessage = this.translate.instant('FILL_PERMISSION_NAME');
      this.toast.error(errorMessage);
    }
  }

  onEdit(permission: any) {
    const updatedPermission = { ...permission, name: prompt('Edit Name', permission.name) || permission.name };
    this.permissionsService.updatePermission(updatedPermission).subscribe(() => {
      this.loadPermissions();
    }, (error: any) => {
      this.toast.error(error?.error);
    });
  }

  onDelete(permissionId: number) {
    this.permissionsService.deletePermission(permissionId).subscribe(() => {
      this.loadPermissions();
    }, (error: any) => {
      this.toast.error(error?.error);
    });
  }

  onAssign() {
    this.selectedRole = this.roles[0];
    this.onRoleChange(this.roles[0]);
  }

  onRoleChange(role: any) {
    if (role) {
      this.selectedRole = role;
      this.permissionsService.getPermissionsByRole(role.id).subscribe((data) => {
        this.rolePermissions = data;

        this.permissions.forEach(permission => {
          permission.assigned = this.rolePermissions.some(rp => rp.id === permission.id);
        });

        this.showRoleDialog = true;
      });
    }
  }

  onPermissionToggle(permissionId: number, assigned: boolean) {
    if (this.selectedRole) {
      this.permissionsService.togglePermissionForRole(this.selectedRole.id, permissionId, assigned).subscribe();
    }
  }
}
