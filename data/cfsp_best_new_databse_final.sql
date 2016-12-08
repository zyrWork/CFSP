/*--cfsp 用到的数据库--*/

create database CommunityFinancialServicesPlatform;

create table Customer
(                                                                                                              
   C_Number             int  not null AUTO_INCREMENT,
   C_Account_number     varchar(20) unique key,
   C_Password           varchar(20),
   C_Username           varchar(20),
   C_Sex                varchar(20),
   C_ID                 varchar(30),
   C_Tele               varchar(20),
   C_Email              varchar(20),
   C_Nickname           varchar(20),
   C_Community          varchar(20),
   C_Integration        int,
   C_Level              int,
   C_HealthTimes        int,
   primary key (C_Number)
);

drop table customer;

insert into customer values ( 1,'20132184','940716','zyr','man','411327199408203317','18349366304','2655100813@qq.com','zyr','people',10,1,3);
insert into customer values ( 2,'20132198','123456','zl','man','411327199508203452','14753289542','12365478@qq.com','zl','people',50,2,3);
insert into customer values ( 3,'20132145','123456','zw','wpmen','41253685462200123','2334444554','1236556478@qq.com','was','people',20,2,3);

create table Account
(
   A_ID                 varchar(20)  primary key,
   C_Number             varchar(20),
 /*本来是想作为外键的，可是要改的实在太多，就只有人工修改了*/
   A_Balance            varchar(20) 
  );

insert into account values("001","1","1000");
insert into account values("002","2","2000");
insert into account values("003","3","5000");

create table Detail
(
   D_ID                 varchar(20)   primary key,
   A_ID                 varchar(20),
   foreign key(A_ID) references Account(A_ID)
   on delete CASCADE
   on update CASCADE, 
   D_Type               varchar(20),
   D_Money              varchar(20),
   D_Datetime           varchar(20)
);

insert into detail values("001","001","games","50","1995-10-20");
insert into detail values("002","001","movie","200","2016-10-20");
insert into detail values("003","001","movie","100","2016-7-23");
insert into detail values("004","001","games","50","1995-10-20");
insert into detail values("005","001","movie","200","2016-10-20");
insert into detail values("006","001","movie","100","2016-7-23");
insert into detail values("007","001","games","50","1995-10-20");
insert into detail values("008","001","movie","200","2016-10-20");
insert into detail values("009","001","movie","100","2016-7-23");
insert into detail values("010","001","games","50","1995-10-20");
insert into detail values("011","001","movie","200","2016-10-20");
insert into detail values("012","001","movie","100","2016-7-23");
insert into detail values("013","001","games","50","1995-10-20");
insert into detail values("014","001","movie","200","2016-10-20");
insert into detail values("015","001","movie","100","2016-7-23");

insert into detail values("016","002","eat","100","2016-7-20");
insert into detail values("017","002","go shopping","1000","2016-7-22");
insert into detail values("018","002","games","100","1998-10-20");
insert into detail values("019","003","games","70","2016-7-23");
insert into detail values("020","003","play","10","2015-10-20");
insert into detail values("021","003","games","56","2005-10-20");
insert into detail values("022","003","consume","13","2003-10-20");

create table News
(
   N_ID                 varchar(20) primary key,
   N_Code               varchar(20),
   N_Title              varchar(500),
   N_Info               varchar(10000),
   N_Publisher          varchar(30),
   N_PubTime            varchar(30),
   N_BuyLink            varchar(30),
   N_OrderLink          varchar(30),
   N_Picture            varchar(30)
);

/*drop table news;*/

insert into News values("001","economy","hello","aaaaaaaaaaaaaaaaaaaaaa","zyr","2016-7-23","http://www.baidu.com","http://sohu.com","1.jpg");
insert into News values("002","economy","good morning","aaaaaaaaaaaaaaaaaaaaaa","zyr","2016-6-23","http://www.baidu.com","http://sohu.com","2.jpg");
insert into News values("003","economy","people life","aaaaaaaaaaaaaaaaaaaaaa","zyr","2016-7-13","http://www.baidu.com","http://sohu.com","3.jpg");
insert into News values("004","economy","all over the world","aaaaaaaaaaaaaaaaaaaaaa","zyr","2013-7-26","http://www.baidu.com","http://sohu.com","4.jpg");

insert into News values("005","people","beauty","aaaaaaaaaaaaaaaaaaaaaa","qaz","2016-7-23","http://www.baidu.com","http://sohu.com","1.jpg");
insert into News values("006","people","good life","aaaaaaaaaaaaaaaaaaaaaa","wsx","2016-6-23","http://www.baidu.com","http://sohu.com","2.jpg");
insert into News values("007","other","people thinking","aaaaaaaaaaaaaaaaaaaaaa","edc","2016-7-13","http://www.baidu.com","http://sohu.com","3.jpg");
insert into News values("008","other","on the earth","aaaaaaaaaaaaaaaaaaaaaa","rfv","2013-7-26","http://www.baidu.com","http://sohu.com","4.jpg");

insert into News values("009","health","health","aaaaaaaaaaaaaaaaaaaaaa","qaz","2016-7-23","http://www.baidu.com","http://sohu.com","1.jpg");
insert into News values("010","health","good life","aaaaaaaaaaaaaaaaaaaaaa","wsx","2016-6-23","http://www.baidu.com","http://sohu.com","2.jpg");
insert into News values("011","health","people thinking","aaaaaaaaaaaaaaaaaaaaaa","edc","2016-7-13","http://www.baidu.com","http://sohu.com","3.jpg");
insert into News values("012","health","on the earth","aaaaaaaaaaaaaaaaaaaaaa","rfv","2013-7-26","http://www.baidu.com","http://sohu.com","4.jpg");

create table Orders
(  
   O_Number             varchar(20) primary key,
   C_Number             varchar(20),
   O_Date               varchar(20),
   O_Money              double,
   O_ProductServe       varchar(100),
   O_IsPayOff           varchar(20),
   O_Evaluate           varchar(200)
);

/*drop table orders;*/

insert into orders values ("001","1","2015-5-25",15.6,"ProductServe:send to home","false","the product is good");
insert into orders values ("002","1","2013-1-13",15,"ProductServe:will be ensure","false","the product is better");
insert into orders values ("003","1","2012-2-23",14,"ProductServe:send to home","true","the product is bad");
insert into orders values ("004","1","2014-5-21",15,"ProductServe:will be ensure","false","the product is good");
insert into orders values ("005","1","2016-4-2",15,"ProductServe:send to home","true","the product is good");
insert into orders values ("006","1","2013-6-5",10,"ProductServe:move to house","false","the product is best");
insert into orders values ("007","1","2011-7-15",15,"ProductServe:send to home","true","the product is good");
insert into orders values ("008","1","2013-9-15",15,"ProductServe:can be changed","true","the product is bad");

insert into orders values ("009","1","2015-5-25",15,"ProductServe:send to home","false","the product is good");
insert into orders values ("010","1","2013-1-13",15,"ProductServe:will be ensure","false","the product is better");
insert into orders values ("011","1","2012-2-23",12,"ProductServe:send to home","true","the product is bad");
insert into orders values ("012","1","2014-5-21",64,"ProductServe:will be ensure","false","the product is good");
insert into orders values ("013","1","2016-4-2",65,"ProductServe:send to home","true","the product is good");
insert into orders values ("014","1","2013-6-5",18,"ProductServe:move to house","false","the product is best");
insert into orders values ("015","1","2011-7-15",14,"ProductServe:send to home","true","the product is good");
insert into orders values ("016","1","2013-9-15",50,"ProductServe:can be changed","true","the product is bad");

insert into orders values ("017","1","2015-5-25",78,"ProductServe:send to home","false","the product is good");
insert into orders values ("018","1","2013-1-13",90,"ProductServe:will be ensure","false","the product is better");
insert into orders values ("019","1","2012-2-23",45,"ProductServe:send to home","true","the product is bad");
insert into orders values ("020","1","2014-5-21",78,"ProductServe:will be ensure","false","the product is good");
insert into orders values ("021","1","2016-4-2",14,"ProductServe:send to home","true","the product is good");
insert into orders values ("022","1","2013-6-5",17,"ProductServe:move to house","false","the product is best");
insert into orders values ("023","1","2011-7-15",42,"ProductServe:send to home","true","the product is good");
insert into orders values ("024","1","2013-9-15",75,"ProductServe:can be changed","true","the product is bad");

create table Product
(  
   P_Number             varchar(20) primary key,
   S_Number             varchar(20),
   P_Name               varchar(20),
   P_Explain            varchar(100),
   P_BelongTo           varchar(20),
   P_Category           varchar(20),

   P_InvestKind         varchar(20),
   P_InvestStyle        varchar(20),
   P_InvestEvaluate     varchar(50),

   P_Code               varchar(20),
   P_Deadline           varchar(20), 
   P_SubscriptionPoint  varchar(20),  
   P_DateBegin          varchar(20),
   P_DateEnd            varchar(20),
   P_Price              double,
   P_AnnualizedReturn   double,
   P_ExpectedRate       double,
   P_BrowseCount        int,
   P_BuyCount           int
);
insert into product values("001","001","pencil","pen is used to write","hawdee","common use product","pay money",
                           "get money","the product is good","123456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("002","001","bag","bag is used to caeer items","hawdee","common use product","pay money",
                           "get money","the product is good","152456","2016-7-24","12.6%","2015-5-14","2016-7-22",25.6,12.5,121.3,225,14);

insert into product values("003","001","mouse","mouse is used to point","hawdee","common use product","pay money",
                           "get money","the product is good","113456","2016-7-24","12.6%","2015-15-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("004","001","clothes","clothes is used to put up","hawdee","common use product","pay money",
                           "get money","the product is good","233445","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("005","001","paper","paper is used to write","hawdee","common use product","pay money",
                           "get money","the product is good","355456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("006","001","cup","cup is a container","hawdee","common use product","pay money",
                           "get money","the product is good","136456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("007","001","house","house is used to live in","hawdee","common use product","pay money",
                           "get money","the product is good","432456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("008","001","desk","desk is used to keep items","hawdee","common use product","pay money",
                           "get money","the product is good","127456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("009","001","computer","computer is used to work","hawdee","common use product","pay money",
                           "get money","the product is good","123456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);

insert into product values("010","001","keyboard","keyboard is used to input information","hawdee","common use product","pay money",
                           "get money","the product is good","123456","2016-7-24","12.6%","2015-5-4","2016-7-28",25.6,14.5,12.3,25,14);