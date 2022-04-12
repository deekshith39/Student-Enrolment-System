-- User Table
 create table user (user_id varchar(10) not null,
  password varchar(100) not null,
  role varchar(10) not null,
  primary key (user_id));

-- Degree Table
 create table degree (degree_id varchar(7) not null,
  degree_name varchar(30) not null,
  duration integer not null,
  primary key (degree_id));

-- Course Table
 create table course (course_id varchar(7) not null,
  course_name varchar(30) not null,
  primary key (course_id));

-- Student Table
 create table student (usn varchar(10) not null,
  address varchar(50) not null,
  class_ten_board varchar(7) not null,
  class_ten_percentage float not null,
  class_twelve_board varchar(7) not null,
  class_twelve_percentage float not null,
  contact_number varchar(10) not null,
  dob varchar(20) not null,
  email varchar(30) not null,
  father_contact_number varchar(10) not null,
  father_name varchar(30) not null,
  gender varchar(7) not null,
  mother_name varchar(30) not null,
  name varchar(30) not null,
  course_id varchar(7),
  degree_id varchar(7),
  primary key (usn),
  foreign key (course_id) references course (course_id),
  foreign key (degree_id) references degree (degree_id),
  foreign key (usn) references user(user_id)
  );

-- Fees Table
 create table fees (did varchar(7) not null,
  exam_fee float not null,
  tuition_fee float not null,
  university_fee float not null,
  primary key (did),
  foreign key (did) references degree(degree_id));

-- User Table add column
alter table user add status varchar(8);
