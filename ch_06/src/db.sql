drop table employees cascade constraint;
drop table employee_history cascade constraint;

create table employees (
  code number(6) constraint emp_pk primary key,
  name varchar2(24) constraint emp_name_nn not null,
  last_name varchar2(32) constraint emp_last_nn not null,
  login varchar2(16) constraint emp_login_nn not null,
  psw varchar2(16)
);

create table employee_history(
  id number(6) constraint history_pk primary key,
  position varchar2(24) constraint position_nn not null,
  manager number(6),
  hire date constraint hire_nn not null,
  dismiss date,
  code number(6),
  constraint emp_manager check(manager > 0),
  constraint emp_dismiss check(dismiss >= hire),
  constraint emp_relation foreign key(code) references employees (code)
);