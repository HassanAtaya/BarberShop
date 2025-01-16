import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class allSRVService {
    public url: any = 'http://localhost:8080/api/';
    // public url: any = 'https://101.14.1.5:8080/api/';

    public user: any = null;
    public credentialsDTO: any = null;

    public languageUrl: any = "languages";
    menus: any = [];
    lang: any = "en";

    constructor(
        private http: HttpClient
    ) {

    }

    setStorage(key: any, value: any): void {
        if (typeof value === 'object') {
            localStorage.setItem(key, JSON.stringify(value));
        } else {
            localStorage.setItem(key, String(value));
        }
    }

    getStorage(key: any): any | null {
        const storedValue = localStorage.getItem(key);
        if (storedValue) {
            try {
                return JSON.parse(storedValue);
            } catch (error) {
                return storedValue;
            }
        }
        return null;
    }

    getAllLanguages() {
        return this.http.get(this.url + this.languageUrl + "/getAllLanguages");
    }

    checkIfPermission(name: string): boolean {
        return this.user?.rolePermissions?.some(
            (element: any) =>
                element?.permission?.name === name ||
                element?.permission?.name === "ALL_PERMISSIONS"
        ) || false;
    }

    loadMenus() {
        if (this.checkIfPermission('ALL_PERMISSIONS')) {
          this.menus = [
            { label: 'Users', route: '/users' },
            { label: 'Roles', route: '/roles' },
            { label: 'Permissions', route: '/permissions' }
          ];
        }
        else {
          if (this.checkIfPermission('Add User') ||
            this.checkIfPermission('Edit User') ||
            this.checkIfPermission('Delete User')) {
            this.menus.push({ label: 'Users', route: '/users' });
          }
          if (this.checkIfPermission('Add Role') ||
            this.checkIfPermission('Edit Role') ||
            this.checkIfPermission('Delete Role')) {
            this.menus.push({ label: 'Roles', route: '/roles' });
          }
          if (this.checkIfPermission('Add Permission') ||
            this.checkIfPermission('Edit Permission') ||
            this.checkIfPermission('Delete Permission')) {
            this.menus.push({ label: 'Permissions', route: '/permissions' });
          }
        }
    
        this.setStorage("menus", this.menus);
      }

}
