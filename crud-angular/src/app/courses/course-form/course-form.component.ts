import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms'
import { MatInputModule } from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import { CourseService } from '../services/course.service';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';

@Component({
  selector: 'app-course-form',
  standalone: true,
  imports: [MatFormFieldModule, ReactiveFormsModule, MatInputModule, MatCardModule,
     MatToolbarModule, MatButtonModule, MatSelectModule, MatSnackBarModule],
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private service: CourseService,
    private _snackBar: MatSnackBar){
    this.form = this.formBuilder.group({
      name: [null],
      category: [null]
    })
  }

  onSubmit(){
    this.service.save(this.form.value).subscribe(result => console.log(result),
     error =>  this.onError())
  }

  onCancel(){

  }

  private onError(){
    this._snackBar.open('Erro ao salvar curso', '', { duration: 5000 });
  }
}
