drop table if exists product;

create table if not exists product (
	productId varchar(255) primary key, 
	code  varchar(200) not null,
	name varchar(255)
);