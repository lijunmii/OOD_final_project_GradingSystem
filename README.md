# Final Project - Grading System

## Authors

Group 21

Yufeng Chen, yufeng72@bu.edu

Rui Pang, ruipang@bu.edu

Hang Xu, xuh@bu.edu

Lijun Chen, lijunc@bu.edu

Anish Lyengar, anishi@bu.edu


## How to test

1. Run main method in Test.java to test all code. The recommended approach is to run the application via IntelliJ IDEA.
2. The code in src folder is the db version (all data stored in the database). You need to setup database before you test it.
If you don't want to setup database, you can test all functions in the no-db version (no database, all data stored in memory) by replacing "\src\SystemDatabase.java" with "\doc\SystemDatabase different version\withoutdb\SystemDatabase.java".
3. You can always choose to use "\doc\SystemDatabase different version\withdb\SystemDatabase.java" for testing the no-db version, or "\doc\SystemDatabase different version\withdb\SystemDatabase.java" for testing the db version.

## How to set up database

1. First install mysql@5.7, and set the root password as "pass" (without the quotes)
2. Next, create a database called grading. In grading database, create a tabke: clientByte.
```
mysql> create database grading;
use grading;
create table clientByte ( userName varchar(20), javaObject blob, primary key (userName));
```
3. keep the database running, then you can test all functions.
