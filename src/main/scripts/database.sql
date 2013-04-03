
/* Table for Users. */
CREATE TABLE TS_USERS (
userid INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
displayName VARCHAR(20) not null,
username VARCHAR(15) not null unique,
password VARCHAR(15) not null,
adminrole boolean default false,
lastlogin timestamp default CURRENT TIMESTAMP);

/* Inserts a Admin record to TS_Users table */
INSERT INTO TS_USERS(displayName,username,password,adminrole) VALUES ('Admin', 'admin','admin',true);

/* Company Master table */
CREATE TABLE TS_COMPANY (
companyid INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
displayName VARCHAR(20) not null,
symbol VARCHAR(15) not null unique
);


/* User's Favorite Companies Table */
CREATE TABLE TS_USERS_FAVORITE (
favoriteid INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
userid INTEGER,
companyid Integer,
FOREIGN KEY (userid) REFERENCES TS_USERS(userid),
FOREIGN KEY (companyid) REFERENCES TS_COMPANY(companyid)
);

/* stock inflection table */

CREATE TABLE TS_STOCK_INFLECTION (
inflectionid INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
companyid Integer,
datetime timestamp default CURRENT TIMESTAMP,
amount float,
FOREIGN KEY (companyid) REFERENCES TS_COMPANY(companyid)
);

