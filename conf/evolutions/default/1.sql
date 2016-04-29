# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        integer auto_increment not null,
  game_code                 varchar(255),
  constraint pk_game primary key (id))
;

create table games (
  id                        varchar(255) not null,
  constraint pk_games primary key (id))
;

create table user (
  id                        integer auto_increment not null,
  user_name                 varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  is_mod                    tinyint(1) default 0,
  type                      varchar(255),
  is_active                 tinyint(1) default 0,
  game_code                 varchar(255),
  constraint pk_user primary key (id))
;

create table users (
  id                        varchar(255) not null,
  constraint pk_users primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table game;

drop table games;

drop table user;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

