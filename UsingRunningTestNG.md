**WARNING** This is an unfinished page, I have yet to test the more in-depth details. I will  update this page as I progress with the research. You can however use the links for a clear explanation regarding these details.

# Introduction #

This page will attempt to explain the basics in writing and running tests for the Salesmen Project. For the official TestNG documentation, please see: http://testng.org/doc/documentation-main.html#introduction
This document will be referred further on this page as TestNGDoc
Another very interesting page is chapter 31 of the jboss-seam Reference:
http://docs.jboss.com/seam/2.0.2.SP1/reference/en-US/html/testing.html


# Writing #
## Program Logic ##
### Basics ###
Primarily, you must write the logic for testing the program. A clear in-depth explanation is already delivered in TestNGDoc chapter 5.
### Seam with TestNG ###
To be completed
For now, please see
http://docs.jboss.com/seam/2.0.2.SP1/reference/en-US/html/testing.html
## XML file ##
### Eclipse ###
When writing your own tests in Eclipse, you can make use of the Eclipse plugin to create everything you need.
### Test.xml ###

# Running #
## Ant ##
### in Build.xml ###
Just deploy the website. Tests will be run automatically.
### in seperate test.xml ###
Command promt:
ant test
## Eclipse ##
http://testng.org/doc/eclipse.html

This page gives a nice overview of how to run tests with eclipse. Remember Ant testing is still expected. The testclasses are not different, so you just have to provide the right xml data.