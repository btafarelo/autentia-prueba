import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Course } from 'src/app/models/course.model';
import { Level } from 'src/app/models/level.model';
import { Teacher } from 'src/app/models/teacher.model';
import { LevelService } from 'src/app/services/level.service';
import { TeacherService } from 'src/app/services/teacher.service';
import { CourseService } from 'src/app/services/course.service';
import { MatDialogRef } from '@angular/material';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  
  private levels: Level[];

  private teachers: Teacher[];

  private active: boolean = true;

  private title: string;

  private teacher: number;

  private level: number;

  private hours: number = 0;

  private file: File;

  constructor(
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<DialogComponent>,
    private levelService: LevelService,
    private teacherService: TeacherService,
    private courseService: CourseService) {}

  ngOnInit() {
    this.levelService.list().subscribe(
      res => { this.levels = res; }
    );

    this.teacherService.list().subscribe(
      res => { this.teachers = res; }
    );
  }

  fileChange(event) {
    this.file =  event.target.files[0];
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, { duration: 2000 });
  }

  save() {
    let course: Course = {
      title: this.title,
      active: this.active,
      hours: this.hours,
      level: {
        id: this.level
      },
      teacher: {
        id: this.teacher
      }
    }

    this.courseService.insert(course).subscribe(
      data => {
        if (this.file != undefined)
          this.courseService.upload(data.id, this.file).subscribe(
            data2 => {
              this.openSnackBar("curso ha sido añadido!", "despedir"); 
              this.dialogRef.close();
            });
      },
      error => {
        this.openSnackBar("curso no ha sido añadido!", "despedir");
      }
    );
  }
}
