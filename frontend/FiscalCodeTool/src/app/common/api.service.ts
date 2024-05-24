import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BelfioreResponse, GenerateResponse, ValidateResponse } from '../common/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = '/api';

  constructor(private http: HttpClient) {}

  public generateFiscalCode(requestBody: any): Observable<GenerateResponse> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<GenerateResponse>(`${this.baseUrl}/generate`, requestBody, { headers: headers });
  }

  public validateFiscalCode(requestBody: any): Observable<ValidateResponse> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<ValidateResponse>(`${this.baseUrl}/validate`, requestBody, { headers: headers });
  }

  getAllBelfioreCodes(): Observable<BelfioreResponse> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get<BelfioreResponse>(`${this.baseUrl}/getAllBelfioreCodes`, { headers: headers });
  }

  getAllBelfioreCodesByIsItalianMunicipality(isItalian: boolean): Observable<BelfioreResponse> {
    console.log(isItalian);
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<BelfioreResponse>(`${this.baseUrl}/getBelfioreCodesByIsItalianMunicipality`, { italianMunicipality: isItalian }, { headers: headers });
  }

  getAllBelfioreCodesByProvince(province: string): Observable<BelfioreResponse> {
    console.log(province);
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<BelfioreResponse>(`${this.baseUrl}/getBelfioreCodesByProvince`, { province: province }, { headers: headers });
  }
}
