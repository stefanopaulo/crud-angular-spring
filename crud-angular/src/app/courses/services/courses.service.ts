import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';

import { Course } from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = 'api/courses';

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Course[]>(this.API)
    .pipe(
      first(),
      tap(courses => console.log(courses))
    );
  }

  save(record: Partial<Course>) {
    return this.http.post<Course>(this.API, record)
    .pipe(first());
  }
}
