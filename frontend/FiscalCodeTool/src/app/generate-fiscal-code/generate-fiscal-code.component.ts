import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../common/api.service';
import { BelfioreDTO, BelfioreResponse, GenerateResponse } from '../common/api-response.model';

@Component({
  selector: 'app-generate-fiscal-code',
  templateUrl: './generate-fiscal-code.component.html',
  styleUrls: ['./generate-fiscal-code.component.css']
})
export class GenerateFiscalCodeComponent implements OnInit{

  generateForm: FormGroup;
  responseGenerate: GenerateResponse | null = null;
  responseBelfiore: BelfioreResponse | null = null;
  belfioreCodes: BelfioreDTO[] = [];
  provinceCodes: string[] = [];
  municipalityCodes: string[] = [];
  errorMessage: string | null = null;
  
  constructor(private service: ApiService, private formBuilder: FormBuilder) {
    this.generateForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      birthDate: ['', Validators.required],
      isItalian: ['', Validators.required],
      province: ['', Validators.required],
      municipality: ['', Validators.required],
      gender: ['', Validators.required]
    });
  }

  ngOnInit() {
    //this.loadBelfioreCodes();
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
    console.log(italian);
    this.belfioreCodes = []; // Pulizia dell'array dei codici Belfiore per evitare duplicati
    this.provinceCodes = []; // Pulizia dell'array delle province
    this.municipalityCodes = []; // Pulizia dell'array dei comuni
    this.service.getAllBelfioreCodesByIsItalianMunicipality(italian).subscribe({
      next: (response: BelfioreResponse) => {
        console.log(response);
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
    console.log(province);
    this.belfioreCodes = []; // Pulizia dell'array dei codici Belfiore per evitare duplicati
    this.provinceCodes = []; // Pulizia dell'array delle province
    this.municipalityCodes = []; // Pulizia dell'array dei comuni
    this.service.getAllBelfioreCodesByProvince(province).subscribe({
      next: (response: BelfioreResponse) => {
        console.log(response);
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
    this.provinceCodes = Array.from(uniqueProvinces);
  }

  getMunicipalityCodes(): void {
    const uniqueMunicipalities = new Set<string>(); 
    this.belfioreCodes.forEach((code) => {
      uniqueMunicipalities.add(code.municipality);
    });
    this.municipalityCodes = Array.from(uniqueMunicipalities);
  }
}
