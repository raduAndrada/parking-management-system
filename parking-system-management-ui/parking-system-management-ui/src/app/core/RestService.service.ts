import { HttpClient } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Subject, Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable()
export class RestService<T> {
  baseUrl = environment.serverPort;

  constructor(private http: HttpClient) {}

  getList(url: String): Observable<T[]> {
    return this.http.get<T[]>(this.baseUrl + url).pipe(
      tap((_) => console.log(_)),
      catchError(this.handleError<T[]>('getList'))
    );
  }

  getById(id: number, url: String): Observable<T> {
    const urlTemp = this.baseUrl + url + '/id/' + id;
    return this.http.get<T>(urlTemp).pipe(
      tap((_) => console.log(_)),
      catchError(this.handleError<T>('getById'))
    );
  }

  update(updateDetails: T, url: String): Observable<T> {
    const urlTemp = this.baseUrl + url;
    return this.http.put<T>(urlTemp, updateDetails).pipe(
      tap((_) => console.log(_)),
      catchError(this.handleError<T>('update'))
    );
  }

  create(toAdd: T, url: String): Observable<T> {
    const urlTemp = this.baseUrl + url;
    return this.http.post<T>(urlTemp, toAdd).pipe(
      tap((_) => console.log(_)),
      catchError(this.handleError<T>('create'))
    );
  }

  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T);
    };
  }
}
