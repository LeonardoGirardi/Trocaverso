import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceOffer } from '../models/service-offer';

@Injectable({
  providedIn: 'root'
})
export class ServiceOfferService {
  private apiUrl = 'http://localhost:8080/api/service-offers';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ServiceOffer[]> {
    return this.http.get<ServiceOffer[]>(this.apiUrl);
  }

  getById(id: number): Observable<ServiceOffer> {
    return this.http.get<ServiceOffer>(`${this.apiUrl}/${id}`);
  }

  create(data: ServiceOffer): Observable<ServiceOffer> {
    return this.http.post<ServiceOffer>(this.apiUrl, data);
  }

  update(id: number, data: ServiceOffer): Observable<ServiceOffer> {
    return this.http.put<ServiceOffer>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

