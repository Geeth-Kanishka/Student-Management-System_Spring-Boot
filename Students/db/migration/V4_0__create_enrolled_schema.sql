CREATE TABLE IF NOT EXISTS students_enrolled(
    course_id bigint NOT NULL,
    student_id bigint NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id), 
    FOREIGN KEY (student_id) REFERENCES students(id),
    UNIQUE (course_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

