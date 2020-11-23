



/* AUTH */
insert into AUTHORITIES values(1,'user1','ROLE_USER')

insert into AUTHORITIES values(2 ,'admin','ROLE_ADMIN')


/* users */

insert into USERS values(1,'user1','{noop}pass1',true,'ROLE_USER')

insert into USERS values(2,'admin','{noop}admin123',true,'ROLE_ADMIN')






insert into DRINK values(null ,'sekersiz mesrubat',3,'Mesrubatlar','Cola sekersiz' )

insert into DRINK values(null,'mesrubat',3,'Mesrubatlar','Coco-Cola' )


insert into Food values(null , 'çikolatalı tatlı',20,'Tatlılar','Profiterol' )

insert into Food values(null , 'oreo Cheesecake',30,'Tatlılar','Cheesecake' )


insert into Food values(null, 'et döner',25,'Yemekler','Döner' )

insert into Food values(null , 'füme sandviç',15,'Yemekler','Sandviç' )


insert into Orders values(null , 1,2,20,CURRENT_TIMESTAMP ,'CASH')