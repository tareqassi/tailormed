 create table patients (
       id bigint not null,
        createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        address varchar(255),
        city varchar(255),
        dateOfBirth date,
        dateOfDeath date,
        deceased bit,
        firstName varchar(255),
        gender varchar(255),
        lastModifiedDate date,
        lastName varchar(255),
        mrn bigint,
        sex varchar(255),
        state varchar(255),
        zipCode varchar(255),
        primary key (id)
    ) engine=InnoDB
    
    create table treatments (
       id varchar(255) not null,
        createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        cyclesNumber varchar(255),
        diagnosis varchar(255),
        displayName varchar(255),
        endDate date,
        patientId bigint,
        protocolId varchar(255),
        startDate date,
        status varchar(255),
        treatmentLine varchar(255),
        primary key (id)
    ) engine=InnoDB