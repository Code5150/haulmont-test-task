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

INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Жмышенко', 'Валерий', 'Альбертович', '228-14-88');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Голубев', 'Максим', 'Алексеевич', '564-65-98');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Соколов', 'Никита', 'Валерьевич', '275-41-28');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Уварова', 'Майя', 'Ярославовна', '297-65-35');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Воронцов', 'Эдуард', 'Русланович', '478-66-53');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Панфилова', 'Элеонора', 'Сергеевна', '365-03-13');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Тарасова', 'Ульяна', 'Владимировна', '594-12-02');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Максимов', 'Роман', 'Алексеевич', '302-80-50');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Михайлов', 'Павел', 'Николаевич', '452-67-39');
INSERT INTO Patient (Surname, Name, Patronymic, PhoneNumber) VALUES ('Копылов', 'Аркадий', 'Авдеевич', '214-39-08');

INSERT INTO Doctor (Surname, Name, Patronymic, Specialization) VALUES ('Кузнецов', 'Петр', 'Иванович', 'Аллерголог');
INSERT INTO Doctor (Surname, Name, Patronymic, Specialization) VALUES ('Воронин', 'Максим', 'Глебович', 'Флеболог');
INSERT INTO Doctor (Surname, Name, Patronymic, Specialization) VALUES ('Сергеев', 'Владимир', 'Станиславович', 'Терапевт');
INSERT INTO Doctor (Surname, Name, Patronymic, Specialization) VALUES ('Тоцкий', 'Валерий', 'Анатольевич', 'Отоларинголог');
INSERT INTO Doctor (Surname, Name, Patronymic, Specialization) VALUES ('Семёнов', 'Степан', 'Александрович', 'Венеролог');

INSERT INTO Prescription (Description, Patient, Doctor, Creation_Date, Validity, Priority) VALUES ('Аддерал', 0, 2, '2019-01-01', '2020-01-01', 'Немедленный');
INSERT INTO Prescription (Description, Patient, Doctor, Creation_Date, Validity, Priority) VALUES ('Антибиотик', 7, 1, '2019-05-07', '2020-05-21', 'Срочный');
INSERT INTO Prescription (Description, Patient, Doctor, Creation_Date, Validity, Priority) VALUES ('Противоаллергическое', 3, 0, '2019-02-03', '2019-03-03', 'Нормальный');
