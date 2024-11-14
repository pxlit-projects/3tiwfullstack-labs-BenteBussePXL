import { Component, EventEmitter, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Customer } from '../../../shared/models/customer.model';
import { Router } from '@angular/router';
import { CustomerService } from '../../../shared/services/customer.service';

@Component({
  selector: 'app-add-customer',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'] 
})
export class AddCustomerComponent {
  customerService: CustomerService = inject(CustomerService);
  router: Router = inject(Router);  
  fb: FormBuilder = inject(FormBuilder);
  customerForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    avatar: ['default.png', [Validators.required]],
    city: ['', Validators.required],
    address: ['', Validators.required],
    country: ['', Validators.required],
    vat: ['', [Validators.required, Validators.min(0)]]
  });

  onSubmit() {
    const newCustomer: Customer = {
      ...this.customerForm.value
    };
    this.customerService.addCustomer(newCustomer).subscribe(customer => {
      this.customerForm.reset();
      this.router.navigate(['/customers']);
    });
  }

}
