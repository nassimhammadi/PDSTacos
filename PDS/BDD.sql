CREATE DATABASE CSC;


use CSC;

drop table if exists REPAIRS;
drop table if exists IS_LOGGED;
drop table if exists LOGS_BREAKDOWNS;
drop table if exists CAR;
drop table if exists BIKE;
drop table if exists LOGS_DEPOT;
drop table if exists BREAKDOWNS;
drop table if exists PIECES;
drop table if exists EMPLOYEES;


/*==========================================
====================*/
/* Table: PRIORIZEDLIST */
/*==========================================
====================*/
create table PRIORIZEDLIST
(
ID_PL INT(20) not null AUTO_INCREMENT,
ID_CAR INT(20),
ID_BIKE INT(20),
MODEL VARCHAR(100),
PRIORITY INT(1),
DATE_ENTRY TIMESTAMP,
primary key (ID_PL)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: BREAKDOWNS */
/*==========================================
====================*/
create table BREAKDOWNS
(
ID_BREAKDOWN INT(20) not null
AUTO_INCREMENT,
ID_PIECE INT(20),
NAME_BREAKDOWN VARCHAR(100),
TYPE_BREAKDOWN INT(1),
PRIORITY INT(1),
DURATION INT(2),
JUSTIFICATION VARCHAR(250),
primary key (ID_BREAKDOWN)
)ENGINE=INNODB;



/*==========================================
====================*/
/* Table: EMPLOYEES */
/*==========================================
====================*/
create table EMPLOYEES
(
ID_EMPLOYEE INT(10) not null
AUTO_INCREMENT,
PASSWORD VARCHAR(200) not null,
LAST_NAME VARCHAR(30),
FIRST_NAME VARCHAR(30),
PHONE VARCHAR(10) NOT NULL,
EMAIL VARCHAR(50) unique not null,
IS_MANAGER boolean,
primary key (ID_EMPLOYEE)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: IS_LOGGED */
/*==========================================
====================*/
create table IS_LOGGED
(
ID_VEHICLE INT(10) not null,
ID_BREAKDOWN_LOG INT(20) not null,
primary key (ID_VEHICLE, ID_BREAKDOWN_LOG)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: LOGS_BREAKDOWNS
*/
/*==========================================
====================*/
create table LOGS_BREAKDOWNS
(
ID_BREAKDOWN_LOG INT(20) not null
AUTO_INCREMENT,
ID_CAR INT(20),
ID_BIKE INT(20),
ID_EMPLOYEE INT(20),
ID_BREAKDOWN INT(20),
DATE_ENTRY TIMESTAMP,
DATE_OCCURED TIMESTAMP,
DATE_REPARED TIMESTAMP,
COMMENT VARCHAR(250),
primary key (ID_BREAKDOWN_LOG)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: LOGS_DEPOT */
/*==========================================
====================*/
create table LOGS_DEPOT
(
DATE_ENTRY TIMESTAMP not null,
CAPACITY INT(4),
OCCUPATION INT(4),
NB_ENTRIES INT(4),
NB_EXITS INT(4),
primary key (DATE_ENTRY)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: PIECES */
/*==========================================
====================*/
create table PIECES
(
ID_PIECE INT(20) not null 
AUTO_INCREMENT,
NAME_PIECE VARCHAR(100),
COST INT(6),
STOCK INT(4),
primary key (ID_PIECE)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: REPAIRS */
/*==========================================
====================*/
create table REPAIRS
(
ID_EMPLOYEE INT(10) not null,
ID_BREAKDOWN_LOG INT(20) not null,
primary key (ID_EMPLOYEE, ID_BREAKDOWN_LOG)
)ENGINE=INNODB;

/*==========================================
====================*/
/* Table: VEHICLES */
/*==========================================
====================*/
create table CAR
(
ID_CAR INT(20) not null AUTO_INCREMENT,
LICENSE_NUMBER VARCHAR(7) UNIQUE,
YEAR_VEHICLE INT(4) not null,
IS_ELECTRIC boolean not null,
IS_PRESENT boolean not null,
BRAND VARCHAR(20) ,
MODEL VARCHAR(20) ,
DATE_ENTRY TIMESTAMP,
LOCATION INT(4) UNIQUE,
primary key (ID_CAR)
);

create table BIKE
(
ID_BIKE INT(20) not null AUTO_INCREMENT,
YEAR_VEHICLE INT(4) not null,
IS_ELECTRIC boolean not null,
IS_PRESENT boolean not null,
BRAND VARCHAR(20) ,
MODEL VARCHAR(20) ,
DATE_ENTRY TIMESTAMP,
LOCATION INT(4) UNIQUE,
primary key (ID_BIKE)
);

alter table BREAKDOWNS add constraint
FK_PROVOKES foreign key (ID_PIECE)
references PIECES (ID_PIECE) on delete restrict on
update restrict;


alter table LOGS_BREAKDOWNS add constraint
FK_LOGS2 foreign key (ID_CAR)
references CAR (ID_CAR) on delete restrict on
update restrict;

alter table LOGS_BREAKDOWNS add constraint
FK_LOGS3 foreign key (ID_BIKE)
references BIKE (ID_BIKE) on delete restrict on
update restrict;

alter table LOGS_BREAKDOWNS add constraint
FK_LOGS4 foreign key (ID_EMPLOYEE)
references EMPLOYEES (ID_EMPLOYEE) on delete
restrict on update restrict;

alter table LOGS_BREAKDOWNS add constraint
FK_LOGS5 foreign key (ID_BREAKDOWN)
references BREAKDOWNS (ID_BREAKDOWN) on
delete restrict on update restrict;

alter table REPAIRS add constraint FK_REPAIRS foreign
key (ID_EMPLOYEE)
references EMPLOYEES (ID_EMPLOYEE) on delete
restrict on update restrict;

alter table REPAIRS add constraint FK_REPAIRS2
foreign key (ID_BREAKDOWN_LOG)
references LOGS_BREAKDOWNS
(ID_BREAKDOWN_LOG) on delete restrict on update
restrict;

alter table PRIORIZEDLIST add constraint FK_PL foreign
key (ID_CAR)
references CAR (ID_CAR) on delete restrict on
update restrict;

alter table PRIORIZEDLIST add constraint FK_PL2
foreign key (ID_BIKE)
references BIKE (ID_BIKE) on delete restrict on
update restrict;

CREATE TABLE BREAKDOWNSPIECES(ID_BREAKDOWN INT(20), ID_PIECE INT(20));

alter table BREAKDOWNSPIECES add constraint 
FK_BDP foreign key (ID_BREAKDOWN) 
references BREAKDOWNS(ID_BREAKDOWN) on delete restrict on
update restrict;

alter table BREAKDOWNSPIECES add constraint 
FK_BDP2 foreign key (ID_PIECE) 
references PIECES(ID_PIECE) on delete restrict on
update restrict;




/* -- INSERT */

INSERT INTO
EMPLOYEES(PASSWORD,LAST_NAME,FIRST_NAME,
PHONE,EMAIL,IS_MANAGER)
VALUES('admin','admin','admin','0654852587','admin@csc.fr',TRUE);
INSERT INTO
EMPLOYEES(PASSWORD,LAST_NAME,FIRST_NAME,
PHONE,EMAIL,IS_MANAGER)
VALUES('chaton','HOLLARD','LAURA','0621852587','hollard.laura@etu-upec.fr',FALSE);
INSERT INTO
EMPLOYEES(PASSWORD,LAST_NAME,FIRST_NAME,
PHONE,EMAIL,IS_MANAGER)
VALUES('pizza','LAARABI','HATIM','0632852587','laarabi.hatim@etu-upec.fr',FALSE);




/* -- INSERT USE CASE LAZARE */
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('pneu',70,35);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('phare avant',60,15);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('phare arrière',60,19);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('moteur',900,5);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('cardan',100,25);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('amortisseur',50,32);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('pare-brise',110,12);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('retroviseur',50,20);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('essuie-glace',20,32);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('bougie',12,35);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('vitre avant',40,14);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('vitre arrière',40,9);
;INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('lunette',40,7);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('batterie',45,28);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('pot d\'échappement',50,8);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('alternateur',70,15);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('tête d\'allumeur',10,30);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('portière avant gauche',350,5);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('portière avant droite',350,5);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('portière arrière gauche',350,4);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('portière arrière droite',350,5);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('clignotant avant',15,30);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('clignotant arrière',15,30);
INSERT INTO PIECES( NAME_PIECE, COST, STOCK) 
VALUES('radiateur',80,8);



INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(1,'pneu',1,3,60);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(2,'phare avant',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(3,'phare arrière',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(4,'moteur',1,3,90);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(5,'cardan',1,3,90);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(6,'amortisseur',1,2,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(7,'pare-brise',1,2,60);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(8,'retroviseur',1,1,45);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(9,'essuie-glace',1,1,15);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(10,'bougie',1,1,45);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(11,'vitre avant',1,1,45);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(12,'vitre arrière',1,1,45);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(13,'lunette',1,1,45);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(14,'batterie',1,3,15);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(15,'pot d\'échappement',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(16,'alternateur',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(17,'tête d\'allumeur',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(18,'portière avant gauche',1,2,60);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(19,'portière avant droite',1,2,60);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(20,'portière arrière gauche',1,2,60);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(21,'portière arrière droite',1,2,60);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(22,'clignotant avant',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(23,'clignotant arrière',1,1,30);
INSERT INTO BREAKDOWNS(ID_PIECE,NAME_BREAKDOWN,TYPE_BREAKDOWN,PRIORITY,DURATION) 
VALUES(24,'radiateur',1,1,30);

