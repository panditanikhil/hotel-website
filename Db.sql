create table CustomerRegistration(
Customer_Id Integer primary key AUTOINCREMENT,
Password varchar2(28) not null,
Customer_Name varchar2(50) not null,
Age number(3) not null,
Gender varchar2(20) not null,
Location varchar2(100) not null,
Email varchar2(100) not null,
Contact number(10) not null
);
select * from CustomerRegistration