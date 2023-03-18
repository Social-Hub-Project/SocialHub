--CLEANING
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS post_category;
DROP TABLE IF EXISTS followers;
DROP TABLE IF EXISTS my_user;
DROP TABLE IF EXISTS role;

--TABLE CREATION (DO NOT SWAP PLACES!)
CREATE TABLE role(
   	id_role INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  	role VARCHAR(50) NOT NULL
);

CREATE TABLE my_user(
	id_my_user INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   	id_role INT REFERENCES role(id_role) NOT NULL,
  	email VARCHAR(100),
   	password VARCHAR(100),
  	name VARCHAR(70),
  	surname VARCHAR(70),
  	date_of_birth VARCHAR(25),
  	residence VARCHAR(200),
  	sex BOOLEAN,
  	banned BOOLEAN,
  	private BOOLEAN,
  	profile_photo_source VARCHAR,
  	background_photo_source VARCHAR,
   	active BOOLEAN,
	creation_date DATE
);

CREATE TABLE followers(
	id_followers INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  	id_my_user INT REFERENCES my_user(id_my_user) NOT NULL,
  	id_follower INT REFERENCES my_user(id_my_user) NOT NULL,
  	creation_date DATE
);

CREATE TABLE post_category(
	id_post_category INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  	name VARCHAR(100)
);

CREATE TABLE post(
	id_post INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  	id_my_user INT REFERENCES my_user(id_my_user) NOT NULL,
  	id_post_category INT REFERENCES post_category(id_post_category) NOT NULL,
  	content VARCHAR(1000),
  	blocked BOOLEAN,
  	creation_date DATE,
  	photo_source VARCHAR
);

CREATE TABLE rating(
	id_rating INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  	id_post INT REFERENCES post(id_post) NOT NULL,
  	id_my_user INT REFERENCES my_user(id_my_user) NOT NULL,
  	value BOOLEAN,
  	creation_date DATE,
  	modification_date DATE
);

CREATE TABLE comment(
	id_comment INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  	id_my_user INT REFERENCES my_user(id_my_user) NOT NULL,
  	id_post INT REFERENCES post(id_post) NOT NULL,
  	content VARCHAR(500),
  	creation_date DATE
);

--EXAMPLE DATA


-- Insert roles
INSERT INTO role(role)
VALUES ('Admin'), ('Moderator'), ('User');

-- Insert users
INSERT INTO my_user(id_role, email, password, name, surname, date_of_birth, residence, sex, banned, private, profile_photo_source, background_photo_source, active, creation_date)
VALUES (1, 'admin@example.com', 'password', 'John', 'Doe', '1980-01-01', 'New York', true, false, false, 'https://example.com/profile.jpg', 'https://example.com/background.jpg', true, now()),
       (2, 'moderator@example.com', 'password', 'Jane', 'Doe', '1990-01-01', 'Los Angeles', false, false, true, 'https://example.com/profile.jpg', 'https://example.com/background.jpg', true, now()),
       (3, 'user@example.com', 'password', 'Bob', 'Smith', '2000-01-01', 'Chicago', true, false, false, 'https://example.com/profile.jpg', 'https://example.com/background.jpg', true, now());

-- Insert post categories
INSERT INTO post_category(name)
VALUES ('News'), ('Sports'), ('Music');

-- Insert posts
INSERT INTO post(id_my_user, id_post_category, content, blocked, creation_date, photo_source)
VALUES (1, 1, 'This is a news post', false, now(), 'https://example.com/photo1.jpg'),
       (2, 2, 'This is a sports post', false, now(), 'https://example.com/photo2.jpg'),
       (3, 3, 'This is a music post', false, now(), 'https://example.com/photo3.jpg');

-- Insert comments
INSERT INTO comment(id_my_user, id_post, content, creation_date)
VALUES (1, 1, 'Great news!', now()),
       (2, 2, 'I love sports!', now()),
       (3, 3, 'Nice music!', now());

-- Insert ratings
INSERT INTO rating(id_post, id_my_user, value, creation_date, modification_date)
VALUES (1, 2, true, now(), now()),
       (2, 3, false, now(), now()),
       (3, 1, true, now(), now());

-- Insert followers
INSERT INTO followers(id_my_user, id_follower, creation_date)
VALUES (1, 2, now()),
       (1, 3, now()),
       (2, 3, now());