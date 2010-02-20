-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Member (userid, screenname, firstname, lastname, email) values (1001, 'Mr_Smiley', 'Mr', 'Smiley', 'smiley@nowhere.com')
insert into Member (userid, screenname, firstname, lastname, email) values (1002, 'duke', 'duke', 'McDuke', 'duke@sun.com')
insert into Member (userid, screenname, firstname, lastname, email) values (1003, 'shadowman', 'shadow', 'man', 'shadowman@redhat.com')
insert into Member (userid, screenname, firstname, lastname, email) values (1004, 'mona', 'mona', 'lisa', 'monalisa@louvre.fr')

insert into UserRole (roleid, name, conditional) values (1001, 'user', false);
insert into UserRole (roleid, name, conditional) values (1002, 'admin', false);

insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (1001, 'demo', '70D33A98C7E76C7365A7C58F88CA8A89373B6EB5', 'C0FA5E59FC18E1E1', true, 1001);
insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (1002, 'duke', '8D18E5D7DB472FF8AA3E3984F16BFDDA2B265598', '576EF3D383B00897', true, 1002);
insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (1003, 'shadowman', '1D70EA4F262D46525F38DA0C1A130FFF1CA02149', '727D5C9995088913', true, 1003);
insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (1004, 'mona', 'CDF580AB2AFC6F7FBCF5A1F114E383DB0DF9E309', '7D804DD05067750C', true, 1004);

insert into UserAccountRoles (accountid, roleid) values (1001, 1002);
insert into UserAccountRoles (accountid, roleid) values (1002, 1001);
insert into UserAccountRoles (accountid, roleid) values (1003, 1001);
insert into UserAccountRoles (accountid, roleid) values (1004, 1001);

insert into UserRoleGroup (roleid, memberof) values (1002, 1001);

--insert into Category (name) values ('Root category');
--insert into Category (name,parent_id) values ('Vehicles',1);
--insert into Category (name,parent_id) values ('Immo',1);



