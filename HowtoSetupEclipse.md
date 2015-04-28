# Introduction #

This page focuses on showing you how to setup your Salesmen development environment in Eclipse.

## Prerequisites ##

You should know how to [install JBoss](HowtoRunJBossSeamExamples.md).

# Eclipse #

If you have not yet installed Eclipse, here is where you can find [Eclipse downloads](http://www.eclipse.org/downloads/).

Immediate download links for different operating systems below:

> [Linux 32bit](http://www.eclipse.org/downloads/download.php?file=/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-linux-gtk.tar.gz&url=http://eclipse.mirror.kangaroot.net/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-linux-gtk.tar.gz&mirror_id=468)

> [Windows](http://www.eclipse.org/downloads/download.php?file=/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-win32.zip&url=http://eclipse.mirror.kangaroot.net/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-win32.zip&mirror_id=468)

> [Mac OS X Carbon 32bit](http://www.eclipse.org/downloads/download.php?file=/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-macosx-carbon.tar.gz&url=http://eclipse.mirror.kangaroot.net/eclipse/downloads/drops/R-3.5.1-200909170800/eclipse-SDK-3.5.1-macosx-carbon.tar.gz&mirror_id=468)

Once downloaded, extract this archive.

Optional:

> To use a different version of Java than Eclipse's default, download the version you want and run Eclipse using this command:

> eclipse -vm /folderof/alternatejavaversion/bin/java/

Choose a folder as workspace for Eclipse.

# Install Plugins #

Arrived in Eclipse, choose Help, Install New Software.
Choose Add..., these are the 3 plugins to add:

> Name: JBoss Tools Dev
> Location: http://download.jboss.org/jbosstools/updates/JBossTools-3.1.0.M4

> Name: BIRT 2.5
> Location: http://download.eclipse.org/birt/update-site/2.5/

> Name: m2eclipse
> Location: http://m2eclipse.sonatype.org/sites/m2e

> Name: TestNG
> Location: http://beust.com/eclipse

> Name: Subclipse 1.6 Updates
> Location: http://subclipse.tigris.org/update_1.6.x/

Now, choose JBoss Tools Dev, TestNG, Subclipse, check everything, choose Next, Finish.

This will take several minutes to complete...

# Usage #

## Import Salesmen ##

Go to `File -> New -> Project -> SVN -> Checkout Projects from SVN`

Checkout the Salesmen source code using the following URL (make sure to use https):
https://salesmen.googlecode.com/svn/trunk/salesmen/core/

Edit `$SALESMEN_CORE/build.properties` and adapt the value of the `jboss.home` property.

You may need to add a JBoss 5.1 Server to your Eclipse installation. This is done by going to `File -> New -> Other -> Server -> Server`.

Please feel free to post to the MailingList for troubleshooting.