-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('Mr_Smiley', 'Mr', 'Smiley', 'smiley@nowhere.com', 0, '1503-07-01', '2007-01-02', 'BE', 'Brussels', 0)
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('duke', 'duke', 'McDuke', 'duke@sun.com', 1, '1503-07-01', '2007-01-02', 'BE', 'Gent', 0)
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('shadowman', 'shadow', 'man', 'shadowman@redhat.com', 0, '1503-07-01', '2007-01-02', 'BE', 'Antwerp', 0)
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('mona', 'mona', 'lisa', 'monalisa@louvre.fr', 1, '1503-07-01', '2007-01-02', 'BE', 'Ostend', 0)

insert into UserRole (name, conditional) values ('user', false);
insert into UserRole (name, conditional) values ('admin', false);

insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, version) values ('demo', '70D33A98C7E76C7365A7C58F88CA8A89373B6EB5', 'C0FA5E59FC18E1E1', true, 1, 5946808103467846878, 0);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, version) values ('duke', '8D18E5D7DB472FF8AA3E3984F16BFDDA2B265598', '576EF3D383B00897', true, 2, 5946808103467846878, 0);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, version) values ('shadowman', '1D70EA4F262D46525F38DA0C1A130FFF1CA02149', '727D5C9995088913', true, 3, 5946808103467846878, 0);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, version) values ('mona', 'CDF580AB2AFC6F7FBCF5A1F114E383DB0DF9E309', '7D804DD05067750C', true, 4, 5946808103467846878, 0);

insert into UserAccountRoles (accountid, roleid) values (1, 2);
insert into UserAccountRoles (accountid, roleid) values (2, 1);
insert into UserAccountRoles (accountid, roleid) values (3, 1);
insert into UserAccountRoles (accountid, roleid) values (4, 1);

insert into UserRoleGroup (roleid, memberof) values (2, 1);

insert into Category (name,version) values ('Categories', 0);

insert into Category (name,parent_id,version) values ('Book',1, 0);
  --insert into Category (name,parent_id,version) values ('Literature', 2, 0);

insert into Category (name,parent_id,version) values ('Music, Moves & Games',1, 0);
  --insert into Category (name,parent_id,version) values ('Movies & TV', 4, 0);

insert into Category (name,parent_id,version) values ('Electronics',1, 0);
  --insert into Category (name,parent_id,version) values ('TV & Video', 6, 0);

insert into Category (name,parent_id,version) values ('Health & Beauty',1, 0);
  --insert into Category (name,parent_id,version) values ('Grocery', 8, 0);
  
insert into Category (name,parent_id,version) values ('Clothing & Shoes',1, 0);
  --insert into Category (name,parent_id,version) values ('Accessories', 10, 0);

insert into Category (name,parent_id,version) values ('Sports & Outdoor',1, 0);
  --insert into Category (name,parent_id,version) values ('Exercise & Fitness', 12, 0);

insert into Category (name,parent_id,version) values ('Tools & Auto',1, 0);
  --insert into Category (name,parent_id,version) values ('Power & Hand Tools', 14, 0);



insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate) values ('Botten','Zwarte met strikjes',51.6,1,2,0,3,now(),now());
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate) values ('Auto','Blauw',36.51,1,4,0,2,now(),now());
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate) values ('PC','Kapot enzo',5.1,1,6,0,1,now(),now());
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate) values ('Mijn moeder','...',1.88,1,7,0,2,now(),now());