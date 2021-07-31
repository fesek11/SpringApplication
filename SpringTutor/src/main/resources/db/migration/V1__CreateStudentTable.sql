CREATE table if not exists  student2 (
    student_id int primary key not null,
    first_name varchar (100) not null,
    email varchar (100) not null unique,
    dob date not null,
    age int not null
    );

INSERT INTO course
(id, name, description, department)
VALUES (uuid_generate_v4(), 'Database Fundamentals', 'TODO', 'Computer Science');
