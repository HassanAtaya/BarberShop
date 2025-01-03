import { Component, OnInit } from '@angular/core';
import { RolesService } from './roles.service';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.scss']
})
export class RolesComponent implements OnInit {
  roles: any[] = [];
  roleForm: any = { // Initialize the form with an empty object
    name: ''
  };

  constructor(private rolesService: RolesService) { }

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles() {
    this.rolesService.getAllRoles().subscribe(
      data => {
        this.roles = data;
      },
      error => {
        console.error('Error loading roles', error);
      }
    );
  }

  createRole(role: any) {
    this.rolesService.createRole(role).subscribe(
      response => {
        this.loadRoles(); // Reload the roles list after creating a new one
        this.resetForm(); // Reset the form fields after submission
      },
      error => {
        console.error('Error creating role', error);
      }
    );
  }

  deleteRole(id: number) {
    this.rolesService.deleteRole(id).subscribe(
      response => {
        this.loadRoles(); // Reload the roles list after deleting
      },
      error => {
        console.error('Error deleting role', error);
      }
    );
  }

  resetForm() {
    this.roleForm = { // Reset form fields
      name: ''
    };
  }
}
