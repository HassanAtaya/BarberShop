import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';
import { RolesService } from '../roles/roles.service';
import { allSRVService } from '../all-srvc.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: any[] = [];
  roles: any[] = [];
  languages: any[] = [];
  user: any = {};
  editingUserId: number | null = null;
  showAddUserModal: boolean = false;

  constructor(
    private userService: UsersService,
    private rolesService: RolesService,
    private allSRVService: allSRVService
  ) {

  }

  ngOnInit(): void {
    this.loadUsers();
    this.getAllRoles();
    this.getAllLanguages();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe(
      (data: any) => {
        this.users = data;
      },
      (error: any) => {
        console.error('Error loading users', error);
      }
    );
  }

  getAllRoles() {
    this.rolesService.getAllRoles().subscribe((response: any) => {
      if (response) {
        this.roles = response;
      }
    }, (error: any) => {
      console.error('Error getting roles', error);
    }
    );
  }

  getAllLanguages() {
    this.allSRVService.getAllLanguages().subscribe((response: any) => {
      if (response) {
        this.languages = response;
      }
    }, (error: any) => {
      console.error('Error getting roles', error);
    }
    );
  }

  saveUser(): void {
    if (this.editingUserId) {
      this.userService.updateUser(this.user).subscribe(
        () => {
          this.loadUsers();
          this.resetForm();
        },
        (error: any) => {
          console.error('Error updating user', error);
        }
      );
    } else {
      this.userService.createUser(this.user).subscribe(
        () => {
          this.loadUsers();
          this.resetForm();
        },
        (error: any) => {
          console.error('Error creating user', error);
        }
      );
    }
  }

  editUser(user: any): void {
    this.user = { ...user };  // Copy the user data to edit
    this.editingUserId = user.id;
    this.showAddUserModal = true;
  }

  deleteUser(id: number): void {
    const confirmDelete = confirm('Are you sure you want to delete this user?');
    if (confirmDelete) {
      this.userService.deleteUser(id).subscribe(
        () => {
          this.loadUsers();  // Refresh the list after deletion
        },
        (error: any) => {
          console.error('Error deleting user', error);
        }
      );
    }
  }

  resetForm(): void {
    this.user = {};
    this.editingUserId = null;
    this.showAddUserModal = false;
  }

  addUser() {
    this.user = {};
    this.editingUserId = null;
    this.showAddUserModal = true;
  }
}