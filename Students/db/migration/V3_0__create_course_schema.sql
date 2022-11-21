CREATE TABLE IF NOT EXISTS courses (

    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
	lecturer_id bigint ,
	FOREIGN KEY (lecturer_id) REFERENCES lecturers(id),
	UNIQUE (lecturer_id)
	

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

