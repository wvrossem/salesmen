-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Member (userid, screenname, firstname, lastname, email) values (1, 'Mr_Smiley', 'Mr', 'Smiley', 'smiley@nowhere.com')
insert into Member (userid, screenname, firstname, lastname, email) values (2, 'duke', 'duke', 'McDuke', 'duke@sun.com')
insert into Member (userid, screenname, firstname, lastname, email) values (3, 'shadowman', 'shadow', 'man', 'shadowman@redhat.com')
insert into Member (userid, screenname, firstname, lastname, email) values (4, 'mona', 'mona', 'lisa', 'monalisa@louvre.fr')

insert into UserRole (roleid, name, conditional) values (1, 'user', false);
insert into UserRole (roleid, name, conditional) values (2, 'admin', false);

insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (1, 'demo', '70D33A98C7E76C7365A7C58F88CA8A89373B6EB5', 'C0FA5E59FC18E1E1', true, 1);
insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (2, 'duke', '8D18E5D7DB472FF8AA3E3984F16BFDDA2B265598', '576EF3D383B00897', true, 2);
insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (3, 'shadowman', '1D70EA4F262D46525F38DA0C1A130FFF1CA02149', '727D5C9995088913', true, 3);
insert into UserAccount (accountid, username, passwordhash, passwordsalt, enabled, userid) values (4, 'mona', 'CDF580AB2AFC6F7FBCF5A1F114E383DB0DF9E309', '7D804DD05067750C', true, 4);

insert into UserAccountRoles (accountid, roleid) values (1, 2);
insert into UserAccountRoles (accountid, roleid) values (2, 1);
insert into UserAccountRoles (accountid, roleid) values (3, 1);
insert into UserAccountRoles (accountid, roleid) values (4, 1);

insert into UserRoleGroup (roleid, memberof) values (2, 1);