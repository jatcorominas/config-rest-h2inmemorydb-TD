DROP TABLE IF EXISTS config;
create table config (
	ID INT PRIMARY KEY,
	APPCODE VARCHAR(30) NOT NULL,
	PORT INT NOT NULL,
	VERSION VARCHAR(30) NOT NULL,
	LASTMODIFIEDDATE TIMESTAMP NOT NULL,
);
insert into config (id, appCode, port, version, lastModifiedDate)values(1001,'H2', 8090,'1','2021-02-01')
  ,(1002,'H2', 8976,'2','2021-02-02')
  ,(1003,'H2', 6564,'3','2021-02-03')
  ,(1004,'MONGODB', 5672,'1','2021-02-04')
  ,(1005,'MONGODB', 3471,'2','2021-02-05')
  ,(1006,'MONGODB', 7891,'3','2021-02-06')
  ,(1007,'KAFKA', 9843,'1','2021-02-07')
  ,(1008,'KAFKA', 2890,'2','2021-02-08')
  ,(1009,'KAFKA', 1895,'3','2021-02-09')
  ,(1010,'KAFKA', 2642,'4','2021-02-10')
  ;