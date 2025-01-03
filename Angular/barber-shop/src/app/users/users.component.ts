import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';
import { RolesService } from '../roles/roles.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: any[] = [];
  userForm: any = { // Initialize userForm
    userName: '',
    password: '',
    role: { id: null }
  };
  roles: any[] = []; // Assuming roles are fetched from an API

  constructor(private usersService: UsersService, private rolesService: RolesService) { }

  ngOnInit(): void {
    this.loadUsers();
    this.loadRoles(); // Load roles if needed for the dropdown
  }

  loadUsers() {
    this.usersService.getAllUsers().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.error('Error loading users', error);
      }
    );
  }

  loadRoles() {
    this.rolesService.getAllRoles().subscribe( // Ensure you have a service method for this
      data => {
        this.roles = data;
      },
      error => {
        console.error('Error loading roles', error);
      }
    );
  }

  createUser(user: any) {
    this.usersService.createUser(user).subscribe(
      response => {
        this.loadUsers();
      },
      error => {
        console.error('Error creating user', error);
      }
    );
  }

  deleteUser(id: number) {
    this.usersService.deleteUser(id).subscribe(
      response => {
        this.loadUsers();
      },
      error => {
        console.error('Error deleting user', error);
      }
    );
  }
}
