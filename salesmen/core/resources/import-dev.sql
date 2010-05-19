-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('Mr_Smiley', 'Mr', 'Smiley', 'smiley@nowhere.com', 0, '1503-07-01', '2007-01-02', 'BE', 'Brussels', 0)
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('duke', 'duke', 'McDuke', 'duke@sun.com', 1, '1503-07-01', '2007-01-02', 'BE', 'Gent', 0)
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('shadowman', 'shadow', 'man', 'shadowman@redhat.com', 0, '1503-07-01', '2007-01-02', 'BE', 'Antwerp', 0)
insert into Member (screenname, firstname, lastname, email, gender, dateOfBirth, membersince, country, city, version) values ('mona', 'mona', 'lisa', 'monalisa@louvre.fr', 1, '1503-07-01', '2007-01-02', 'BE', 'Ostend', 0)

insert into UserRole (name, conditional) values ('user', false);
insert into UserRole (name, conditional) values ('admin', false);

insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, loginAttempts, version) values ('demo', '70D33A98C7E76C7365A7C58F88CA8A89373B6EB5', 'C0FA5E59FC18E1E1', true, 1, 5946808103467846878, 0, 0);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, loginAttempts, version) values ('duke', '8D18E5D7DB472FF8AA3E3984F16BFDDA2B265598', '576EF3D383B00897', true, 2, 5946808103467846878, 0, 0);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, loginAttempts, version) values ('shadowman', '1D70EA4F262D46525F38DA0C1A130FFF1CA02149', '727D5C9995088913', true, 3, 5946808103467846878, 0, 0);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid, activationKey, loginAttempts, version) values ('mona', 'CDF580AB2AFC6F7FBCF5A1F114E383DB0DF9E309', '7D804DD05067750C', true, 4, 5946808103467846878, 0, 0);

insert into UserAccountRoles (accountid, roleid) values (1, 2);
insert into UserAccountRoles (accountid, roleid) values (2, 1);
insert into UserAccountRoles (accountid, roleid) values (3, 1);
insert into UserAccountRoles (accountid, roleid) values (4, 1);

insert into UserRoleGroup (roleid, memberof) values (2, 1);

insert into Category (name,version) values ('Categories', 0);

insert into Category (name,parent_id,version) values ('Book',1, 0);
  insert into Category (name,parent_id,version) values ('Literature', 2, 0);

insert into Category (name,parent_id,version) values ('Music, Moves & Games',1, 0);
  insert into Category (name,parent_id,version) values ('Movies & TV', 4, 0);

insert into Category (name,parent_id,version) values ('Electronics',1, 0);
  insert into Category (name,parent_id,version) values ('TV & Video', 6, 0);

insert into Category (name,parent_id,version) values ('Health & Beauty',1, 0);
  insert into Category (name,parent_id,version) values ('Grocery', 8, 0);
  
insert into Category (name,parent_id,version) values ('Clothing & Shoes',1, 0);
  insert into Category (name,parent_id,version) values ('Accessories', 10, 0);

insert into Category (name,parent_id,version) values ('Sports & Outdoor',1, 0);
  insert into Category (name,parent_id,version) values ('Exercise & Fitness', 12, 0);

insert into Category (name,parent_id,version) values ('Tools & Auto',1, 0);
  insert into Category (name,parent_id,version) values ('Power & Hand Tools', 14, 0);


insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Botten','Zwarte met strikjes',51.6,1,2,0,3,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Auto','Blauw',500,1,4,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('PC','Kapot enzo',5.1,1,6,0,1,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Gotterdammerung','...',11.2,1,8,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Organon','By Aristotle',10,1,10,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Principia Mathematica','By Isaac Newton',9.95,1,2,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Discourse on the Method','By Rene Descartes',25,1,5,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('The Republic','By Plato',24,1,2,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('A set of postulates for the foundation of logic','Alonzo Church',10,1,11,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Recursive Functions of Symbolic Expressions and Their Computation','By John McCarthy',13,1,13,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('On Computable Numbers, with an Application to the Entscheidungsproblem','By Alan Turing',14.5,1,6,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Metaphysical Foundations of Natural Science','By Immanuel Kant',70,1,3,0,2,now(),now(),false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Uber formal unentscheidbare Satze der Principia Mathematica und verwandter','By Kurt Godel',19.5,1,4,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('The GNU Manifesto','By Richard Stallman',10,1,8,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('An Enquiry concerning Human Understanding','By David Hume',45,1,7,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Aspects of the theory of syntax.','By Noam Chomskey',24,1,12,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('The Canon of Medicine','By Avicenna',20.5,1,11,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Introductio in analysin infinitorum','By Leonhard Euler',10.2,1,10,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('On the Constitution of Atoms and Molecules, Part I','By Niels Bohr',10.2,1,9,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Botten','Zwarte met strikjes',51.6,1,2,0,3,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Unconscious Landscape (1967-1968)','Bronze. 12 x 22 x 24 inches.',10.5,1,13,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('The Blind Leading the Blind (1947-1949)','Bronze, dark patina. 69.25 x 69 x 23 inches.',11.70,1,12,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Dejeuner sur l\'herbe','Painting  by Edouard Manet.',10.8,1,11, 0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Eine Kleine Nachtmusik','Serenade  by Wolfgang Amadeus Mozart.',90,1,10,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Vers la flamme','Piano piece by Alexander Scriabin.',100,1,9,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('The Principles of Mathematics','By Bertrand Russel',10,1,6,0,2,now(),now(), true);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('On Certainty','Ludwig Wittgenstein',10.25,1,7,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('A mathematical theory of communication','By C.E. Shannon',13.2,1,15,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('A method for the construction of minimum redundancy codes', 'By David A. Huffman',11.5,1,14,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('Guarded Commands, Nondeterminacy and Formal Derivation of Programs','By Edsger W. Dijkstra',11.5,1,14,0,2,now(),now(), false);
insert into auction (title,description,startingprice,status, category_id, version, user_id,startDate,endDate,hotAuction) values ('A letter from Gödel to von Neumann','By Kurt Gödel',5.2,1,6,0,2,now(),now(), true);



insert into transaction (id,buyeraccount_id,selleraccount_id,auction_id,version,payed,shipped,rating) values (1,1,2,1,0,false,false,0);
insert into transaction (id,buyeraccount_id,selleraccount_id,auction_id,version,payed,shipped,rating) values (2,2,1,2,0,false,false,0);
