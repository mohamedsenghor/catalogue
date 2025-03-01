CREATE DATABASE IF NOT EXISTS catalogue_db;
CREATE USER IF NOT EXISTS 'catalogue_dev'@'%' IDENTIFIED BY 'catalogue_dev_pwd';
GRANT ALL PRIVILEGES ON catalogue_db.* TO 'catalogue_dev'@'%';
FLUSH PRIVILEGES;
