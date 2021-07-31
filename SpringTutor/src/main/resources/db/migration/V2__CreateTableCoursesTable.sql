CREATE TABLE IF NOT EXISTS course(
    course_id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR (255) NOT NULL UNIQUE,
    description TEXT NOT NULL UNIQUE,
    department VARCHAR (255),
    teacher_name VARCHAR (100)
);

CREATE TABLE IF NOT EXISTS student_course(
    student_id INTEGER NOT NULL REFERENCES student2 (student_id),
    course_id INTEGER NOT NULL REFERENCES course (course_id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    grade INTEGER CHECK (grade >= 0 AND grade <= 100),
    UNIQUE (student_id, course_id)
);
ALTER TABLE distributors ALTER COLUMN street DROP NOT NULL;

SELECT * from student2
inner join course USING (course_id)
left join student_course USING (student_id);
