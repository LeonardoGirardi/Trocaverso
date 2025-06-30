import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceTradeProposal } from '../models/service-trade-proposal';

@Injectable({
  providedIn: 'root'
})
export class ServiceTradeProposalService {
  private apiUrl = 'http://localhost:8080/api/service-trade-proposals';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ServiceTradeProposal[]> {
    return this.http.get<ServiceTradeProposal[]>(this.apiUrl);
  }

  getById(id: number): Observable<ServiceTradeProposal> {
    return this.http.get<ServiceTradeProposal>(`${this.apiUrl}/${id}`);
  }

  create(data: ServiceTradeProposal): Observable<ServiceTradeProposal> {
    return this.http.post<ServiceTradeProposal>(this.apiUrl, data);
  }

  updateStatus(id: number, data: any): Observable<ServiceTradeProposal> {
    return this.http.put<ServiceTradeProposal>(`${this.apiUrl}/${id}/status`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

