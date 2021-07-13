CREATE table if not exists  student2 (
    student_id int primary key not null,
    first_name varchar (100) not null,
    email varchar (100) not null unique,
    dob date not null,
    age int not null
    );