create table if not exists employeedocuments (
                                                 id integer generated by default as identity,
                                                 documenttype varchar(255),
    issuedby varchar(255),
    issueddate date,
    number integer,
    series integer,
    employeeid integer not null,
    primary key (id)
    );

create table if not exists employees (
                                         id integer generated by default as identity,
                                         address varchar(255),
    dateofdismissal date,
    dateofreceipt date,
    department varchar(255),
    name varchar(255),
    patronymic varchar(255),
    position varchar(255),
    surname varchar(255),
    telephonenumber integer,
    primary key (id)
    );

create table if not exists engine (
                                      id integer generated by default as identity,
                                      fuel varchar(255),
    name varchar(255),
    volume decimal,
    primary key (id)
    );

create table if not exists goods (
                                     id integer generated by default as identity,
                                     amount integer,
                                     height decimal,
                                     length decimal,
                                     name varchar(255),
    weight decimal,
    nomenclatureid integer not null,
    transportorderid integer,
    primary key (id)
    );

create table if not exists nomenclatures (
                                             id integer generated by default as identity,
                                             comments varchar(255),
    height decimal,
    length decimal,
    number varchar(255),
    weight decimal,
    width decimal,
    primary key (id)
    );

create table if not exists roles (
                                     id integer generated by default as identity,
                                     name varchar(255),
    primary key (id)
    );

create table if not exists transportdocuments (
                                                  id integer generated by default as identity,
                                                  documenttype varchar(255),
    issuedby varchar(255),
    issueddate date,
    number integer,
    series integer,
    expiredate date,
    transportid integer not null,
    primary key (id)
    );

create table if not exists transportmodel (
                                              id integer generated by default as identity,
                                              height integer,
                                              length integer,
                                              maxweight integer,
                                              name varchar(255),
    producer varchar(255),
    width integer,
    primary key (id)
    );

create table if not exists transportorders (
                                               id integer not null,
                                               comments varchar(255),
    declinereason varchar(255),
    loadingplace varchar(255) not null,
    orderdate date not null,
    ordernumber varchar(255) not null,
    placemethod varchar(255) not null,
    telephonenumber integer not null,
    transportpresentingdate date not null,
    unloadingplace varchar(255) not null,
    customeremployeeid integer not null,
    responsibleemployeeid integer not null,
    transportid integer not null,
    primary key (id)
    );

create table if not exists transports (
                                          id integer generated by default as identity,
                                          color varchar(255),
    enginepower integer,
    number varchar(255),
    producedyear integer not null,
    startupdate date,
    vin varchar(255),
    writeoffdate date,
    engineid integer not null,
    transportmodelid integer not null,
    primary key (id)
    );

create table if not exists user_roles (
                                          user_id integer not null,
                                          role_id integer not null,
                                          primary key (user_id, role_id)
    );

create table if not exists users (
                                     id integer generated by default as identity,
                                     enabled boolean,
                                     password varchar(255),
    username varchar(255),
    employeeid integer,
    primary key (id)
    );

alter table users
    add constraint UKr43af9ap4edm43mmtq01oddj6 unique (username);

alter table employeedocuments
    add constraint FKm72mg54pl42w57os1r8umytdj
        foreign key (employeeid)
            references employees;

alter table goods
    add constraint FK3wx5f2jxq09tny4xlxvy9qixv
        foreign key (nomenclatureid)
            references nomenclatures;

alter table goods
    add constraint FK1em3lux64h0x7acle20bsolu7
        foreign key (transportorderid)
            references transportorders;

alter table transportdocuments
    add constraint FK9opsbcdhjrghvbfsspjbmtp3k
        foreign key (transportid)
            references transports;

alter table transportorders
    add constraint FK8r9s118csc3t37s0q500rl5sn
        foreign key (customeremployeeid)
            references employees;

alter table transportorders
    add constraint FKavryv3kn3x19lckoibtujjv56
        foreign key (responsibleemployeeid)
            references employees;

alter table transportorders
    add constraint FKa8b1yfveplgwgyipx7q4vjb7l
        foreign key (transportid)
            references transports;

alter table transports
    add constraint FK7c6dko3y26yh0lwnu1yrmdr73
        foreign key (engineid)
            references engine;

alter table transports
    add constraint FKie0nbinouske0yy4rv8c7gytx
        foreign key (transportmodelid)
            references transportmodel;

alter table user_roles
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id)
            references roles;

alter table user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id)
            references users;

alter table users
    add constraint FKnexvof3xb3ca7t1wbke214y1s
        foreign key (employeeid)
            references employees;