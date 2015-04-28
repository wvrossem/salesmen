# Set-up #

Open a terminal and execute this command:
> ssh-keygen -t rsa
Some questions will be asked. Just press enter each time to skip them.

Note the location where the file is created. This should be something like /home/wouter/.ssh/id\_rsa.pub

Now we will copy this file to the wilma server using scp:
> scp .ssh/id\_rsa.pub se2\_0910@wilma.vub.ac.be:

Log in to wilma with the se2\_0910 account.

Then we add the key to the authorized keys:
> cat id\_rsa.pub >> .ssh/authorized\_keys2

You cna now test if this worked by trying to log in to wilma again. You should now log in without entering a password.

# Deploying #

## Starting the server on wilma ##
Log in to wilma and enter these commands:
> . .bashrc

> salesmen-jboss-run

To check if this worked, open your webbrowser and go to http://wilma.vub.ac.be:1388/salesmen

## Deploying ##
Go to the folder where the code resides:
> cd ./salesmen/trunk/salesmen/core

> ant remote-explode