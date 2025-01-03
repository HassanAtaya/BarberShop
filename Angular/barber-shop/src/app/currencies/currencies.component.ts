import { Component, OnInit } from '@angular/core';
import { CurrenciesService } from './currencies.service';

@Component({
  selector: 'app-currencies',
  templateUrl: './currencies.component.html',
  styleUrls: ['./currencies.component.scss']
})
export class CurrenciesComponent implements OnInit {
  currencies: any[] = [];
  currencyForm: any = { // Initialize the form fields
    name: '',
    value: '',
    date: ''
  };

  constructor(private currenciesService: CurrenciesService) { }

  ngOnInit(): void {
    this.loadCurrencies();
  }

  loadCurrencies() {
    this.currenciesService.getAllCurrencies().subscribe(
      data => {
        this.currencies = data;
      },
      error => {
        console.error('Error loading currencies', error);
      }
    );
  }

  createCurrency(currency: any) {
    this.currenciesService.createCurrency(currency).subscribe(
      response => {
        this.loadCurrencies(); // Refresh the currency list after creation
      },
      error => {
        console.error('Error creating currency', error);
      }
    );
  }

  deleteCurrency(id: number) {
    this.currenciesService.deleteCurrency(id).subscribe(
      response => {
        this.loadCurrencies(); // Refresh the currency list after deletion
      },
      error => {
        console.error('Error deleting currency', error);
      }
    );
  }
}
