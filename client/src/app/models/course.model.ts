import { Level } from './level.model';
import { Teacher } from './teacher.model';

export class Course {
  id?: number;
  title: string;
  hours: number;
  active: boolean;
  level: Level;
  teacher: Teacher;
  fileName?: string;
}