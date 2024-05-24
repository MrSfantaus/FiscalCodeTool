import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidateResponse } from '../common/api-response.model';
import { ApiService } from '../common/api.service';

@Component({
  selector: 'app-validate-fiscal-code',
  templateUrl: './validate-fiscal-code.component.html',
  styleUrls: ['./validate-fiscal-code.component.css']
})
export class ValidateFiscalCodeComponent {
  validateForm: FormGroup;
  response: ValidateResponse | null = null;
  errorMessage: string | null = null;

  constructor(private service: ApiService, private formBuilder: FormBuilder) {
    this.validateForm = this.formBuilder.group({
      fiscalCode: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.validateForm.valid) {
      const requestBody = { fiscalCode: this.validateForm.value.fiscalCode };
      this.service.validateFiscalCode(requestBody).subscribe({
        next: (response: ValidateResponse | null) => {
          this.response = response;
          this.errorMessage = null;
        },
        error: (error: { message: string; }) => {
          this.errorMessage =  error.message;
          console.error(error);
        }
      });
    }
  }
}
