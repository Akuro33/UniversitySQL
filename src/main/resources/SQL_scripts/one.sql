CREATE DATABASE IF NOT EXISTS `UniversitySQL`;

USE `UniversitySQL`;



DROP TABLE IF EXISTS student;

CREATE TABLE `student` (
                           id_student int not null auto_increment,
                           first_name varchar(40) default null,
                           last_name varchar(40) default null,
                           email VARCHAR(255) NOT NULL,
                           subject VARCHAR(32),
                           age int (10),
                           primary key (`id_student`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS teacher;

CREATE TABLE teacher (
                         id_teacher int not null auto_increment,
                         first_name varchar(40) default null,
                         last_name varchar(40) default null,
                         age int not null,
                         email varchar(40) default null,
                         subject varchar(40) default null,
                         primary key (`id_teacher`)

) ENGINE=InnoDB  DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS teacher_student;

CREATE TABLE teacher_student (
                                   id_teacher int(11) NOT NULL,
                                   id_student int(11) NOT NULL,

                                   PRIMARY KEY (id_teacher, id_student),

                                   KEY `FK_STUDENT_idx` (id_student),

                                   CONSTRAINT `FK_TEACHER` FOREIGN KEY (id_teacher)
                                       REFERENCES teacher (id_teacher)
                                       ON DELETE NO ACTION ON UPDATE NO ACTION,

                                   CONSTRAINT `FK_STUDENT` FOREIGN KEY (id_student)
                                       REFERENCES student (id_student)
                                       ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB  DEFAULT CHARSET= utf8mb4;