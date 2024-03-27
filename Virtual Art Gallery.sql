create database v_art_gallery;

use v_art_gallery;

CREATE TABLE Artwork (
    ArtworkID INT PRIMARY KEY,
    Title VARCHAR(255),
    Description TEXT,
    CreationDate DATE,
    Medium VARCHAR(100),
    ImageURL VARCHAR(255),
    ArtistID INT,
    FOREIGN KEY (ArtistID) REFERENCES Artist(ArtistID)
);

CREATE TABLE Artist (
    ArtistID INT PRIMARY KEY,
    Name VARCHAR(100),
    Biography TEXT,
    BirthDate DATE,
    Nationality VARCHAR(100),
    Website VARCHAR(255),
    ContactInformation VARCHAR(255)
);

CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Username VARCHAR(50) UNIQUE,
    Password VARCHAR(255), 
    Email VARCHAR(100) UNIQUE,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    ProfilePicture VARCHAR(255)
);

CREATE TABLE Gallery (
    GalleryID INT PRIMARY KEY,
    Name VARCHAR(100),
    Description TEXT,
    Location VARCHAR(255),
    Curator INT, 
    OpeningHours VARCHAR(255),
    FOREIGN KEY (Curator) REFERENCES Artist(ArtistID)
);

CREATE TABLE User_Favorite_Artwork (
    UserID INT,
    ArtworkID INT,
    PRIMARY KEY (UserID, ArtworkID),
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (ArtworkID) REFERENCES Artwork(ArtworkID)
);

CREATE TABLE Artist_Gallery (
    ArtistID INT,
    GalleryID INT,
    PRIMARY KEY (ArtistID, GalleryID),
    FOREIGN KEY (ArtistID) REFERENCES Artist(ArtistID),
    FOREIGN KEY (GalleryID) REFERENCES Gallery(GalleryID)
);

CREATE TABLE Artwork_Gallery (
    ArtworkID INT,
    GalleryID INT,
    PRIMARY KEY (ArtworkID, GalleryID),
    FOREIGN KEY (ArtworkID) REFERENCES Artwork(ArtworkID),
    FOREIGN KEY (GalleryID) REFERENCES Gallery(GalleryID)
);


INSERT INTO Artist (ArtistID, Name, Biography, BirthDate, Nationality, Website, ContactInformation) 
VALUES 
(1, 'Vincent van Gogh', 'Dutch Post-Impressionist painter', '1853-03-30', 'Dutch', 'https://www.vangoghmuseum.nl/', 'contact@vangogh.com'),
(2, 'Leonardo da Vinci', 'Italian polymath', '1452-04-15', 'Italian', 'https://leonardodavinci.net/', 'contact@leonardo.com'),
(3, 'Pablo Picasso', 'Spanish painter, sculptor, printmaker, ceramicist, and stage designer', '1881-10-25', 'Spanish', 'https://www.picasso.fr/', 'contact@picasso.com');

INSERT INTO User (UserID, Username, Password, Email, FirstName, LastName, DateOfBirth, ProfilePicture) 
VALUES 
(1, 'user1', 'password1', 'user1@example.com', 'John', 'Doe', '1990-01-01', 'profile1.jpg'),
(2, 'user2', 'password2', 'user2@example.com', 'Jane', 'Smith', '1995-05-15', 'profile2.jpg');

INSERT INTO Artwork (ArtworkID, Title, Description, CreationDate, Medium, ImageURL, ArtistID) 
VALUES (2, 'Mona', 'wood', '1503-01-01', 'Oil', 'mona_lisa.jpg', 2);

INSERT INTO Artwork (ArtworkID, Title, Description, CreationDate, Medium, ImageURL, ArtistID) 
VALUES (3, 'Guernica', 'Oil painting on canvas', '1937-05-01', 'Oil', 'guernica.jpg', 3);

INSERT INTO Gallery (GalleryID, Name, Description, Location, Curator, OpeningHours) 
VALUES 
(1, 'Louvre Museum', 'World-renowned art museum', 'Paris, France', 2, '9:00 AM - 6:00 PM'),
(2, 'Van Gogh Museum', 'Art museum dedicated to the works of Vincent van Gogh', 'Amsterdam, Netherlands', 1, '10:00 AM - 5:00 PM'),
(3, 'Prado Museum', 'Main Spanish national art museum', 'Madrid, Spain', 3, '10:00 AM - 8:00 PM');

INSERT INTO Artwork_Gallery (ArtworkID, GalleryID) 
VALUES 
(2, 1), 
(3, 3); 

INSERT INTO Artist_Gallery (ArtistID, GalleryID)
VALUES (1, 1);

INSERT INTO Artist_Gallery (ArtistID, GalleryID)
VALUES (2, 2);


INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) 
VALUES 
(1, 2);