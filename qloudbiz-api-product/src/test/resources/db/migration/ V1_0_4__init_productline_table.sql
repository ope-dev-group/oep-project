drop table if exists productLine;

create table if not exists productLine (
	lineId varchar(50) primary key,
	lineCode varchar(50) not null,
	lineName varchar(100) not null,
	parentId varchar(50),
	sort int(4) not null,
	status varchar(16) not null,
	createdTime timestamp not null,
	creatorId varchar(50) not null,
	creatorName varchar(50) not null,
	modifyTime timestamp,
	modifierId varchar(50),
	modifierName varchar(50),
	lastModifyTime timestamp,
	ownerCompanyCode varchar(50),
	tenantId varchar(50) not null
);