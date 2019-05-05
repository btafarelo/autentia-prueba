insert into levels (value) values ('avanzado');
insert into levels (value) values ('básico');
insert into levels (value) values ('intermedio');

insert into teachers (name,surname) values ('Bruno','Tafarelo');
insert into teachers (name,surname) values ('Simone','Domingues');

insert into courses (title, hours, active, level, teacher) values ('Curso 1111', 12, true, 1, 1);
insert into courses (title, hours, active, level, teacher) values ('Curso 2222', 24, true, 2, 2);
insert into courses (title, hours, active, level, teacher) values ('Curso 3333', 36, true, 3, 2);
insert into courses (title, hours, active, level, teacher) values ('Curso 4444', 48, false, 2, 1);