import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Level } from '../models/level.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LevelService {

  url = environment.url + '/niveles';

  constructor(private http: HttpClient) { }

  list(): Observable<Level[]> {
    return this.http.get<Level[]>(this.url);
  }
}
