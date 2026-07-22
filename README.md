# Hemijska laboratorija

Web aplikacija za upravljanje evidencijom hemikalija, njihovim količinama, zaduženjima i potrošnjom

## Pokretanje aplikacije

### 1. Kreiranje i inicijalizacija baze podataka

U okviru projekta nalazi se folder `database` koji sadrži SQL fajlove potrebne za kreiranje i inicijalizaciju MySQL baze podataka.

SQL fajlove je potrebno izvršiti sledećim redosledom u MySQL Workbench okruženju:

1. `create-database.sql` – kreiranje baze podataka
2. `schema.sql` – kreiranje tabela i potrebne strukture baze podataka
3. `data.sql` – unos početnih podataka za demonstraciju aplikacije (opciono)

`data.sql` fajl nije neophodan za pokretanje aplikacije, ali omogućava korišćenje aplikacije sa unapred pripremljenim podacima.

### 2. Podešavanje konekcije sa bazom podataka (`src/main/resources/`)

Podešavanja za povezivanje sa MySQL bazom nalaze se u fajlovima
`application.properties` i `application-local.properties`.

Po potrebi je potrebno prilagoditi sledeće podatke:

- URL baze podataka
- korisničko ime
- lozinku

Lokalni podaci za povezivanje sa bazom, koji se mogu razlikovati u zavisnosti od lokalnog okruženja,
definišu se u fajlu `application-local.properties`.

Ovaj fajl je dodat u `.gitignore` i ne nalazi se u Git repozitorijumu,
jer svaki korisnik može koristiti različite kredencijale za pristup lokalnoj bazi podataka.
Zbog toga je fajl `application-local.properties` potrebno kreirati lokalno i uneti odgovarajuće podatke.

U našem timu, korisničko ime za pristup MySQL bazi je isto kod svih članova
i definisano je u fajlu `application.properties`, dok se u fajlu `application-local.properties` nalazi samo lozinka,
koja se razlikuje kod svakog člana tima.


### 3. Pokretanje aplikacije

Projekat je potrebno otvoriti u IntelliJ IDEA razvojnom okruženju
i pokrenuti glavnu Spring Boot klasu aplikacije, `ChemLabInventoryApplication`.

Nakon uspešnog pokretanja, aplikacija je dostupna na adresi:

`http://localhost:8080`

## Potrebno okruženje

Za pokretanje aplikacije potrebno je imati instalirano:

- Java 21 ili noviju verziju
- MySQL
- Maven (ukoliko nije podešena `MAVEN_HOME` environment varijabla, potrebno ju je podesiti)
- IntelliJ IDEA razvojno okruženje