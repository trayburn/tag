use tag;

DROP TABLE exits;
DROP TABLE location;
DROP TABLE adversary;

CREATE TABLE IF NOT EXISTS adversary(
	Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    HitPoints int,
    DamageTaken int DEFAULT 0,
    AttackDamage int DEFAULT 100,
    DropItem VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS location(
	Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description VARCHAR(255) DEFAULT 'Description Here',
    AdversaryId INT,
    FOREIGN KEY (AdversaryId) REFERENCES adversary(Id)
);

CREATE TABLE IF NOT EXISTS exits(
	Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Aliases VARCHAR(255) NOT NULL,
    OriginId INT NOT NULL,
    DestinationId INT NOT NULL,
    FOREIGN KEY (OriginId) REFERENCES location(Id),
    FOREIGN KEY (DestinationId) REFERENCES location(Id)
);


INSERT INTO adversary 
(Name, Hitpoints, DropItem)
VALUES
('Sauron', 300, ''),
('Blue Koopa', 20, 'Blue Shell');

INSERT INTO location
(Name, AdversaryId)
VALUES
('Mount Doom', 1),
('The Mac and Cheese Shop', 2);

INSERT INTO location
(Name)
VALUES
("The Deathly Hallows"),
("The Desert"),
("The Amazon"),
("The Reef"),
("The Mall"),
("The Velvet Moose"),
("The Volcano of Death"),
("The Airport"),
("An Ice Cream Truck"),
("The Mountains");

INSERT INTO exits 
(Name, Aliases, OriginId, DestinationId)
VALUES
('The Deathly Brownie', 'tdb, deathly, brownie', 3, 4),
('Heaven Ave', 'h, ave, heaven, ha', 3, 2),
('Rocky Road', 'rocky, road, R, RR', 4, 11),
('Magic Portal', 'Magic, Portal, MP, m, p', 11, 1),
('Jump into Lava', 'Jump, lava, J, L, JL, jumplava, jumpintolava', 1, 9),
('The Dock', 'TD, D, docks, dock', 4, 10),
('Flight 121', 'Flight121, f121, 121', 10, 12),
('The Lava Flow', 'Lava, flow, L, F, LF', 12, 9),
('The narrow trail', 'Narrow, N, NT', 12, 1),
('The plane', 'Plane, TP, P', 12, 5),
('Bike trail', 'Bike, B, BT', 12, 6),
('Flight to the mall', 'mall, Fm, FTTM', 10, 7),
('Path to Doom', 'path, p, pd', 7, 1),
('An escalator of doom', 'escalator, e, ed', 7, 9),
('The Cab', 'Cab, c, tc', 1, 7),
('The Pudding Slide', 'TPS, P, S, PS, Pudding, Slide', 8, 10),
('The Front Door', 'TFD, F, D, FD, front, door', 8, 5),
('Camel Path', 'cp, c, camel', 4, 5),
('Amaz-ing Moose', 'AM, Amazing, Moose, A, M', 5, 8),
('The Scenic Route', 'scenic, SR, scenic route, route', 6, 8),
('Highway 121', '121, hwy 121, h121', 2, 5),
('Paradise Road', 'P, r, PR, Paradise, Paradise Rd, Road', 2, 6),
('Highway 21', '21, h21, hwy 21, hwy21', 2, 9),
('The City Walk', 'city, walk, C, w, cw', 6, 7);