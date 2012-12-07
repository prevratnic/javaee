create table users (
  id number(6) primary key,
  login varchar2(20) unique
);

create table registration(
  code number(6) primary key,
  id number(6),
  role varchar2(16),
  date1 timestamp,
  constraint user_reg foreign key(id) references users(id)
);
