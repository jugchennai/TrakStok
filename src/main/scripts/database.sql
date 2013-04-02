
/*
Table for Users.
*/
CREATE TABLE TS_USERS (
userid INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
displayName varchar(20) not null,
username Varchar(15) not null unique,
password varchar(15) not null,
lastlogin timestamp )