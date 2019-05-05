import { TestBed } from '@angular/core/testing';

import { CourseService } from './course.service';
import { Course } from '../models/course.model';
import { HttpClientModule } from '@angular/common/http';

describe('CourseService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [CourseService]
    });
  });

  it('should be created', () => {
    const service: CourseService = TestBed.get(CourseService);
    expect(service).toBeTruthy();
  });

  it('should return a list', () => {
    const service: CourseService = TestBed.get(CourseService);

    service.list("title", "asc", 0).subscribe(
      data => {
        expect(data.body.length).toEqual(3);
      }
    );
  });
});
