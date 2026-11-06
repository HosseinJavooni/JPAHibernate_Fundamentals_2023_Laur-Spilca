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
                            COuRSE_ID INT,
                            CONSTRAINT FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT (ID),
                            CONSTRAINT FOREIGN KEY (COURSE_ID) REFERENCES COURSE (ID)
);
