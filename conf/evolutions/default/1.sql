# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table event (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  start_time                datetime,
  end_time                  datetime,
  round_id                  bigint,
  human_location            varchar(255),
  zombie_location           varchar(255),
  constraint pk_event primary key (id))
;

create table map_marker (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  icon                      varchar(255),
  latitude                  double,
  longitude                 double,
  position                  varchar(255),
  time                      datetime,
  round_id                  bigint,
  user_id                   integer,
  constraint pk_map_marker primary key (id))
;

create table message (
  id                        integer auto_increment not null,
  created_at                datetime,
  game_code                 varchar(255),
  recipient                 varchar(255),
  message                   varchar(255),
  location                  varchar(255),
  sender_old                varchar(255),
  round_id                  bigint,
  sender_id                 integer,
  time                      datetime,
  message_type              integer,
  constraint ck_message_message_type check (message_type in (0,1,2)),
  constraint pk_message primary key (id))
;

create table round (
  id                        bigint auto_increment not null,
  game_code                 varchar(255),
  title                     varchar(255),
  description               varchar(255),
  round_rules               varchar(255),
  game_rules                varchar(255),
  contact_info              varchar(255),
  constraint pk_round primary key (id))
;

create table user (
  id                        integer auto_increment not null,
  display_name              varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  is_mod                    tinyint(1) default 0,
  team_old                  varchar(255),
  is_active                 tinyint(1) default 0,
  game_code                 varchar(255),
  name                      varchar(255),
  current_round_id          bigint,
  team                      integer,
  constraint ck_user_team check (team in (0,1,2)),
  constraint pk_user primary key (id))
;

alter table event add constraint fk_event_round_1 foreign key (round_id) references round (id) on delete restrict on update restrict;
create index ix_event_round_1 on event (round_id);
alter table map_marker add constraint fk_map_marker_round_2 foreign key (round_id) references round (id) on delete restrict on update restrict;
create index ix_map_marker_round_2 on map_marker (round_id);
alter table map_marker add constraint fk_map_marker_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_map_marker_user_3 on map_marker (user_id);
alter table message add constraint fk_message_round_4 foreign key (round_id) references round (id) on delete restrict on update restrict;
create index ix_message_round_4 on message (round_id);
alter table message add constraint fk_message_sender_5 foreign key (sender_id) references user (id) on delete restrict on update restrict;
create index ix_message_sender_5 on message (sender_id);
alter table user add constraint fk_user_currentRound_6 foreign key (current_round_id) references round (id) on delete restrict on update restrict;
create index ix_user_currentRound_6 on user (current_round_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table event;

drop table map_marker;

drop table message;

drop table round;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

