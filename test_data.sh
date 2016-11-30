#!/bin/ksh
mysql --user=team27 --password=nov#mber5 -h grove.cs.jmu.edu -v <<!! 
SELECT VERSION(), CURRENT_DATE;

# Measures time of execution
SET @start=UNIX_TIMESTAMP();

USE team27_db;

INSERT INTO school (school) VALUES ("Maggie L Walker"),( "James Madison University"), ("University Of Virginia");
INSERT INTO role (role_name) VALUES ('Tester');
INSERT INTO user (password, email, name, username, role_id, is_professor, school_id) VALUES ("defPassword", "my@email.com", "TestName", "UserNameTester123521", 1, 0, 1);
INSERT INTO class (class_num, class_name, class_desc) VALUES (1, 1, "This is a class about stuff");
INSERT INTO posts (author_id, class_id, rating, endorse, notes_desc, text) VALUES (1, 1, 1.0, 1, "These notes describe all of the stuff about some stuff", "Here is the text of the notes. Look at all these great notes");
INSERT INTO posts (author_id, class_id, rating, endorse, notes_desc, text) VALUES (1, 1, 1.0, 1, "These nodafsdfasdgfsadgsadgasdgasdff about some stuff", "Here is the text of the nasdgasdgasdgsagdsagdasgase great notes");
INSERT INTO comments(post_id, comment, author_id, score) VALUES (1, "Here is my comment fadhfkljashkdfhaslkdhfkasd", 1, 5);
INSERT INTO image(file_location, post_id) VALUEs ("location of file", 1);

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