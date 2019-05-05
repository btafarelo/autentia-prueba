import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Observable, merge, BehaviorSubject } from 'rxjs';
import { tap, finalize } from 'rxjs/operators';
import { DataSource, CollectionViewer } from '@angular/cdk/collections';
import { MatSort, MatPaginator, MatDialog } from '@angular/material';

import { environment } from 'src/environments/environment';

import { CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements AfterViewInit, OnInit {

  displayedColumns = ['title', 'teacher', 'level', 'hours', 'fileName'];

  url = environment.url + "/cursos/download/";

  dataSource: CourseDataSource;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private dialog: MatDialog,
    private courseService: CourseService) { }

  ngOnInit() {
    this.dataSource = new CourseDataSource(this.courseService, this.paginator);
  }

  ngAfterViewInit() {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
          tap(() => this.loadPage())
      ).subscribe();

    this.loadPage();
  }

  loadPage() {
    var sort = this.sort.active;
    var order = this.sort.direction;
    var page = this.paginator.pageIndex

    this.dataSource.loadCourses(sort, order, page);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '350px'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadPage();
    });
  }
}

export class CourseDataSource extends DataSource<any> {

  private coursesSubject = new BehaviorSubject<Course[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  constructor(
    private courseService: CourseService, 
    private paginator: MatPaginator) {
      super();
  }

  connect(collectionViewer: CollectionViewer): Observable<Course[]> {
    return this.coursesSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.coursesSubject.complete();
    this.loadingSubject.complete();    
  }

  loadCourses(sort: string, order: string, page: number) {
    this.loadingSubject.next(true);

    this.courseService.list(sort, order, page)
    .pipe(
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(res => {
        var total = parseInt(res.headers.get("X-Total-Count"));

        this.paginator.length = total;
        this.coursesSubject.next(res.body);
    });
  }
}