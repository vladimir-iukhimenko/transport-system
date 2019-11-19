CREATE DATABASE transportsystem;
CREATE TABLE transports;
(
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_id PRIMARY KEY,
  number VARCHAR(9) NOT NULL,
  vin VARCHAR(17) NOT NULL UNIQUE,
  transportmodelid int NOT NULL,
  producedyear int NOT NULL,
  startupdate DATE NOT NULL,
  writeoffdate DATE
);
CREATE TABLE transportmodelid (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_transportmodelid PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  color VARCHAR(32),
  producer VARCHAR(32) NOT NULL,
  maxweight INT,
  engineid INT NOT NULL,
  enginepower INT
);
CREATE TABLE engine (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_engineid PRIMARY KEY,
  name VARCHAR(16) NOT NULL,
  volume FLOAT NOT NULL,
  fuel VARCHAR(16) NOT NULL,
);
CREATE TABLE transportdocuments (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_transportdocumentid PRIMARY KEY,
  documenttype VARCHAR(32) NOT NULL,
  series INT,
  number INT NOT NULL,
  maxweight INT,
  issuedby VARCHAR(64) NOT NULL,
  issueddate DATE,
  expiredate DATE
);
CREATE TABLE employeedocuments (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_employeedocumentid PRIMARY KEY,
  documenttype VARCHAR(32) NOT NULL,
  series INT,
  number INT NOT NULL,
  maxweight INT,
  issuedby VARCHAR(64) NOT NULL,
  issueddate DATE,
  employeeid INT
);
CREATE TABLE employees (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_employeeid PRIMARY KEY,
  surname VARCHAR(32) NOT NULL,
  name VARCHAR(32) NOT NULL,
  patronymic VARCHAR(32),
  department VARCHAR(64) NOT NULL,
  position VARCHAR(32) NOT NULL,
  address VARCHAR(100),
  telephonenumber INT,
  dateofreceipt DATE NOT NULL,
  dateofdismissal DATE
);
CREATE TABLE nomenclatures (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_nomenclatureid PRIMARY KEY,
  length FLOAT NOT NULL,
  width FLOAT NOT NULL,
  height FLOAT NOT NULL,
  weight FLOAT NOT NULL,
  comment VARCHAR(1000),
  );
  CREATE TABLE goods (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_goodsid PRIMARY KEY,
  nomenclatureid INT NOT NULL,
  amount INT NOT NULL,
  length FLOAT NOT NULL,
  height FLOAT NOT NULL,
  weight FLOAT NOT NULL,
  );
  CREATE TABLE transportorders (
  id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_transportordersid PRIMARY KEY,
  goodsid INT NOT NULL,
  orderdate DATE NOT NULL,
  transportpresentingdate DATE NOT NULL,
  transportid INT NOT NULL,
  loadingplace VARCHAR(100) NOT NULL,
  unloadingplace VARCHAR(100) NOT NULL,
  placemethod VARCHAR(100) NOT NULL,
  responsibleemployeeid INT NOT NULL,
  telephonenumber INT NOT NULL,
  customeremployeeid INT NOT NULL,
  comment VARCHAR(1000),
  declinereason VARCHAR(500)
  );