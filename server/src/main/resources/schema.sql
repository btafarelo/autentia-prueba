create table levels (
    id int identity not null,
    value varchar(10) not null,
    primary key(id)
);

create table teachers (
    id int identity not null,
    name varchar(50) not null,
    surname varchar(50) not null,
    primary key(id),
    unique (name,surname)
);

create table courses (
    id int identity not null,
    title varchar(50) not null,
    hours int not null,
    active bool not null,
    level int not null,
    teacher int not null,
    file blob,
    contentType varchar(50),
    fileName varchar(255),
    primary key (id),
    unique (title),
    foreign key (level) references levels(id),
    foreign key (teacher) references teachers(id)
);