import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ValidateFiscalCodeComponent } from './validate-fiscal-code/validate-fiscal-code.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { GenerateFiscalCodeComponent } from './generate-fiscal-code/generate-fiscal-code.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ValidateFiscalCodeComponent,
    GenerateFiscalCodeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
