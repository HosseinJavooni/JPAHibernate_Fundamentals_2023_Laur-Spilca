CREATE TABLE STUDENT(
                        id int AUTO_INCREMENT PRIMARY KEY,
                        name varchar(20),
                        AGE INT
);

CREATE TABLE COURSE(
                       id int AUTO_INCREMENT PRIMARY KEY,
                       name varchar(20)
);

CREATE TABLE ENROLLMENT (
                            id int AUTO_INCREMENT PRIMARY KEY ,
                            DATE_OF_ENROLLMENT DATE,
                            STUDENT_ID INT,
                            COURSE_ID INT,
                            CONSTRAINT FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT (ID),
                            CONSTRAINT FOREIGN KEY (COURSE_ID) REFERENCES COURSE (ID)
);

INSERT INTO demo.STUDENT (id, name, AGE) VALUES (4, 'Hossein', 20);
INSERT INTO demo.STUDENT (id, name, AGE) VALUES (5, 'Hossein 2', 50);
INSERT INTO demo.STUDENT (id, name, AGE) VALUES (6, 'Hossein 3', 68);

INSERT INTO demo.COURSE (id, name) VALUES (5, 'Course 1');
INSERT INTO demo.COURSE (id, name) VALUES (6, 'Course 2');
INSERT INTO demo.COURSE (id, name) VALUES (7, 'Course 3');
INSERT INTO demo.COURSE (id, name) VALUES (8, 'Course 3');


INSERT INTO demo.ENROLLMENT (id, DATE_OF_ENROLLMENT, STUDENT_ID, COURSE_ID) VALUES (1, '2026-11-07', 4, 5);
INSERT INTO demo.ENROLLMENT (id, DATE_OF_ENROLLMENT, STUDENT_ID, COURSE_ID) VALUES (2, '2026-11-07', 5, 6);
INSERT INTO demo.ENROLLMENT (id, DATE_OF_ENROLLMENT, STUDENT_ID, COURSE_ID) VALUES (3, '2026-11-07', 6, 7);
INSERT INTO demo.ENROLLMENT (id, DATE_OF_ENROLLMENT, STUDENT_ID, COURSE_ID) VALUES (4, '2026-11-07', 4, 6);
