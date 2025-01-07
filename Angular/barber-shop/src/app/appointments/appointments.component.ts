import { Component, OnInit } from '@angular/core';
import { AppointmentsService } from './appointments.service';
import { UsersService } from '../users/users.service';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.scss']
})
export class AppointmentsComponent implements OnInit {
  appointments: any[] = [];
  appointmentForm: any = { // Define the appointmentForm property
    date: '',
    timeFrom: '',
    timeTo: '',
    user: { id: null },
    paid: false
  };
  users: any[] = []; // Assuming you have a list of users for the dropdown

  constructor(private appointmentsService: AppointmentsService,
    private usersService: UsersService
  ) { }

  ngOnInit(): void {
    this.loadAppointments();
    this.loadUsers(); // You might need a method to load users
  }

  loadAppointments() {
    this.appointmentsService.getAllAppointments().subscribe(
      data => {
        this.appointments = data;
      },
      error => {
        console.error('Error loading appointments', error);
      }
    );
  }

  loadUsers() {
    // Assuming there's a service method to load users
    this.usersService.getUsers().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.error('Error loading users', error);
      }
    );
  }

  createAppointment(appointment: any) {
    this.appointmentsService.createAppointment(appointment).subscribe(
      response => {
        this.loadAppointments();
      },
      error => {
        console.error('Error creating appointment', error);
      }
    );
  }

  deleteAppointment(id: number) {
    this.appointmentsService.deleteAppointment(id).subscribe(
      response => {
        this.loadAppointments();
      },
      error => {
        console.error('Error deleting appointment', error);
      }
    );
  }
}
