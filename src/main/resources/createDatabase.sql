CREATE TABLE "Patient" (
    "PatientID" longvarchar  NOT NULL ,
    "Name" varchar(64)  NOT NULL ,
    "Surname" varchar(64)  NOT NULL ,
    "Patronymic" varchar(64)  NOT NULL ,
    "PhoneNumber" varchar(64)  NOT NULL ,
    CONSTRAINT "pk_Patient" PRIMARY KEY (
        "PatientID"
    )
);

CREATE TABLE "Doctor" (
    "DoctorID" longvarchar  NOT NULL ,
    "Name" varchar(64)  NOT NULL ,
    "Surname" varchar(64)  NOT NULL ,
    "Patronymic" varchar(64)  NOT NULL ,
    "Specialization" varchar(64)  NOT NULL ,
    CONSTRAINT "pk_Doctor" PRIMARY KEY (
        "DoctorID"
    )
);

CREATE TABLE "Prescription" (
    "PrescriptionID" longvarchar  NOT NULL ,
    "Description" varchar(256)  NOT NULL ,
    "Patient" longvarchar  NOT NULL ,
    "Doctor" longvarchar  NOT NULL ,
    "CreationDate" datetime  NOT NULL ,
    "Validity" datetime  NOT NULL ,
    "Priority" varchar(16)  NOT NULL ,
    CONSTRAINT "pk_Prescription" PRIMARY KEY (
        "PrescriptionID"
    )
);

ALTER TABLE "Prescription" ADD CONSTRAINT "fk_Prescription_Patient" FOREIGN KEY("Patient")
REFERENCES "Patient" ("PatientID");

ALTER TABLE "Prescription" ADD CONSTRAINT "fk_Prescription_Doctor" FOREIGN KEY("Doctor")
REFERENCES "Doctor" ("DoctorID");
