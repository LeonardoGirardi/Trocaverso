import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PrivateMessage} from '../models/private-message';

@Injectable({ providedIn: 'root' })
export class PrivateMessageService {
  private apiUrl = 'http://localhost:8080/api/private-messages';

  constructor(private http: HttpClient) {}

  getById(id: number): Observable<PrivateMessage> {
    return this.http.get<PrivateMessage>(`${this.apiUrl}/${id}`);
  }

  getConversation(params: any): Observable<PrivateMessage[]> {
    return this.http.get<PrivateMessage[]>(`${this.apiUrl}/conversation`, { params });
  }

  sendMessage(data: PrivateMessage): Observable<PrivateMessage> {
    return this.http.post<PrivateMessage>(this.apiUrl, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

