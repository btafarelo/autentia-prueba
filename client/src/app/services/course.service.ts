import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private url = environment.url + '/cursos';

  constructor(private http: HttpClient) { }

  list(sort: string, order: string, page: number): Observable<HttpResponse<Course[]>> {
    return this.http.get<Course[]>(this.url,
      {
        observe: "response",
        params: new HttpParams()
            .set('sort', sort)
            .set('order', order)
            .set('page', page.toString())
      }
    );
  }

  insert(course: Course): Observable<Course> {
    const options = {
      headers: {'Content-Type': 'application/json'}
    };

    return this.http.post<Course>(this.url, JSON.stringify(course), options);
  }

  upload(id: number, file: File): Observable<any> {
    let formData:FormData = new FormData();
    formData.append('id', "" + id);
    formData.append('file', file, file.name);
      
    return this.http.post(this.url + "/upload", formData);
  }
}
