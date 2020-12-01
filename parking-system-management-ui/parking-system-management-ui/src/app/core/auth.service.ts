import { HttpClient } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Subject, Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from './models';

@Injectable()
export class AuthService {
  baseUrl = environment.serverPort;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<User> {
    const urlTemp = this.baseUrl + '/login';
    return this.http.post<User>(urlTemp, {username: username, password: password}).pipe(
      catchError(this.handleError<User>('login'))
    );
  }

  logout(username: string): Observable<User> {
    const urlTemp = this.baseUrl + '/logout';
    return this.http.post<User>(urlTemp, {username: username}).pipe(
      catchError(this.handleError<User>('logout'))
    );
  }



  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T);
    };
  }
}
