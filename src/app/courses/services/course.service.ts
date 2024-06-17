import { Injectable } from '@angular/core';
import { Course } from '../models/course';
import { HttpClient } from '@angular/common/http'
import { first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  // AJAX

  private readonly API = '/assets/courses.json';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get <Course[]> (this.API) //Observable & Generics
    .pipe(
      first(), //Como não é uma stream, ele só pega a primeira resposta do servidor
      // e depois fecha a conexao.
      tap(courses => console.log(courses))
    ); // Pipe e tap -> Parecido com Log lá no Java, para debugar.
  }
}
