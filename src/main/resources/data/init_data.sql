CREATE TABLE users(
       user_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
       user_password varchar(255) NOT NULL,
       user_email varchar(255) NOT NULL
);

CREATE TABLE groups(
      group_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
      group_name varchar(255) NOT NULL,
      type varchar(255) NOT NULL
);

CREATE TABLE members(
      member_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
      group_id BIGINT NOT NULL,
      user_id BIGINT NOT NULL
);

insert into users(user_id, user_password, user_email) VALUES (999, '$2a$10$9JRsX2UzATtqzs92VftBO.E/nQSV749ccGhp.KH/iotwMO6DF3h4y' , 'hoon@naver.com');
insert into groups(group_id, group_name, type) VALUES (999,'자동매칭그룹', 'AUTO');
insert into groups(group_id, group_name, type) VALUES (998,'자동매칭그룹', 'MANUAL');
insert into members(member_id, group_id, user_id) VALUES (999, 999,999);