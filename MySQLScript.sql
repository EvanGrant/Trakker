use trakker;

drop table if exists Users;
CREATE TABLE Users (
    UserID int,
    LastName varchar(255),
    FirstName varchar(255),
    Email varchar(255),
    Password varchar(255)
);

drop table if exists Lists;
CREATE TABLE Lists(
    ListID int,
    UserId int,
    ListName varchar(255)
);

drop table if exists Movies;
CREATE TABLE Movies(
    MovieId int,
    Title varchar(255),
    Director varchar(255),
    length int,
    Genre varchar(255)
);

drop table if exists ListsToMovies;
CREATE TABLE ListsToMovies(
	id int auto_increment,
    ListId int,
    MovieId int,
    primary key (id)
);


