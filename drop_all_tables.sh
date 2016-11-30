#!/bin/ksh
mysql --user=team27 --password=nov#mber5 -h grove.cs.jmu.edu -v <<!! 
SELECT VERSION(), CURRENT_DATE;

# Measures time of execution
SET @start=UNIX_TIMESTAMP();

USE team27_db;

DROP TABLE comments;
DROP TABLE image;
DROP TABLE posts;
DROP TABLE user;
DROP TABLE role;
DROP TABLE school;
DROP TABLE class;




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