import { Component, Input } from '@angular/core';
import { Course } from '../models/course';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { CategoryPipe } from '../../shared/pipes/category.pipe';

@Component({
  selector: 'app-courses-list',
  standalone: true,
  imports: [MatTableModule, MatIconModule, CategoryPipe],
  templateUrl: './courses-list.component.html',
  styleUrl: './courses-list.component.scss'
})
export class CoursesListComponent {

  @Input() courses: Course[] = [];

  readonly displayedColumns = ['_id', 'name', 'category', 'actions'];


  constructor(private router: Router, private route: ActivatedRoute){
  }

  onAdd(){
    this.router.navigate(['new'], {relativeTo: this.route})
  }
}
