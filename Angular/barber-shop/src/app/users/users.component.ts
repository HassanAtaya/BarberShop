import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: any[] = [];
  user: any = {};  // Empty user object for adding/editing
  editingUserId: number | null = null;
  showAddUserModal: boolean = false;

  constructor(private userService: UsersService) { }

  ngOnInit(): void {
    this.loadUsers();
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

  saveUser(): void {
    if (this.editingUserId) {
      this.userService.updateUser(this.user).subscribe(
        () => {
          this.loadUsers();  // Refresh the list of users after updating
          this.resetForm();
        },
        (error: any) => {
          console.error('Error updating user', error);
        }
      );
    } else {
      this.userService.createUser(this.user).subscribe(
        () => {
          this.loadUsers();  // Refresh the list of users after adding
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
    this.user = {};  // Reset the user form
    this.editingUserId = null;
    this.showAddUserModal = false;
  }
}