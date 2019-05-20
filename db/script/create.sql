/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/5/20 16:23:40                           */
/*==============================================================*/


drop table if exists SLR_SELLER;

drop table if exists SLR_SHOP;

drop table if exists SLR_SHOP_ACCOUNT;

/*==============================================================*/
/* Table: SLR_SELLER                                            */
/*==============================================================*/
create table SLR_SELLER
(
   ID                   bigint not null comment '卖家ID(=组织ID)',
   primary key (ID)
);

alter table SLR_SELLER comment '卖家
卖家是组织，只有在这个表中的组织才是卖家';

/*==============================================================*/
/* Table: SLR_SHOP                                              */
/*==============================================================*/
create table SLR_SHOP
(
   ID                   bigint not null comment '店铺ID',
   SELLER_ID            bigint not null comment '卖家ID(=卖家组织ID)',
   SHOP_NAME            varchar(50) not null comment '店铺名称',
   SHORT_NAME           varchar(25) not null comment '店铺简称',
   ADDERSS              varchar(200) comment '详细地址',
   LONGITUDE            varchar(50) comment '经度',
   LATITUDE             varchar(50) comment '纬度',
   CONTACT              varchar(15) comment '联系方式',
   IS_ENABLED           bool not null default true comment '是否启用',
   CREATE_TIME          datetime not null comment '创建时间',
   MODIFY_TIME          datetime comment '修改时间',
   primary key (ID),
   unique key AK_SHOP_NAME_ORG_ID (SHOP_NAME, SELLER_ID),
   unique key AK_SHOP_ABBRE_ORG_ID (SHORT_NAME, SELLER_ID)
);

alter table SLR_SHOP comment '店铺信息';

/*==============================================================*/
/* Table: SLR_SHOP_ACCOUNT                                      */
/*==============================================================*/
create table SLR_SHOP_ACCOUNT
(
   ID                   bigint not null comment '店铺账号ID',
   SHOP_ID              bigint not null comment '店铺ID',
   SELLER_ID            bigint not null comment '卖家ID',
   ACCOUNT_ID           bigint not null comment '账号ID（SUC_USER表的ID）',
   IS_DEFAULT           bool not null comment '是否为默认店铺',
   primary key (ID),
   unique key AK_SELLER_ID_SHOP_ID_ACCOUNT_ID (SHOP_ID, ACCOUNT_ID, SELLER_ID)
);

alter table SLR_SHOP_ACCOUNT comment '店铺账号';

alter table SLR_SHOP add constraint FK_Relationship_3 foreign key (SELLER_ID)
      references SLR_SELLER (ID) on delete restrict on update restrict;

alter table SLR_SHOP_ACCOUNT add constraint FK_Relationship_1 foreign key (SHOP_ID)
      references SLR_SHOP (ID) on delete restrict on update restrict;

alter table SLR_SHOP_ACCOUNT add constraint FK_Relationship_2 foreign key (SELLER_ID)
      references SLR_SELLER (ID) on delete restrict on update restrict;

