INSERT INTO roles (name) VALUES
('ADMIN'),
('USER');

INSERT INTO locations (name, description) VALUES
('Hemikalije NS bunker', 'Lokacija iz postojeće Excel evidencije'),
('Hemikalije Superlab', 'Lokacija iz postojeće Excel evidencije'),
('Lab 1P', 'Lokacija iz postojeće Excel evidencije'),
('Ormar br.6', 'Skladišni ormar'),
('Frižider', 'Hemikalije koje zahtevaju čuvanje u frižideru'),
('Kutija 1', 'Skladišna kutija'),
('Kutija 2', 'Skladišna kutija'),
('Kutija 3', 'Skladišna kutija'),
('Kutija 4', 'Skladišna kutija'),
('Kutija 5', 'Skladišna kutija');

INSERT INTO manufacturers (name) VALUES
('Sigma'),
('Betahem'),
('Centrohem'),
('Lahner'),
('Lachner'),
('Fisher'),
('Roth'),
('Acros'),
('Loba'),
('Superlab'),
('C.Erba'),
('Alkaloid'),
('Hemos'),
('Merk'),
('Honeywell'),
('JTB'),
('NRK'),
('Zorka');

INSERT INTO users
(first_name, last_name, email, password, active, role_id)
VALUES
('Nikola', 'Rajic', 'admin@test.com', 'admin123', true, 1),
('Test', 'User', 'user@test.com', 'user123', true, 2);