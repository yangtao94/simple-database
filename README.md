# simple-database
An object oriented design of a database.


Functions include : Reading in data (from JSON), Data validation, Constraint checking, Selection and Projection.

Main file lies in JsonDB folder, while seeding data lies in Resources folder.

<p align="center">
  <a href="" rel="noopener">
 <img width=200px height=200px src="https://imgur.com/uofCkbW.png" alt="Project logo"></a>
</p>

<h3 align="center">Simple Database</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
</div>

---

<p align="center">

An object oriented design of a database.

    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Built Using](#built_using)

## 🧐 About <a name = "about"></a>
Building a database with the help of Object Oriented Programming. Functions include : Reading in data (from JSON), Data validation, Constraint checking, Selection and Projection. 

Data within database is from the relevant json files and is not saved once the program closes.

## 🏁 Getting Started <a name = "getting_started"></a>
Clone this repo! And go to working directory. Resources folder contain the seed data; jsonDB folder contains all the main files. 

### Prerequisites
Set up a Java environment. 


## 🎈 Usage <a name="usage"></a>
To run the program, under current directory, type

```
java jsonDB.db.simpleDB ...
```

if you only want to populate the table i.e, test out the validation function, 

```
java jsonDB.db.simpleDB -t <table1> -t <table2> ... 
```

if you want to add in the uesrs, 

```
java jsonDB.db.simpleDB -t <table1> -t <table2> ... -u <userFileName> ...
```

finish off with queries 

```
java jsonDB.db.simpleDB -t <table1> -t <table2> ... -u <userFileName> ... -q <queryFileName> ...
```


## ⛏️ Built Using <a name = "built_using"></a>
- Java

