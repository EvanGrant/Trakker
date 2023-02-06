use trakker;
CREATE TABLE Users (
    UserID int,
    LastName varchar(255),
    FirstName varchar(255),
    Email varchar(255),
    Password varchar(255),
);

CREATE TABLE Lists(
    ListID int,
    UserId int,
    ListName varchar(255)
);

CREATE TABLE Movies(
    MovieId,
    Title varchar(255),
    Director varchar(255),
    length int,
    Genre varchar(255)
);

CREATE TABLE ListsToMovies(
    ListId int,
    MovieId int
);


