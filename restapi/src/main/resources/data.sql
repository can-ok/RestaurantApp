



/* AUTH */
insert into AUTHORITIES values(1,'ROLE_USER','user1')

insert into AUTHORITIES values(2 ,'ROLE_ADMIN','admin')

Insert into Users values(1,'ROLE_USER',true,'{noop}pass1','user1')

Insert into Users values(2,'ROLE_ADMIN',true,'{noop}admin1','admin')



/* users */


insert into PRODUCT_CATEGORY   values(1,'Mesrubatlar')

insert into PRODUCT_CATEGORY   values(2,'Tatlılar')

insert into PRODUCT_CATEGORY   values(3,'Yemekler')


insert into DRINK values(null ,'sekersiz mesrubat',3,'Cola sekersiz',1 )

insert into DRINK values(null,'mesrubat',3,'Coco-Cola' ,1)

insert into Food values(null , 'çikolatalı tatlı',20,'Profiterol',2 )

insert into Food values(null , 'oreo Cheesecake',30,'Cheesecake' ,2)


insert into Food values(null, 'et döner',25,'Döner',3 )

insert into Food values(null , 'füme sandviç',15,'Sandviç',3 )


insert into TABLE_CATEGORY values(1,'Salon')

insert into TABLE_CATEGORY values(2,'Balkon')


insert into TABLES values(1,true,'MASA 1',1)

insert into TABLES values(2,true,'MASA 2',2)
