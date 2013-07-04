TrakStok
========

A proof of concept project for [JSR 354: Money and Currency API](http://java.net/projects/javamoney/pages/Home)

This is a JEE6 web application uses JSR 354: Money and Currency API to show case its features like core api, 
exchange rate, custom currency etc.

Prerequisite
============
Any IDE which supports Maven/Java will do.  This project is developed and run on Glassfish. You still can deploy in any server supports JEE 6.

TrakStok uses following tools :

* [Netbeans Java EE IDE 7.3](http://netbeans.org/downloads/)
* [JSR 354: Money and Currency API](https://github.com/JavaMoney/javamoney)
* Java DB (comes with Glassfish) DB Script is available at _TrakStok/src/main/scripts/database.sql_
* Latest Chrome Browser

Setup
============
* Create a DB Connection in Java DB for `jdbc:derby://localhost:1527/trakstok`
* Go to `database.sql` file and execute in the above connection
* Clone the [JavaMoney](https://github.com/JavaMoney/javamoney) project and build it locally
* Clone the TS project and build.
* In JPA module execute `TSExchangeRateDataLoader` and `TSStockInflectionDataLoader` DataLoader in same order
* Run the Web module

TODO list
============
* JavaFX is currently under construction.
* Mobility yet to start.

JSR 354: Money and Currency API
===============================
 This JSR focuses on defining interfaces and classes to be used for currencies and monetary amounts. Generally the following areas are defined:

* **Core** This area defines the classes representing currencies and monetary amounts.
* **Conversion** This area deals with exchange rates between currencies and provides an API/SPI to perform conversion of monetary amounts from one currency to another.
* **Format** This area defines APIs/SPIs for formatting and parsing of currencies and monetary amounts, providing support for complex usage scenarios.
* **Extensions** This area defines additional functionas and utilities that are registered/accessible using the Java Money extensions SPI. 

NOTE:
=====
TrakStok-JavaFX - Is using [JavaFX Maven plugin](https://github.com/zonski/javafx-maven-plugin). To setup such project you can find it [here](http://www.zenjava.com/2012/11/24/from-zero-to-javafx-in-5-minutes/).

License
========
 Copyright 2013 JUGChennai.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 imitations under the License.