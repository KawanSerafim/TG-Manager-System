DELETE FROM students;
DELETE FROM student_groups;
DELETE FROM courses;

INSERT INTO courses(id, name) VALUES(1, 'Engenharia de Software');
INSERT INTO student_groups(id, course_id, year, semester) VALUES(1, 1, 2025, 2);