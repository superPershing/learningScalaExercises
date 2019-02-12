### MySQL
建立用户并赋予权限
```
mysql> CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' WITH GRANT OPTION;
```

require: MySQL 8.0.15  
