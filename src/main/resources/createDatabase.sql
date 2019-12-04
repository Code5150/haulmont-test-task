CREATE TABLE IF NOT EXISTS Patient (
    Patient_ID bigint IDENTITY PRIMARY KEY NOT NULL ,
    Surname varchar(64)  NOT NULL ,
    Name varchar(64)  NOT NULL ,
    Patronymic varchar(64)  NOT NULL ,
    PhoneNumber varchar(64)  NOT NULL
);

CREATE TABLE IF NOT EXISTS Doctor (
    Doctor_ID bigint IDENTITY PRIMARY KEY NOT NULL ,
    Surname varchar(64)  NOT NULL ,
    Name varchar(64)  NOT NULL ,
    Patronymic varchar(64)  NOT NULL ,
    Specialization varchar(64)  NOT NULL
);

CREATE TABLE IF NOT EXISTS Prescription (
    Prescription_ID bigint IDENTITY PRIMARY KEY NOT NULL ,
    Description varchar(256)  NOT NULL ,
    Patient bigint,
    Doctor bigint,
    Creation_Date datetime  NOT NULL ,
    Validity datetime  NOT NULL ,
    Priority varchar(16)  NOT NULL
);

ALTER TABLE Prescription ADD CONSTRAINT fk_Prescription_Patient FOREIGN KEY(Patient)
REFERENCES Patient (Patient_ID);

ALTER TABLE Prescription ADD CONSTRAINT fk_Prescription_Doctor FOREIGN KEY(Doctor)
REFERENCES Doctor (Doctor_ID);

INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Жмышенко', 'Valeriy', 'Albertovich', '228-14-88');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Васильев', 'Valeriy', 'Albertovich', '228-14-88');