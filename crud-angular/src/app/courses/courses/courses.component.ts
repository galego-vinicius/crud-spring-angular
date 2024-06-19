import { CourseService } from './../services/course.service';
import { Component } from '@angular/core';
import { Course } from '../models/course';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import { Observable, catchError, of } from 'rxjs';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';
import {MatIconModule} from '@angular/material/icon';
import { CategoryPipe } from '../../shared/pipes/category.pipe';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [MatTableModule, MatCardModule, MatToolbarModule, MatProgressSpinnerModule,
     CommonModule, MatIconModule, CategoryPipe],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {
  courses$: Observable <Course[]>;
  displayedColumns = ['_id', 'name', 'category'];

  constructor(private coursesService: CourseService, public dialog: MatDialog){
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar cursos!')
        return of([]) // Cria um observable com array vazio.
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }
}
