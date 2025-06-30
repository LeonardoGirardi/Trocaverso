import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserReview } from '../models/user-review';

@Injectable({
  providedIn: 'root'
})
export class UserReviewService {
  private apiUrl = 'http://localhost:8080/api/user-reviews';

  constructor(private http: HttpClient) {}

  getAll(): Observable<UserReview[]> {
    return this.http.get<UserReview[]>(this.apiUrl);
  }

  getById(id: number): Observable<UserReview> {
    return this.http.get<UserReview>(`${this.apiUrl}/${id}`);
  }

  getByUser(userId: number): Observable<UserReview[]> {
    return this.http.get<UserReview[]>(`${this.apiUrl}/by-user/${userId}`);
  }

  create(data: UserReview): Observable<UserReview> {
    return this.http.post<UserReview>(this.apiUrl, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
