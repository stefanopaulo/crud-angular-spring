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

  findById(id: string) {
    return this.http.get<Course>(`${this.API}/${id}`);
  }

  save(record: Partial<Course>) {
    if(record._id) {
      return this.update(record);
    }

    return this.create(record);
  }

  private create(record: Partial<Course>) {
    return this.http.post<Course>(this.API, record)
    .pipe(first());
  }

  private update(record: Partial<Course>) {
    return this.http.put<Course>(`${this.API}/${record._id}`, record)
    .pipe(first());
  }

  remove(id: string) {
    return this.http.delete(`${this.API}/${id}`)
    .pipe(first());
  }
}
