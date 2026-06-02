USE chem_lab_inventory;

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    role_id BIGINT NOT NULL,

    CONSTRAINT fk_users_role
        FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE manufacturers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE locations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE chemicals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    unit VARCHAR(20) NOT NULL,
    minimum_quantity DECIMAL(12,3),
    description VARCHAR(500),

    CONSTRAINT uq_chemical_name_unit UNIQUE (name, unit)
);

CREATE TABLE chemical_batches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    chemical_id BIGINT NOT NULL,
    manufacturer_id BIGINT,
    location_id BIGINT,
    responsible_user_id BIGINT,

    purity VARCHAR(100),

    package_size DECIMAL(12,3),
    package_unit VARCHAR(20),

    purchased_quantity INT,
    total_quantity DECIMAL(12,3),
    current_quantity DECIMAL(12,3),

    expiration_date DATE,
    price DECIMAL(12,2),

    minimum_quantity_alarm DECIMAL(12,3),

    sds_file_path VARCHAR(500),
    certificate_file_path VARCHAR(500),

    note VARCHAR(500),

    CONSTRAINT fk_batch_chemical
        FOREIGN KEY (chemical_id) REFERENCES chemicals(id),

    CONSTRAINT fk_batch_manufacturer
        FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id),

    CONSTRAINT fk_batch_location
        FOREIGN KEY (location_id) REFERENCES locations(id),

    CONSTRAINT fk_batch_responsible_user
        FOREIGN KEY (responsible_user_id) REFERENCES users(id)
);

CREATE TABLE consumptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    chemical_batch_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    quantity DECIMAL(12,3) NOT NULL,
    unit VARCHAR(20) NOT NULL,

    date_taken DATE NOT NULL,
    note VARCHAR(500),

    CONSTRAINT fk_consumption_batch
        FOREIGN KEY (chemical_batch_id) REFERENCES chemical_batches(id),

    CONSTRAINT fk_consumption_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);