import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Teacher } from '../models/teacher.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  private url = environment.url + '/profesores';

  constructor(private http: HttpClient) { }

  list(): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(this.url);
  }
}
