import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../common/api.service';
import { BelfioreDTO, BelfioreResponse, GenerateResponse } from '../common/api-response.model';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { DateFnsAdapter } from '@angular/material-date-fns-adapter';
import { Observable, map, startWith } from 'rxjs';

export const MY_FORMATS = {
  parse: {
    dateInput: 'dd/MM/yyyy',
  },
  display: {
    dateInput: 'dd/MM/yyyy',
    monthYearLabel: 'MMM yyyy',
    dateA11yLabel: 'dd/MM/yyyy',
    monthYearA11yLabel: 'MMMM yyyy',
  },
};

@Component({
  selector: 'app-generate-fiscal-code',
  templateUrl: './generate-fiscal-code.component.html',
  styleUrls: ['./generate-fiscal-code.component.css'],
  providers: [
    { provide: DateAdapter, useClass: DateFnsAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ]
})
export class GenerateFiscalCodeComponent implements OnInit {

  generateForm: FormGroup = new FormGroup({});
  responseGenerate: GenerateResponse | null = null;
  responseBelfiore: BelfioreResponse | null = null;
  belfioreCodes: BelfioreDTO[] = [];
  provinceCodes: string[] = [];
  municipalityCodes: string[] = [];
  errorMessage: string | null = null;
  filteredProvinces: Observable<string[]> = new Observable<string[]>();
  filteredMunicipalities: Observable<string[]> = new Observable<string[]>();

  constructor(private service: ApiService, private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.generateForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      birthDate: ['', Validators.required],
      isItalian: [false, Validators.required],
      province: ['', Validators.required],
      municipality: ['', Validators.required],
      gender: ['', Validators.required]
    });

    this.getFilteredProvince();
    this.getFilteredMunicipalities();
  }

  loadBelfioreCodes(): void {
    this.service.getAllBelfioreCodes().subscribe({
      next: (response: BelfioreResponse) => {
        this.belfioreCodes = response.data.belfioreCodes;
      },
      error: (error: any) => {
        console.error('Error: ', error);
      }
    });
  }

  onIsItalianChange(): void {
    const italian = this.generateForm.value.isItalian;
    this.belfioreCodes = []; 
    this.provinceCodes = []; 
    this.municipalityCodes = []; 
    this.service.getAllBelfioreCodesByIsItalianMunicipality(italian).subscribe({
      next: (response: BelfioreResponse) => {
        this.belfioreCodes.push(...response.data.belfioreCodes);
        this.getProvinceCodes();
      },
      error: (error: any) => {
        console.error('Error: ', error);
      }
    });
  }

  onProvinceChange() {
    const province = this.generateForm.value.province;
    this.belfioreCodes = []; 
    this.provinceCodes = [];
    this.municipalityCodes = []; 
    this.service.getAllBelfioreCodesByProvince(province).subscribe({
      next: (response: BelfioreResponse) => {
        this.belfioreCodes.push(...response.data.belfioreCodes);
        this.getMunicipalityCodes();
      },
      error: (error: any) => {
        console.error('Error: ', error);
      }
    });
  }

  onSubmit(): void {
    if (this.generateForm.valid) {
      const requestBody = this.generateForm.value;
      requestBody.birthDate = this.formatDate(requestBody.birthDate);
      this.service.generateFiscalCode(requestBody).subscribe({
        next: (response: GenerateResponse | null) => {
          this.responseGenerate = response;
          this.errorMessage = null;
        },
        error: (error: any) => {
          this.errorMessage = error.message;
          console.error(error);
        }
      });
    }
  }

  onReset() {
    this.generateForm.reset();
  }

  getProvinceCodes(): void {
    const uniqueProvinces = new Set<string>(); 
    this.belfioreCodes.forEach((code) => {
      uniqueProvinces.add(code.province);
    });
    this.provinceCodes = Array.from(uniqueProvinces).sort((n1,n2) => {
        if (n1 > n2) { return 1; }  
        if (n1 < n2) { return -1; }  
        this.getFilteredProvince();
        return 0;
    });
  }

  getMunicipalityCodes(): void {
    const uniqueMunicipalities = new Set<string>(); 
    this.belfioreCodes.forEach((code) => {
      uniqueMunicipalities.add(code.municipality);
    });
    this.municipalityCodes = Array.from(uniqueMunicipalities).sort((n1,n2) => {
        if (n1 > n2) { return 1; }  
        if (n1 < n2) { return -1; }  
        this.getFilteredMunicipalities();
        return 0;
    });
  }

  dateValidator(control: any): { [key: string]: any } | null {
    const regex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d\d$/;
    if (control.value && !regex.test(control.value)) {
      return { 'dateInvalid': true };
    }
    return null;
  }

  onDateInput(event: any) {
    const input = event.target.value;
    const regex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d\d$/;
    if (!regex.test(input)) {
      this.generateForm.get('birthDate')?.setErrors({ dateInvalid: true });
    } else {
      this.generateForm.get('birthDate')?.setErrors(null);
    }
  }

  formatDate(dateString: string): string {
    const [day, month, year] = dateString.split('/');
    return `${day}/${month}/${year}`;
  }

  getFilteredProvince(): void {
    this.filteredProvinces = this.generateForm.get('province')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.provinceCodes))
    );
  }

  getFilteredMunicipalities(): void {
    this.filteredMunicipalities = this.generateForm.get('municipality')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.municipalityCodes))
    );
  }

  private _filter(value: string, options: string[]): string[] {
    const filterValue = value.toUpperCase();
    return options.filter(option => option.toUpperCase().includes(filterValue));
  }
}
