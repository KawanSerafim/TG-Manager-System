INSERT INTO courses (id, name) VALUES (1, 'Engenharia de Software')
ON DUPLICATE KEY UPDATE name = VALUES(name);

INSERT INTO student_groups (id, course_id, year, semester) VALUES (1, 1, 2025, 2)
ON DUPLICATE KEY UPDATE course_id = VALUES(course_id);