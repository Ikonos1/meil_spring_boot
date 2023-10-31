create table department
(
    deptcode     varchar(10) not null
        primary key,
    createdate   date        null,
    deptname     varchar(25) null,
    editdate     date        null,
    isdeleted    bit         null,
    createuserid int         null,
    edituserid   int         null
);

create index createuserid
    on department (createuserid);

create index edituserid
    on department (edituserid);

create table roles
(
    id           bigint auto_increment
        primary key,
    created_date datetime(6)  null,
    description  varchar(255) null,
    role_name    varchar(255) null,
    updated_date datetime(6)  null
);

create table employee
(
    employeeid    int          not null
        primary key,
    createdate    date         null,
    dateofbirth   date         null,
    dateofjoining date         null,
    designation   varchar(100) null,
    drivlicense   varchar(100) null,
    editdate      date         null,
    email2        varchar(50)  null,
    emailoffice   varchar(50)  null,
    empname       varchar(200) null,
    hsecomplevel  int          null,
    hsecomppoints int          null,
    hseleadership varchar(40)  null,
    isdeleted     bit          null,
    mobile2       varchar(30)  null,
    mobile3       varchar(30)  null,
    mobileoffice  varchar(30)  null,
    remarks       varchar(200) null,
    whatsappnum   varchar(30)  null,
    createuserid  int          null,
    deptcode      varchar(10)  null,
    edituserid    int          null,
    empstatus     int          null,
    hsefunctionid varchar(10)  null,
    projcode      int          null,
    role_id       bigint       null,
    password      varchar(255) null,
    empfirstname  varchar(200) null,
    emplastname   varchar(200) null,
    constraint UK_ecp6mhmclfffyqi9eet1cb59b
        unique (role_id),
    constraint FK4am1h1y1msy35hglg78wjiv2a
        foreign key (role_id) references roles (id),
    constraint FK67m32k9vkqe8ug32jsw2pytle
        foreign key (deptcode) references department (deptcode),
    constraint FK8h7e9mn9peomikml91pxjty1s
        foreign key (edituserid) references employee (employeeid),
    constraint FK9yrgwys7ju3fddw6r6cc75rq9
        foreign key (createuserid) references employee (employeeid)
);

create table actiontype
(
    actiontypeid int          not null
        primary key,
    action       varchar(150) null,
    createdate   date         null,
    editdate     date         null,
    isdeleted    bit          null,
    createuserid int          null,
    edituserid   int          null,
    constraint FK5t2h4yhn0m8551jnt7y8o2mli
        foreign key (edituserid) references employee (employeeid),
    constraint FK82hb0xrk6n7wkqsrx20ty6a9m
        foreign key (createuserid) references employee (employeeid)
);

create index createuserid
    on actiontype (createuserid);

create index edituserid
    on actiontype (edituserid);

alter table department
    add constraint FKd8ppyiqnqnhdcno2err7m759h
        foreign key (edituserid) references employee (employeeid);

alter table department
    add constraint FKkxu1dejfvupf74qk0c7o78i31
        foreign key (createuserid) references employee (employeeid);

create index createuserid
    on employee (createuserid);

create index deptcode
    on employee (deptcode);

create index edituserid
    on employee (edituserid);

create index empstatus
    on employee (empstatus);

create index hsefunctionid
    on employee (hsefunctionid);

create index projcode
    on employee (projcode);

create table empstatus
(
    empstatusid  int          not null
        primary key,
    createdate   date         null,
    editdate     date         null,
    empstatus    varchar(150) null,
    isdeleted    bit          null,
    createuserid int          null,
    edituserid   int          null,
    constraint FKblx8jtpg30t0t4jrad70lswi7
        foreign key (edituserid) references employee (employeeid),
    constraint FKmio932somy68wyb9bj73wb48d
        foreign key (createuserid) references employee (employeeid)
);

alter table employee
    add constraint FKbrve4ptdqi37w3w7ik6b7m32n
        foreign key (empstatus) references empstatus (empstatusid);

create index createuserid
    on empstatus (createuserid);

create index edituserid
    on empstatus (edituserid);

create table eventlog
(
    eventlogid   int          not null
        primary key,
    createdate   date         null,
    description  varchar(200) null,
    editdate     date         null,
    isdeleted    bit          null,
    logtime      datetime(6)  not null,
    screenname   varchar(200) null,
    createuserid int          null,
    edituserid   int          null,
    loguserid    int          null,
    constraint FKfgfd732lsesxnwlkmxlva2w2u
        foreign key (loguserid) references employee (employeeid),
    constraint FKhpcvxkbq8y0bi5gurdx0g5bqs
        foreign key (edituserid) references employee (employeeid),
    constraint FKl3thp9vah03wafq59de0pf457
        foreign key (createuserid) references employee (employeeid)
);

create index createuserid
    on eventlog (createuserid);

create index edituserid
    on eventlog (edituserid);

create index loguserid
    on eventlog (loguserid);

create table hsefunction
(
    hsefunccode     varchar(10)  not null
        primary key,
    createdate      date         null,
    editdate        date         null,
    hsefuncion_name varchar(200) null,
    isdeleted       bit          null,
    createuserid    int          null,
    edituserid      int          null,
    constraint FK3ytea4nkoj01e9v6mca9dc2hk
        foreign key (createuserid) references employee (employeeid),
    constraint FKowiasyfevxgcb5we4gejgtu0
        foreign key (edituserid) references employee (employeeid)
);

alter table employee
    add constraint FKgdk3utd45x9a58k2j1j9kg87r
        foreign key (hsefunctionid) references hsefunction (hsefunccode);

create index createuserid
    on hsefunction (createuserid);

create index edituserid
    on hsefunction (edituserid);

create table sector
(
    sectorcode   varchar(10) not null
        primary key,
    createdate   date        null,
    editdate     date        null,
    isdeleted    bit         null,
    sectorname   varchar(25) null,
    createuserid int         null,
    edituserid   int         null,
    hohsemgrid   int         not null,
    constraint FKcn6sfhot2po8iupj6pavgjb7u
        foreign key (edituserid) references employee (employeeid),
    constraint FKglm2kgqyfokh0qld7090s0u3m
        foreign key (createuserid) references employee (employeeid),
    constraint FKi2ii6phdr3y92gv44615pmkj9
        foreign key (hohsemgrid) references employee (employeeid)
);

create index createuserid
    on sector (createuserid);

create index edituserid
    on sector (edituserid);

create index hohsemgrid
    on sector (hohsemgrid);

create table state
(
    statecode    varchar(2)  not null
        primary key,
    createdate   date        null,
    editdate     date        null,
    isdeleted    bit         null,
    statename    varchar(25) null,
    createuserid int         null,
    edituserid   int         null,
    constraint FKiqgouwvmvr7pqdwi7dpolfheu
        foreign key (edituserid) references employee (employeeid),
    constraint FKk0sf2t0h0lt9c47qemi24hk1d
        foreign key (createuserid) references employee (employeeid)
);

create table address
(
    addressid    int          not null
        primary key,
    addressline1 varchar(100) null,
    addressline2 varchar(100) null,
    countryname  varchar(100) null,
    createdate   date         null,
    district     varchar(100) null,
    editdate     date         null,
    isdeleted    bit          null,
    pincode      int          null,
    place        varchar(100) null,
    statename    varchar(100) null,
    createuserid int          null,
    edituserid   int          null,
    statecode    varchar(2)   null,
    constraint FK90i91swq1eiesb2u2eye05i7t
        foreign key (edituserid) references employee (employeeid),
    constraint FKkclg91nw6m9qa669fset0oixw
        foreign key (statecode) references state (statecode),
    constraint FKkifqxnq98das9wnld86k3d272
        foreign key (createuserid) references employee (employeeid)
);

create index createuserid
    on address (createuserid);

create index edituserid
    on address (edituserid);

create index statecode
    on address (statecode);

create index createuserid
    on state (createuserid);

create index edituserid
    on state (edituserid);

create table status
(
    statusid     int          not null
        primary key,
    createdate   date         null,
    editdate     date         null,
    isdeleted    bit          null,
    status       varchar(200) not null,
    createuserid int          null,
    edituserid   int          null,
    constraint FK6m8stm0e4n77cjnkkhbv4o8w8
        foreign key (edituserid) references employee (employeeid),
    constraint FKibp8l1940adva1doa8ev9okjq
        foreign key (createuserid) references employee (employeeid)
);

create table project
(
    projid       int            not null
        primary key,
    createdate   date           null,
    editdate     date           null,
    isdeleted    bit            null,
    isoandm      bit            null,
    projname     varchar(150)   null,
    projvalue    decimal(10, 2) null,
    remarks      varchar(200)   null,
    createuserid int            null,
    dupprojid    int            null,
    edituserid   int            null,
    hsecoordid   int            null,
    hsemgrid     int            null,
    pmrepauthid  int            null,
    projcoordid  int            null,
    projhodid    int            null,
    sectorcode   varchar(10)    null,
    statecode    varchar(2)     null,
    status       int            null,
    constraint FK3hwekdm0lmqvt20l5bdka9hio
        foreign key (createuserid) references employee (employeeid),
    constraint FK3rb81yb2jr810cxhi1scbv70m
        foreign key (hsemgrid) references employee (employeeid),
    constraint FK6ll34wvhbjgxp0l8rnhmuoeoi
        foreign key (statecode) references state (statecode),
    constraint FK70ntd4ekcx7ebqyj5r1qnwayx
        foreign key (projcoordid) references employee (employeeid),
    constraint FKb5ax8wd68l63010ykbwidi2yq
        foreign key (sectorcode) references sector (sectorcode),
    constraint FKd7dsey68lya9xaea6j4v6rjja
        foreign key (edituserid) references employee (employeeid),
    constraint FKkvrk1bqkyarbp83iw8fe82s70
        foreign key (status) references status (statusid),
    constraint FKlwqmgysn0di5dba67o7oiqo5n
        foreign key (pmrepauthid) references employee (employeeid),
    constraint FKqg54pl1mkv4lpyvs9n10txw8p
        foreign key (dupprojid) references project (projid),
    constraint FKsmrmqhn3gbe0exwlbx2yji7dg
        foreign key (projhodid) references employee (employeeid),
    constraint FKso4p4i9bgltu1domvf1bq0837
        foreign key (hsecoordid) references employee (employeeid)
);

alter table employee
    add constraint FK5ax75x1cbnjq7p6uv2ahugkpf
        foreign key (projcode) references project (projid);

create index createuserid
    on project (createuserid);

create index dupprojid
    on project (dupprojid);

create index edituserid
    on project (edituserid);

create index hsecoordid
    on project (hsecoordid);

create index hsemgrid
    on project (hsemgrid);

create index pmrepauthid
    on project (pmrepauthid);

create index projcoordid
    on project (projcoordid);

create index projhodid
    on project (projhodid);

create index sectorcode
    on project (sectorcode);

create index statecode
    on project (statecode);

create index status
    on project (status);

create table projectsite
(
    projsiteid     int            not null
        primary key,
    courierpmobile varchar(200)   null,
    createdate     date           null,
    editdate       date           null,
    isdeleted      bit            null,
    latitude       decimal(10, 8) null,
    longitude      decimal(11, 8) null,
    sitename       varchar(200)   null,
    addressid      int            null,
    courierpcode   int            null,
    createuserid   int            null,
    edituserid     int            null,
    projcoordid    int            not null,
    projid         int            null,
    sitemanagerid  int            null,
    constraint FK40wp5txg7p94powqhs3fom1du
        foreign key (sitemanagerid) references employee (employeeid),
    constraint FK4ffn662yssafmi8dwkt37qwkq
        foreign key (edituserid) references employee (employeeid),
    constraint FK7y2fj4a3dd230fs9xyvts7lhp
        foreign key (projid) references project (projid),
    constraint FK9iwmuupi0gb8jslj0dcvkl5vo
        foreign key (addressid) references address (addressid),
    constraint FKp6saro8ev0u9wju2dwgx8qrg7
        foreign key (createuserid) references employee (employeeid),
    constraint FKr72c2n1xb2ytoihibq6gmfy6h
        foreign key (courierpcode) references employee (employeeid),
    constraint FKryr6yjrjrt4kijcgyfeeeuu6f
        foreign key (projcoordid) references employee (employeeid)
);

create index addressid
    on projectsite (addressid);

create index courierpcode
    on projectsite (courierpcode);

create index createuserid
    on projectsite (createuserid);

create index edituserid
    on projectsite (edituserid);

create index projcoordid
    on projectsite (projcoordid);

create index projid
    on projectsite (projid);

create index sitemanagerid
    on projectsite (sitemanagerid);

create index createuserid
    on status (createuserid);

create index edituserid
    on status (edituserid);

create table transaction
(
    transid       int         not null
        primary key,
    actiondate    date        null,
    createdate    date        null,
    date1         date        null,
    date2         date        null,
    docavailable  bit         null,
    editdate      date        null,
    isactivated   bit         null,
    isapproved    bit         null,
    isdeleted     bit         null,
    noticeperiod  int         null,
    remarks       tinytext    null,
    searchtext    tinytext    null,
    actiontypeid  int         null,
    createuserid  int         null,
    dept1         varchar(10) null,
    dept2         varchar(10) null,
    edituserid    int         null,
    employeeid    int         null,
    fromprojectid int         null,
    function1     varchar(10) null,
    function2     varchar(10) null,
    hsecoordid    int         null,
    hsemgrid      int         null,
    newstatus     int         null,
    projectsite   int         null,
    toprojectid   int         null,
    constraint FK195py4xnu6h2j9i2k8rgeaibn
        foreign key (function2) references hsefunction (hsefunccode),
    constraint FK21ns0y2ky2lyc8efer2sgdtth
        foreign key (projectsite) references projectsite (projsiteid),
    constraint FK3x74s8qjtlbwcag588ql2s569
        foreign key (employeeid) references employee (employeeid),
    constraint FK7yse8s9ii93cy4c0ubbrihme0
        foreign key (edituserid) references employee (employeeid),
    constraint FKa28hkh7rlm26j91rpjfebn0i0
        foreign key (dept2) references department (deptcode),
    constraint FKebnbhq3t4xyuup77w450enaa9
        foreign key (toprojectid) references project (projid),
    constraint FKelr5im9aci9t95t72d4aqy1p3
        foreign key (dept1) references department (deptcode),
    constraint FKfl78vatalte6nqacfghx45gsu
        foreign key (hsemgrid) references employee (employeeid),
    constraint FKh7fnpp5qloscdqpgsjs8mkydc
        foreign key (newstatus) references status (statusid),
    constraint FKigvi5l5n961c8ihx1ij16m14e
        foreign key (fromprojectid) references project (projid),
    constraint FKnruqgb30c0o58mktmrs9frusp
        foreign key (hsecoordid) references employee (employeeid),
    constraint FKo1f8x7drmy2r223a5vn8wor1s
        foreign key (createuserid) references employee (employeeid),
    constraint FKre03wyb8793ew4ktxmn3omcc0
        foreign key (actiontypeid) references actiontype (actiontypeid),
    constraint FKtpfmtlonrp97ugnga17adtqe3
        foreign key (function1) references hsefunction (hsefunccode)
);

create index actiontypeid
    on transaction (actiontypeid);

create index createuserid
    on transaction (createuserid);

create index dept1
    on transaction (dept1);

create index dept2
    on transaction (dept2);

create index edituserid
    on transaction (edituserid);

create index employeeid
    on transaction (employeeid);

create index fromprojectid
    on transaction (fromprojectid);

create index function1
    on transaction (function1);

create index function2
    on transaction (function2);

create index hsecoordid
    on transaction (hsecoordid);

create index hsemgrid
    on transaction (hsemgrid);

create index newstatus
    on transaction (newstatus);

create index projectsite
    on transaction (projectsite);

create index toprojectid
    on transaction (toprojectid);


