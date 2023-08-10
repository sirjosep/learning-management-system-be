CREATE TABLE t_role(
	id serial,
	role_code varchar(5) NOT NULL,
	role_name varchar(12) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_role ADD CONSTRAINT role_pk
	PRIMARY KEY(id);
	
CREATE TABLE t_file(
	id serial,
	files text NOT NULL,
	file_format varchar(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);
	
CREATE TABLE t_profile (
	id serial, 
	profile_name varchar(50),
	profile_phone varchar(13),
	profile_address text,
	file_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_profile ADD CONSTRAINT profile_pk
	PRIMARY KEY(id);

ALTER TABLE t_profile ADD CONSTRAINT profile_file_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);
	
CREATE TABLE t_user (
	id serial,
	email varchar(50) NOT NULL,
	password text NOT NULL,
	role_id int NOT NULL,
	profile_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_user ADD CONSTRAINT user_pk
	PRIMARY KEY(id);

ALTER TABLE t_user ADD CONSTRAINT user_role_fk
	FOREIGN KEY(role_id)
	REFERENCES t_role(id);
	
ALTER TABLE t_user ADD CONSTRAINT user_profile_fk
	FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);

ALTER TABLE t_user ADD UNIQUE(email);
	
CREATE TABLE t_classroom(
	id serial,
	class_code varchar(5) NOT NULL,
	class_name varchar(50) NOT NULL,
	teacher_id int NOT NULL,
	class_thumb_file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_classroom ADD CONSTRAINT classroom_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_classroom ADD CONSTRAINT classroom_teacher_fk
	FOREIGN KEY(teacher_id)
	REFERENCES t_user(id);

ALTER TABLE t_classroom ADD CONSTRAINT classroom_thumb_fk
	FOREIGN KEY(class_thumb_file_id)
	REFERENCES t_file(id);

ALTER TABLE t_classroom ADD UNIQUE(class_code);

CREATE TABLE t_enroll_class(
	id serial,
	student_id int NOT NULL,
	class_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_enroll_class ADD CONSTRAINT enroll_class_pk
	PRIMARY KEY(id);

ALTER TABLE t_enroll_class ADD CONSTRAINT enroll_class_student_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);

ALTER TABLE t_enroll_class ADD CONSTRAINT enroll_class_fk
	FOREIGN KEY(class_id)
	REFERENCES t_classroom(id);

CREATE TABLE t_learning(
	id serial,
	learning_title varchar(50) NOT NULL,
	learning_date_start timestamp NOT NULL,
	learning_date_end timestamp NOT NULL,
	class_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_learning ADD CONSTRAINT learning_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_learning ADD CONSTRAINT learning_class_fk
	FOREIGN KEY(class_id)
	REFERENCES t_classroom(id);

CREATE TABLE t_attendance(
	id serial,
	learning_id int NOT NULL,
	student_id int NOT NULL,
	attendance_time timestamp NOT NULL,
	is_approved boolean NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_attendance ADD CONSTRAINT attendance_pk
	PRIMARY KEY(id);

ALTER TABLE t_attendance ADD CONSTRAINT learning_attendance_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

ALTER TABLE t_attendance ADD CONSTRAINT learning_attendance_student_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);
	
CREATE TABLE t_learning_material(
	id serial,
	learning_material_title varchar(50) NOT NULL,
	learning_material_body text,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_learning_material ADD CONSTRAINT learning_material_pk
	PRIMARY KEY(id);

ALTER TABLE t_learning_material ADD CONSTRAINT learning_material_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

CREATE TABLE t_learning_material_file(
	id serial,
	learning_material_id int NOT NULL,
	file_material_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_learning_material_file ADD CONSTRAINT learning_material_file_pk
	PRIMARY KEY(id);

ALTER TABLE t_learning_material_file ADD CONSTRAINT learning_material_file_fk
	FOREIGN KEY(learning_material_id)
	REFERENCES t_learning_material(id);

ALTER TABLE t_learning_material_file ADD CONSTRAINT learning_file_material_fk
	FOREIGN KEY(file_material_id)
	REFERENCES t_file(id);

CREATE TABLE t_learning_task(
	id serial,
	learning_task_title varchar(50) NOT NULL,
	learning_task_desc text,
	learning_task_start timestamp NOT NULL,
	learning_task_end timestamp NOT NULL,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_learning_task ADD CONSTRAINT learning_task_pk
	PRIMARY KEY(id);

ALTER TABLE t_learning_task ADD CONSTRAINT learning_task_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

CREATE TABLE t_question(
	id serial,
	question_code varchar(5) NOT NULL,
	question_body text,
	learning_task_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question ADD CONSTRAINT question_pk
	PRIMARY KEY(id);

ALTER TABLE t_question ADD CONSTRAINT question_task_fk
	FOREIGN KEY(learning_task_id)
	REFERENCES t_learning_task(id);

ALTER TABLE t_question ADD UNIQUE (question_code);

CREATE TABLE t_question_file(
	id serial,
	question_id int NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_file ADD CONSTRAINT learning_task_file_pk
	PRIMARY KEY(id);

ALTER TABLE t_question_file ADD CONSTRAINT learning_task_file_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

ALTER TABLE t_question_file ADD CONSTRAINT learning_file_task_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);

CREATE TABLE t_question_options(
	id serial,
	options_label text NOT NULL,
	is_correct boolean NOT NULL,
	question_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_options ADD CONSTRAINT question_options_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_question_options ADD CONSTRAINT question_options_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);
	
CREATE TABLE t_forum(
	id serial,
	user_id int NOT NULL,
	forum_title text NOT NULL,
	forum_body text,
	learning_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_forum ADD CONSTRAINT forum_pk
	PRIMARY KEY(id);

ALTER TABLE t_forum ADD CONSTRAINT forum_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);
	
ALTER TABLE t_forum ADD CONSTRAINT forum_learning_fk
	FOREIGN KEY(learning_id)
	REFERENCES t_learning(id);

ALTER TABLE t_forum ADD UNIQUE(learning_id);
	
CREATE TABLE t_forum_comment(
	id serial,
	user_id int NOT NULL,
	forum_comment_body text NOT NULL,
	forum_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_forum_comment ADD CONSTRAINT forum_comment_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_forum_comment ADD CONSTRAINT forum_comment_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);

ALTER TABLE t_forum_comment ADD CONSTRAINT forum_comment_fk
	FOREIGN KEY(forum_id)
	REFERENCES t_forum(id);
	
CREATE TABLE t_review(
	id serial,
	teacher_id int NOT NULL,
	student_id int NOT NULL,
	score numeric(4,1) NOT NULL,
	notes text,
	learning_task_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_review ADD CONSTRAINT review_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_review ADD CONSTRAINT review_teacher_fk
	FOREIGN KEY(teacher_id)
	REFERENCES t_user(id);
	
ALTER TABLE t_review ADD CONSTRAINT review_student_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);

ALTER TABLE t_review ADD CONSTRAINT review_task_fk
	FOREIGN KEY(learning_task_id)
	REFERENCES t_learning_task(id);

CREATE TABLE t_question_answer(
	id serial,
	user_id int NOT NULL,
	question_id int NOT NULL,
	question_answer text,
	question_options_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_answer ADD CONSTRAINT question_answer_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_question_answer ADD CONSTRAINT question_answer_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);
	
ALTER TABLE t_question_answer ADD CONSTRAINT question_answer_options_fk
	FOREIGN KEY(question_options_id)
	REFERENCES t_question_options(id);

ALTER TABLE t_question_answer ADD CONSTRAINT question_answer_user_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);
	
CREATE TABLE t_question_answer_file(
	id serial,
	question_answer_id int NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_answer_file ADD CONSTRAINT question_answer_file_pk
	PRIMARY KEY(id);
	
ALTER TABLE t_question_answer_file ADD CONSTRAINT question_answer_file_fk
	FOREIGN KEY(question_answer_id)
	REFERENCES t_question_answer(id);
	
ALTER TABLE t_question_answer_file ADD CONSTRAINT question_file_answer_fk
	FOREIGN KEY(file_id)
	REFERENCES t_file(id);