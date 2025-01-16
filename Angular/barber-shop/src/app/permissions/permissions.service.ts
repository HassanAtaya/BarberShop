import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { allSRVService } from '../all-srvc.service';

@Injectable({
    providedIn: 'root',
})
export class PermissionsService {
    private url = "permissions";

    constructor(
        private http: HttpClient,
        private allSRVService: allSRVService
    ) { }

    // Fetch all permissions
    getPermissions(): Observable<any> {
        return this.http.post(`${this.allSRVService.url + this.url}/getPermissions`, {});
    }

    // Add a new permission
    addPermission(permission: any): Observable<any> {
        return this.http.post(`${this.allSRVService.url + this.url}/addPermission`, {name :permission});
    }

    // Update an existing permission (Changed to POST)
    updatePermission(permission: any): Observable<any> {
        return this.http.post(`${this.allSRVService.url + this.url}/updatePermission`, permission);
    }

    // Delete a permission (Changed to POST)
    deletePermission(permissionId: number): Observable<any> {
        return this.http.post(`${this.allSRVService.url + this.url}/deletePermission`, { id: permissionId });
    }

    // Get permissions for a role
    getPermissionsByRole(roleId: number): Observable<any> {
        return this.http.post(`${this.allSRVService.url + this.url}/getPermissionsByRole`, {roleId: roleId} );
    }

    // Assign or unassign a permission to a role
    togglePermissionForRole(roleId: number, permissionId: number, assign: boolean): Observable<any> {
        return this.http.post(`${this.allSRVService.url + this.url}/togglePermissionForRole`, {
            roleId: roleId,
            id: permissionId,
            assign: assign
        });
    }
}
