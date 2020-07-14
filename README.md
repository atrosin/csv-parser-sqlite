# csv-parser-sqlite

This application uses sqlite-jdbc library to interact with SQLite database through JDBC API.
And opencsv library to write and read data from csv files.

To run the application user have to run main method.Since application started user have to
 introduce into console existing path to csv source file.

At the beginning of the run JDBC configures to work with SQLite in-memory database.
Then Table with id and 10 columns is created by respective DDL query.
Then looping through data,which was previously retrieved from csv file,application checks number
of columns in each row.Each row represented as String array.
If number of columns in the row as per requirements application counts it as received record.
Each valid row is scanned by an apostrophe in the data and is replaced with double apostrophe
to insert it into the DB.
Each valid row is scanned by an empty column.These columns are counted as bad data record
 and at the end of the run they are written in bad-data-<timestamp>.csv file.
 Values without empty columns are counted as successful records and are written into database.

The outcome of the application is valid data written into the db,bad data file,and log file with
numbers of successful,retrieved,failed records.