import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ItemTradeProposal} from '../models/item-trade-proposal';

@Injectable({ providedIn: 'root' })
export class ItemTradeProposalService {
  private apiUrl = 'http://localhost:8080/api/item-trade-proposals';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ItemTradeProposal[]> {
    return this.http.get<ItemTradeProposal[]>(this.apiUrl);
  }

  getById(id: number): Observable<ItemTradeProposal> {
    return this.http.get<ItemTradeProposal>(`${this.apiUrl}/${id}`);
  }

  create(data: ItemTradeProposal): Observable<ItemTradeProposal> {
    return this.http.post<ItemTradeProposal>(this.apiUrl, data);
  }

  updateStatus(id: number, data: any): Observable<ItemTradeProposal> {
    return this.http.put<ItemTradeProposal>(`${this.apiUrl}/${id}/status`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
