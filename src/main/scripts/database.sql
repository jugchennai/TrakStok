
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

/* Inserts some dummy Companies */
INSERT INTO TS_COMPANY(displayName, symbol) VALUES ('Orange','ORAE');
INSERT INTO TS_COMPANY(displayName, symbol) VALUES ('Moon','MOON');
INSERT INTO TS_COMPANY(displayName, symbol) VALUES ('Macrosoft','MAST');
INSERT INTO TS_COMPANY(displayName, symbol) VALUES ('Zipro','ZIPR');
INSERT INTO TS_COMPANY(displayName, symbol) VALUES ('SBM','SBM');

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

create table TS_CURRENCY
(
    CURRENCY_CODE VARCHAR(3) not null primary key,
    CURRENCY_NAME VARCHAR(225) not null
);

insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('USD','United States dollar');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('JPY','Japanese Yen');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('BGN','Bulgarian Lev');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('CZK','Czech koruna');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('DKK','Danish krone ');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('GBP','Great British Pound');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('HUF','Hungarian Forint');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('LTL','Lithuanian Litas');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('LVL','Latvian Lats');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('PLN','Polish Zloty');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('RON','Romanian Leu');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('SEK','Swedish Krona');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('CHF','Swiss Franc');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('NOK','Norwegian Krone');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('HRK','Croatian Kuna');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('RUB','Russian Ruble');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('TRY','Turkish Lira');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('AUD','Australian Dollar');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('BRL','Brazilian Real');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('CAD','Canadian Dollar');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('CNY','Chinese Yuan');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('HKD','Hong Kong Dollar');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('IDR','Indonesian Rupiah');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('ILS','Israeli New Sheqel');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('INR','Indian Rupee');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('KRW','South Korean Won');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('MXN','Mexican Peso');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('MYR','Malaysian Ringgit');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('NZD','New Zealand Dollar');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('PHP','Philippine Peso');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('SGD','Singapore Dollar');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('THB','Thai Baht');
insert into TS_CURRENCY (CURRENCY_CODE, CURRENCY_NAME) values('ZAR','South African Rand');

create table TS_EXCHANGE_RATE
(
    EXCHANGE_RATE_ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    CURRENCY_CODE VARCHAR(3),
    EXCHANGE_DATE DATE,
    RATE DOUBLE,
    SOURCE_CURRENCY_CODE VARCHAR(3),
    FOREIGN KEY (CURRENCY_CODE) REFERENCES TS_Currency(CURRENCY_CODE)
);