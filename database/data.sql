start transaction;

-- Proizvođači koji se pojavljuju u statičkim podacima
INSERT IGNORE INTO manufacturers (name) VALUES
                                            ('Lachner'),
                                            ('C.Erba'),
                                            ('Zorka'),
                                            ('Fisher'),
                                            ('J.T.B.'),
                                            ('Sanihem'),
                                            ('VWR'),
                                            ('Promohem'),
                                            ('Centrohem'),
                                            ('Betahem'),
                                            ('WVR'),
                                            ('Pestinorm'),
                                            ('JTB'),
                                            ('Honywell'),
                                            ('Labekspert'),
                                            ('Sigma'),
                                            ('Fluka'),
                                            ('Honywel'),
                                            ('Merck');

-- Hemikalije iz statičke HTML tabele
INSERT IGNORE INTO chemicals (name, unit, minimum_quantity, description) VALUES
                                                                             ('Anilin', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Azotna kiselina', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Dihlormetan', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Heksan', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Hidrogen', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Hlorovodonična kiselina', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Ortofosforna kiselina', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Pentan', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Sirćetna glacijalna', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('fosforna kiselina', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('HCl', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Hromsumporna kiselina', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Sirćetna kiselina', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Sumporna kis.', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('2-Propanol', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('2-propanol', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Aceton', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Acetonitril', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Amonijum-hidroksid', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Anhidrid sirćetne kiseline', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Ugljen tetrahlorid', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Cikloheksan', 'l', NULL, 'Uvezeno iz postojeće evidencije'),
                                                                             ('Dietiletar', 'l', NULL, 'Uvezeno iz postojeće evidencije');

-- Pakovanja / serije hemikalija
INSERT INTO chemical_batches
(chemical_id, manufacturer_id, location_id, purity, package_size, package_unit, purchased_quantity, total_quantity, current_quantity, note)
VALUES
    ((SELECT id FROM chemicals WHERE name = 'Anilin' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Lachner'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Azotna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'C.Erba'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Azotna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Zorka'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 5, 5.0, 5.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Azotna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Lachner'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Dihlormetan' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fisher'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'HPLC', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: Đurđa Promis'),
    ((SELECT id FROM chemicals WHERE name = 'Heksan' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fisher'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'HPLC', 1.5, 'l', 1, 1.5, 1.5, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Heksan' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'J.T.B.'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'Ultra rez', 2.5, 'l', 1, 2.5, 2.5, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Hidrogen' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Sanihem'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), '/', 1.0, 'l', 3, 3.0, 3.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Hlorovodonična kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Lachner'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Hlorovodonična kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Zorka'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Ortofosforna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Lachner'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 2, 2.0, 2.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Ortofosforna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'VWR'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Pentan' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Promohem'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 2, 2.0, 2.0, 'Zadužena osoba: B.D. (Hemijska tehnologija)'),
    ((SELECT id FROM chemicals WHERE name = 'Sirćetna glacijalna' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Centrohem'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), 'P.A.', 1.0, 'l', 1, 1.0, 1.0, 'Zadužena osoba: Đurđa Promis'),
    ((SELECT id FROM chemicals WHERE name = 'Azotna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Zorka'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 1.0, 'l', 5, 5.0, 5.0, NULL),
    ((SELECT id FROM chemicals WHERE name = 'fosforna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Betahem'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 1.0, 'l', 2, 2.0, 2.0, NULL),
    ((SELECT id FROM chemicals WHERE name = 'HCl' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Betahem'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 1.0, 'l', 16, 16.0, 16.0, NULL),
    ((SELECT id FROM chemicals WHERE name = 'Hromsumporna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Zorka'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 1.0, 'l', 30, 30.0, 30.0, NULL),
    ((SELECT id FROM chemicals WHERE name = 'Sirćetna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Zorka'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 1.0, 'l', 15, 15.0, 15.0, NULL),
    ((SELECT id FROM chemicals WHERE name = 'Sirćetna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'WVR'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 2.5, 'l', 1, 2.5, 2.5, NULL),
    ((SELECT id FROM chemicals WHERE name = 'Sumporna kis.' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Zorka'), (SELECT id FROM locations WHERE name = 'Hemikalije NS bunker'), NULL, 1.0, 'l', 28, 28.0, 28.0, NULL),
    ((SELECT id FROM chemicals WHERE name = '2-Propanol' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Promohem'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'HPLC', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: H.T.'),
    ((SELECT id FROM chemicals WHERE name = '2-propanol' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fisher'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'HPLC', 2.5, 'l', 1, 2.5, 2.5, 'Zadužena osoba: H.T.'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Pestinorm'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), '99.70%', 2.5, 'l', 1, 2.5, 2.5, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'JTB'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'Ultra rezi', 2.5, 'l', 3, 7.5, 7.5, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Honywell'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'gc', 2.5, 'l', 3, 7.5, 7.5, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Labekspert'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'PA', 2.5, 'l', 3, 7.5, 7.5, 'Zadužena osoba: H.T.'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Sigma'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'GC', 1.0, 'l', 5, 5.0, 5.0, 'Zadužena osoba: H.T.'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'JTB'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), NULL, 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: J.Agbaba; Napomena: 16.06.2024'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'JTB'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ultrarezi', 2.5, 'l', 3, 7.5, 7.5, 'Zadužena osoba: S.Maletic; Napomena: 18.07.2024'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Centrohem'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'pa', 1.0, 'l', 5, 5.0, 5.0, 'Zadužena osoba: S.Maletic; Napomena: 18.07.2027'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'VWR'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ultrarezi', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: P2C s.Maletic; Napomena: 04.09.2024 Kutija4'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'VWR'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ultrarezi', 2.5, 'l', 7, 17.5, 17.5, 'Zadužena osoba: EnviroCHAR,J.Beljin; Napomena: 04.09.2024 Kutija2.4'),
    ((SELECT id FROM chemicals WHERE name = 'Aceton' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'JTB'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ultrarezi', 2.5, 'l', 7, 17.5, 17.5, 'Napomena: 16.06.2023'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Lachner'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'PA', 1.0, 'l', 6, 6.0, 6.0, 'Zadužena osoba: B.D'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'JTB'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'HPLC', 2.5, 'l', 6, 15.0, 15.0, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fluka'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ACN', 2.5, 'l', 1, 2.5, 2.5, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fisher'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'HPLC', 2.5, 'l', 3, 7.5, 7.5, 'Zadužena osoba: H.T.'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Honywel'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'HPLC', 2.5, 'l', 3, 7.5, 7.5, 'Zadužena osoba: B.D.'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fisher'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'HPLC', 2.5, 'l', 4, 10.0, 10.0, 'Zadužena osoba: Grba'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'VWR'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ultrarezi', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: P2C s.Maletic; Napomena: 04.09.2024 Kutija5'),
    ((SELECT id FROM chemicals WHERE name = 'Acetonitril' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'VWR'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'ultrarezi', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: I.I.T., Supremes; Napomena: 04.09.2024 Kutija2'),
    ((SELECT id FROM chemicals WHERE name = 'Amonijum-hidroksid' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Lachner'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'PA', 1.0, 'l', 2, 2.0, 2.0, 'Zadužena osoba: B.D.'),
    ((SELECT id FROM chemicals WHERE name = 'Anhidrid sirćetne kiseline' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Sigma'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), '99%', 1.0, 'l', 2, 2.0, 2.0, 'Zadužena osoba: B.D.'),
    ((SELECT id FROM chemicals WHERE name = 'Azotna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Labekspert'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'PA', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: H.T.'),
    ((SELECT id FROM chemicals WHERE name = 'Azotna kiselina' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fluka'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'PA', 2.5, 'l', 4, 10.0, 10.0, 'Zadužena osoba: B.D.'),
    ((SELECT id FROM chemicals WHERE name = 'Ugljen tetrahlorid' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Merck'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), '99,9%', 2.5, 'l', 2, 5.0, 5.0, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Cikloheksan' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'JTB'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), '99%', 2.5, 'l', 1, 2.5, 2.5, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Dietiletar' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Fluka'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), 'PA', 2.5, 'l', 1, 2.5, 2.5, 'Zadužena osoba: BD'),
    ((SELECT id FROM chemicals WHERE name = 'Dihlormetan' AND unit = 'l'), (SELECT id FROM manufacturers WHERE name = 'Promohem'), (SELECT id FROM locations WHERE name = 'Hemikalije Superlab'), '99.50%', 2.5, 'l', 6, 15.0, 15.0, 'Zadužena osoba: BD');

-- normalizacija purity values
SET SQL_SAFE_UPDATES = 0;

UPDATE chemical_batches
SET purity = 'Ultra Rezi'
WHERE LOWER(REPLACE(purity,' ','')) IN ('ultrarezi','ultrarez');

SET SQL_SAFE_UPDATES = 1;

UPDATE chemical_batches
SET purity = 'P.A.'
WHERE id > 0
  AND LOWER(REPLACE(purity,'.','')) = 'pa';

UPDATE chemical_batches
SET purity = 'GC'
WHERE id > 0
  AND LOWER(purity) = 'gc';

UPDATE chemical_batches
SET purity = 'HPLC'
WHERE id > 0
  AND LOWER(purity) = 'hplc';


SELECT DISTINCT purity
FROM chemical_batches
ORDER BY purity;