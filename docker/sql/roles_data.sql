-- Admin
INSERT INTO `lms_db`.`roles` (`name`, `description`)
VALUES ('ROLE_ADMIN', 'System Administrator - highest privileges, full system management access');

-- Student
INSERT INTO `lms_db`.`roles` (`name`, `description`)
VALUES ('ROLE_STUDENT', 'Students - can view lectures, submit assignments, and participate in courses');

-- Teacher
INSERT INTO `lms_db`.`roles` (`name`, `description`)
VALUES ('ROLE_TEACHER', 'Teachers - can create courses, post lectures, grade assignments, and manage classes');

-- Department Head
INSERT INTO `lms_db`.`roles` (`name`, `description`)
VALUES ('ROLE_DEPT_HEAD', 'Department Head - manage department, approve courses, and view department reports');

-- Academic Affairs
INSERT INTO `lms_db`.`roles` (`name`, `description`)
VALUES ('ROLE_ACAD_AFFAIRS', 'Academic Affairs - manage academic administration, schedule classes, manage semesters, and handle academic procedures');