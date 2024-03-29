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
    Patient bigint REFERENCES Patient (Patient_ID),
    Doctor bigint REFERENCES Doctor (Doctor_ID),
    Creation_Date date  NOT NULL ,
    Validity date  NOT NULL ,
    Priority varchar(32) NOT NULL
);

