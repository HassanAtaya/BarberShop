import { Component, OnInit } from '@angular/core';
import { UsersService } from './users.service';
import { RolesService } from '../roles/roles.service';
import { allSRVService } from '../all-srvc.service';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

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
    private toastrService: ToastrService,
    private translate: TranslateService,
    public allSRVService: allSRVService
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
        console.error(this.translate.instant('ERROR_LOADING_USERS'), error);
      }
    );
  }

  getAllRoles() {
    this.rolesService.getAllRoles().subscribe((response: any) => {
      if (response) {
        this.roles = response;
      }
    }, (error: any) => {
      console.error(this.translate.instant('ERROR_GETTING_ROLES'), error);
    });
  }

  getAllLanguages() {
    this.allSRVService.getAllLanguages().subscribe((response: any) => {
      if (response) {
        this.languages = response;
      }
    }, (error: any) => {
      console.error(this.translate.instant('ERROR_GETTING_LANGUAGES'), error);
    });
  }

  saveUser(): void {
    if (!this.editingUserId) {
      this.user.id = 0;
    }

    this.userService.saveUser(this.user).subscribe((response: any) => {
      this.loadUsers();
      this.resetForm();
    },
      (error: any) => {
        this.toastrService.error(error?.error);
      }
    );
  }

  editUser(user: any): void {
    this.user = { ...user };
    this.editingUserId = user.id;
    this.showAddUserModal = true;
  }

  deleteUser(id: number): void {
    const confirmDelete = confirm(this.translate.instant('CONFIRM_DELETE_USER'));
    if (confirmDelete) {
      this.userService.deleteUser(id).subscribe(
        () => {
          this.loadUsers();
        },
        (error: any) => {
          console.error(this.translate.instant('ERROR_DELETING_USER'), error);
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
