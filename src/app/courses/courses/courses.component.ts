import { CourseService } from './../services/course.service';
import { Component } from '@angular/core';
import { Course } from '../models/course';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [MatTableModule, MatCardModule, MatToolbarModule],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {
  courses: Course[] = [];
  displayedColumns = ['name', 'category'];
  // coursesService: CourseService;

  constructor(private coursesService: CourseService){
    // this.coursesService = new CourseService();
    this.courses = this.coursesService.list();
  }

}
