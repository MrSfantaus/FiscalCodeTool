import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenerateFiscalCodeComponent } from './generate-fiscal-code/generate-fiscal-code.component';
import { ValidateFiscalCodeComponent } from './validate-fiscal-code/validate-fiscal-code.component';

const routes: Routes = [
  { path: 'generate-fiscal-code', component: GenerateFiscalCodeComponent },
  { path: 'validate-fiscal-code', component: ValidateFiscalCodeComponent },
  { path: '', redirectTo: '/generate-fiscal-code', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
