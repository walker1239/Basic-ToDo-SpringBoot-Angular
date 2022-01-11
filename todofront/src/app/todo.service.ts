import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { todomodel } from './todomodel';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  apiURL = 'http://localhost:9095';
  
  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    })
  }  

  getTodoList(): Observable<todomodel[]> {
    return this.http.get<todomodel[]>(this.apiURL + '/things', this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getTodo(id: number): Observable<todomodel> {
    return this.http.get<todomodel>(this.apiURL + '/things/' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  createTodo(mytodo: todomodel): Observable<todomodel> {
    console.log('trying to save', JSON.stringify(mytodo));
    return this.http.post<todomodel>(this.apiURL + '/thing', JSON.stringify(mytodo), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }  

  updateTodo(id: number, mytodo: todomodel): Observable<todomodel> {
    console.log('trying to update',id, mytodo);
    return this.http.put<todomodel>(this.apiURL + '/things/' + id, JSON.stringify(mytodo), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  deleteTodo(id: number){
    return this.http.delete<todomodel>(this.apiURL + '/things/' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  handleError(error: { error: { message: string; }; status: any; message: any; }) {
     let errorMessage = '';
     if(error.error instanceof ErrorEvent) {
       errorMessage = error.error.message;
     } else {
       errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
     }
     window.alert(errorMessage);
     return throwError(errorMessage);
  }

}
