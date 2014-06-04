== Mysql setup ==
CREATE DATABASE doodleshop;
GRANT ALL PRIVILEGES ON *.* TO 'doodleshop'@'localhost' identified by 'doodleshop' WITH GRANT OPTION;

== Todo ==
-Build number
-flywaydb migration
-Environment detection
