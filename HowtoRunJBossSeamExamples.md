http://ant.apache.org/manual/install.html
> On Windows: Handy tool to install Ant in Windows: [WinAnt](http://code.google.com/p/winant/)

  1. [Download JBoss AS 5](http://sourceforge.net/projects/jboss/files/JBoss/JBoss-5.1.0.GA/). For instance, pick the pre-compiled `jboss-5.1.0.GA-jdk6.zip` as we work with JDK 6.
  1. [Download JBoss Seam 2.2](http://sourceforge.net/projects/jboss/files/JBoss%20Seam/2.2.0.GA).
  1. Create a directory where to install JBoss AS and JBoss Seam.
    * `/opt/salesmen/` for GNU/Linux, or
    * `C:\Salesmen` for windows.
  1. Unpack the downloaded archives in the newly created directory.
  1. Rename unpacked directories or create symbolic links for hiding package versions.
    * For instance, `ln -s jboss-5.X.X.GA jboss` for JBoss AS, and
    * `ln -s jboss-seam-2.X.X.GA jboss-seam`
    * In Windows: Just rename jboss-5.X.X.GA to jboss and jboss-seam-2.X.X.GA to jboss-seam.
  1. Set the `$JBOSS_HOME` environment variable:
    * `export JBOSS_HOME=/opt/salesmen/jboss` in GNU/Linux, and
    * In Windows, go to `Control Panel -> System -> Advanced -> Environment Variables` to create a new environment variable called `JBOSS_HOME` with e.g. `C:\Salesmen\jboss` as value.
  1. Run an instance of the JBoss AS:
    * `cd $JBOSS_HOME/bin` and `./run.sh` in GNU/Linux, and
    * `cd %JBOSS_HOME%\bin` and `run.bat` in Windows
  1. Go to the directory where JBoss Seam is installed.
  1. Edit `build.properties` and add a new binding for `jboss.home`:
    * `jboss.home=/opt/salesmen/jboss` for GNU/Linux, and
    * `jboss.home=C:\\Salesmen\\jboss` for Windows.
  1. Make sure JBoss AS is up-and-running. Check here: http://localhost:8080/
  1. Then go to `jboss-seam/examples/booking` or any other example provided by JBoss Seam.
  1. Run `ant deploy`
  1. Wait until JBoss deploys the example application.
  1. See the running example application here: http://localhost:8080/seam-booking/

Please feel free to adapt this howto document should you think it's necessary.

**NOTE** If you can't make a connection to a postgresql database, you need to 'install' the postgresql driver. You do this by copying the postgresql.jar file to the following directory:
"[path\_to\_jboss](path_to_jboss.md)\common\lib\"
You should be able to find this file somewhere on your harddisk (in the salesmen repository)