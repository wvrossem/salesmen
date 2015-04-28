# Introduction #

In this page, you'll be shown how to interact with the Subversion server to make changes to the source code. It is assumed that the command-line Subversion client is installed on your machine.

# Get a Working Copy #

The _checkout_ command is used to pull an SVN tree such as `https://salesmen.googlecode.com/svn/trunk/` from the server.

The command for a checkout should look like the following. Your password differs from your regular Google password. Your Google Code password can be found [here](http://code.google.com/hosting/settings).

$ svn co https://salesmen.googlecode.com/svn/trunk/ salesmen --username uname@gmail.com

Please note that the above command will checkout the main development branch. Alternate development branches, meeting minutes, timesheets, etc. are not included.

# Commit Your Changes #

The _commit_ command sends your changes to the SVN server. It will commit changed files, added files, and deleted files. Note that you can commit a change to an individual file or changes to files in a specific directory by adding the path to that file/directory to the end of your command. The `-m` option should always be used to pass a log message to the command.

The command following command will commit your changes to the specified file or directory.

`$ svn ci -m "brief explanation of what you have changed" path/to/file/or/directory`

# Update Your Working Copy #

The following command is your friend. Use it all the time so as to make sure that your working copy is synchronized with the server.

`$ svn up`