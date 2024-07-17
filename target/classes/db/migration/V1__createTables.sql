create table role(
role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
role_name VARCHAR(255),
role_description VARCHAR(255)
);

create table user(
user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
profile_pic BLOB,
first_name VARCHAR(255),
last_name VARCHAR(255),
username VARCHAR(255),
email VARCHAR(255),
contact_no VARCHAR(255),
password VARCHAR(255),
reset_password VARCHAR(255),
terms_and_conditions BOOLEAN,
verification_code VARCHAR(255),
is_deleted BOOLEAN,
acc_created_at TIMESTAMP,
last_updated_by TIMESTAMP,
roles_role_id INT,

FOREIGN KEY (roles_role_id) REFERENCES role(role_id)
);