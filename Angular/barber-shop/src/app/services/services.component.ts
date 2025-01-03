import { Component, OnInit } from '@angular/core';
import { ServicesService } from './services.service';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss']
})
export class ServicesComponent implements OnInit {
  services: any[] = [];
  serviceForm: any = { // Initialize the form fields
    name: '',
    price: '',
    durationMinutes: ''
  };

  constructor(private servicesService: ServicesService) { }

  ngOnInit(): void {
    this.loadServices();
  }

  loadServices() {
    this.servicesService.getAllServices().subscribe(
      data => {
        this.services = data;
      },
      error => {
        console.error('Error loading services', error);
      }
    );
  }

  createService(service: any) {
    this.servicesService.createService(service).subscribe(
      response => {
        this.loadServices(); // Refresh the services list after creating
        this.resetForm(); // Reset form fields after submission
      },
      error => {
        console.error('Error creating service', error);
      }
    );
  }

  deleteService(id: number) {
    this.servicesService.deleteService(id).subscribe(
      response => {
        this.loadServices(); // Refresh the services list after deletion
      },
      error => {
        console.error('Error deleting service', error);
      }
    );
  }

  resetForm() {
    this.serviceForm = { // Reset the form fields
      name: '',
      price: '',
      durationMinutes: ''
    };
  }
}
