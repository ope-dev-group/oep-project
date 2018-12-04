drop table if exists productLine;

create table if not exists productLine (
	lineId varchar(50) primary key,
	lineCode varchar(50) not null,
	lineName varchar(100) not null,
	parentId varchar(50) null,
	sort int(4) not null,
	status varchar(16) not null,
	createdTime timestamp not null default CURRENT_DATE(),
	creatorId varchar(50) not null,
	creatorName varchar(50) not null,
	modifyTime timestamp null default CURRENT_DATE(),
	modifierId varchar(50) null,
	modifierName varchar(50) null,
	lastModifyTime timestamp null,
	ownerCompanyCode varchar(50) null,
	tenantId varchar(50) not null
);