use trakker;

drop table if exists Users;
CREATE TABLE Users (
    id int auto_increment,
    LastName varchar(255),
    FirstName varchar(255),
    Email varchar(255),
    Password varchar(255),
    primary key (id)
);

drop table if exists Lists;
CREATE TABLE Lists(
    id int auto_increment,
    UserId int,
    ListName varchar(255),
    primary key (id)
);

drop table if exists Movies;
CREATE TABLE Movies(
    id int auto_increment,
    Title varchar(255),
    Director varchar(255),
    length int,
    Genre varchar(255),
    primary key (id)
);

drop table if exists ListsToMovies;
CREATE TABLE ListsToMovies(
    id int auto_increment,
    ListId int,
    MovieId int,
    primary key (id)
);

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'noah2745';
flush privileges;


