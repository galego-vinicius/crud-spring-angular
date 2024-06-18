import { CourseService } from './../services/course.service';
import { Component } from '@angular/core';
import { Course } from '../models/course';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import { Observable } from 'rxjs';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [MatTableModule, MatCardModule, MatToolbarModule, MatProgressSpinnerModule, CommonModule],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {
  courses$: Observable <Course[]>;
  displayedColumns = ['name', 'category'];

  constructor(private coursesService: CourseService){
    this.courses$ = this.coursesService.list();
  }
}
