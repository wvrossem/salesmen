-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city) values ('Mr_Smiley', 'Mr', 'Smiley', 'smiley@nowhere.com', 0, '1503-07-01', '2007-01-02', 'BE', 'Brussels')
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city) values ('duke', 'duke', 'McDuke', 'duke@sun.com', 1, '1503-07-01', '2007-01-02', 'BE', 'Gent')
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city) values ('shadowman', 'shadow', 'man', 'shadowman@redhat.com', 0, '1503-07-01', '2007-01-02', 'BE', 'Antwerp')
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city) values ('mona', 'mona', 'lisa', 'monalisa@louvre.fr', 1, '1503-07-01', '2007-01-02', 'BE', 'Ostend')

insert into UserRole (name, conditional) values ('user', false);
insert into UserRole (name, conditional) values ('admin', false);

insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey) values ('demo', '70D33A98C7E76C7365A7C58F88CA8A89373B6EB5', 'C0FA5E59FC18E1E1', false, 1, 5946808103467846878);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey) values ('duke', '8D18E5D7DB472FF8AA3E3984F16BFDDA2B265598', '576EF3D383B00897', true, 2, 5946808103467846878);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey) values ('shadowman', '1D70EA4F262D46525F38DA0C1A130FFF1CA02149', '727D5C9995088913', true, 3, 5946808103467846878);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey) values ('mona', 'CDF580AB2AFC6F7FBCF5A1F114E383DB0DF9E309', '7D804DD05067750C', true, 4, 5946808103467846878);

insert into UserAccountRoles (accountid, roleid) values (1, 1);
insert into UserAccountRoles (accountid, roleid) values (2, 1);
insert into UserAccountRoles (accountid, roleid) values (3, 1);
insert into UserAccountRoles (accountid, roleid) values (4, 1);

insert into UserRoleGroup (roleid, memberof) values (2, 1);

insert into Category (name,parent_id) values ('Book',1);
  insert into Category (name,parent_id) values ('Literature', 2);

insert into Category (name,parent_id) values ('Music, Moves & Games',1);
  insert into Category (name,parent_id) values ('Movies & TV', 4);

insert into Category (name,parent_id) values ('Electronics',1);
  insert into Category (name,parent_id) values ('TV & Video', 6);

insert into Category (name,parent_id) values ('Health & Beauty',1);
  insert into Category (name,parent_id) values ('Grocery', 8);
  
insert into Category (name,parent_id) values ('Clothing & Shoes',1);
  insert into Category (name,parent_id) values ('Accessories', 10);

insert into Category (name,parent_id) values ('Sports & Outdoor',1);
  insert into Category (name,parent_id) values ('Exercise & Fitness', 12);

insert into Category (name,parent_id) values ('Tools & Auto',1);
  insert into Category (name,parent_id) values ('Power & Hand Tools', 14);



insert into auction (title,description,startingprice,status) values ('Botten','Zwarte met strikjes',51.6,1);
insert into auction (title,description,startingprice,status) values ('Auto','Blauw',36.51,1);
insert into auction (title,description,startingprice,status) values ('PC','Kapot enzo',5.1,1);
insert into auction (title,description,startingprice,status) values ('Mijn moeder','...',1.88,1);