-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'
insert into Member (screenname, firstname, lastname, email, gender, dob, membersince) values ('Mr_Smiley', 'Mr', 'Smiley', 'smiley@nowhere.com', 0, '1503-07-01', '2007-01-02')
insert into Member (screenname, firstname, lastname, email, gender, dob, membersince) values ('duke', 'duke', 'McDuke', 'duke@sun.com', 1, '1503-07-01', '2007-01-02')
insert into Member (screenname, firstname, lastname, email, gender, dob, membersince) values ('shadowman', 'shadow', 'man', 'shadowman@redhat.com', 0, '1503-07-01', '2007-01-02')
insert into Member (screenname, firstname, lastname, email, gender, dob, membersince) values ('mona', 'mona', 'lisa', 'monalisa@louvre.fr', 1, '1503-07-01', '2007-01-02')

insert into UserRole (name, conditional) values ('user', false);
insert into UserRole (name, conditional) values ('admin', false);

insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid) values ('demo', '70D33A98C7E76C7365A7C58F88CA8A89373B6EB5', 'C0FA5E59FC18E1E1', true, 1);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid) values ('duke', '8D18E5D7DB472FF8AA3E3984F16BFDDA2B265598', '576EF3D383B00897', true, 2);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid) values ('shadowman', '1D70EA4F262D46525F38DA0C1A130FFF1CA02149', '727D5C9995088913', true, 3);
insert into UserAccount (username, passwordhash, passwordsalt, enabled, userid) values ('mona', 'CDF580AB2AFC6F7FBCF5A1F114E383DB0DF9E309', '7D804DD05067750C', true, 4);

insert into UserAccountRoles (accountid, roleid) values (1, 1);
insert into UserAccountRoles (accountid, roleid) values (2, 1);
insert into UserAccountRoles (accountid, roleid) values (3, 1);
insert into UserAccountRoles (accountid, roleid) values (4, 1);

insert into UserRoleGroup (roleid, memberof) values (2, 1);

--insert into Category (name) values ('Root category');
--insert into Category (name,parent_id) values ('Vehicles',1);
--insert into Category (name,parent_id) values ('Immo',1);



