#!/bin/ksh
mysql --user=team27 --password=nov#mber5 -h grove.cs.jmu.edu -v <<!! 
SELECT VERSION(), CURRENT_DATE;

# Measures time of execution
SET @start=UNIX_TIMESTAMP();

USE team27_db;

#Create statements here
CREATE TABLE IF NOT EXISTS role(id INTEGER PRIMARY KEY AUTO_INCREMENT, role_name VARCHAR(255) NOT NULL);

#School
CREATE TABLE IF NOT EXISTS school(id INTEGER PRIMARY KEY AUTO_INCREMENT, school VARCHAR(255) NOT NULL);

#Class
CREATE TABLE IF NOT EXISTS class(id INTEGER PRIMARY KEY AUTO_INCREMENT, class_num INTEGER NOT NULL, class_name VARCHAR(255) NOT NULL, class_desc VARCHAR(255));

#User
CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTO_INCREMENT, password VARCHAR(128) NOT NULL, email VARCHAR(255) NOT NULL, name VARCHAR(255) NOT NULL, username VARCHAR(255) NOT NULL, role_id INTEGER NOT NULL, is_professor BOOLEAN, school_id INTEGER NOT NULL, FOREIGN KEY(role_id) REFERENCES role(id), FOREIGN KEY(school_id) REFERENCES school(id));

#Posts
CREATE TABLE IF NOT EXISTS posts(id INTEGER PRIMARY KEY AUTO_INCREMENT, author_id INTEGER NOT NULL, class_id INTEGER NOT NULL, text TEXT NOT NULL, rating FLOAT, endorse BOOLEAN, notes_desc TEXT, FOREIGN KEY(author_id) REFERENCES user(id), FOREIGN KEY(class_id) REFERENCES class(id));

#Comments
CREATE TABLE IF NOT EXISTS comments(id INTEGER PRIMARY KEY AUTO_INCREMENT, post_id INTEGER NOT NULL, comment TEXT NOT NULL, author_id INTEGER NOT NULL, score INTEGER DEFAULT 0, FOREIGN KEY(post_id) REFERENCES posts(id), FOREIGN KEY(author_id) REFERENCES user(id));

#Image
CREATE TABLE IF NOT EXISTS image(id INTEGER PRIMARY KEY, file_location VARCHAR(255) NOT NULL, post_id INTEGER NOT NULL, FOREIGN KEY(post_id) REFERENCES posts(id));

# Tells you running time
SET
@s=@seconds:=UNIX_TIMESTAMP()-@start,
@d=TRUNCATE(@s/86400,0), @s=MOD(@s,86400),
@h=TRUNCATE(@s/3600,0), @s=MOD(@s,3600),
@m=TRUNCATE(@s/60,0), @s=MOD(@s,60),
@day=IF(@d>0,CONCAT(@d,' day'),''),
@hour=IF(@d+@h>0,CONCAT(IF(@d>0,LPAD(@h,2,'0'),@h),' hour'),''),
@min=IF(@d+@h+@m>0,CONCAT(IF(@d+@h>0,LPAD(@m,2,'0'),@m),' min.'),''),
@sec=CONCAT(IF(@d+@h+@m>0,LPAD(@s,2,'0'),@s),' sec.');

SELECT
CONCAT(@seconds,' sec.') AS seconds,
CONCAT_WS(' ',@day,@hour,@min,@sec) AS elapsed;

quit
!!