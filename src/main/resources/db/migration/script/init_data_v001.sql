INSERT INTO t_role(role_code, role_name, created_by, created_at, updated_by, updated_at, is_active, ver) VALUES
	('ADM', 'Super Admin', 1, now(), 1, now(), true, 1),
	('TCH', 'Pengajar', 1, now(), 1, now(), true, 1),
	('STD', 'Siswa', 1, now(), 1, now(), true, 1);
	
INSERT INTO t_file(files, file_format, created_by, created_at, is_active, ver) VALUES 
	('Profile_pic', '.jpeg', 1, now(), true, 1);
	
INSERT INTO t_profile(profile_name, profile_phone, profile_address, file_id, created_by, created_at, is_active, ver) VALUES
	('Josep Victor Rajadoli', '087808676690', 'Kost Farhan 2, Jl. Lontar 3 No 25a, Menteng Atas', null, 1, now(), true, 1);

INSERT INTO t_user(email, password, role_id, profile_id, created_by, created_at, is_active, ver) VALUES
	('josepvictorrrrr@gmail.com', '$2a$12$9NQrWL8Jajn5HaurJ7hZjeZXOv0jrLlxYvvSajvxAevwzMFcQC1qW', 1, 1, 1, now(), true, 1);