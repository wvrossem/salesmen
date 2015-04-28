# Introduction #

Developing software is all about building abstractions, which is the act of separating an entity from all the other entities by giving it a name. This means that there are lots of names and acronyms to be dealt with.

This page is intended to provide definitions for terms that are often used in the context of Salesmen development. Presenting our literature (a.k.a., Glossary) in a compact form will help developers be more productive as they can refer to this document during the meetings and in other documents.

# Terms #

## API ##

An Application Programming Interface is the interface between a software program and other computer programs. In order for program **A** to interact with program **B**, program **A** must use the _API_ provided by program **B**. Concretely, if Salesmen relies on a third-party program to process user payments, it then needs to use the API of that application --i.e. calling the right routine-- in order to perform that operation.

In Object-oriented programming, public fields of an object are considered to constitute the API provided by that object.

## Java Platform ##

A Java platform consists of a JVM, and an API. There are three such platforms:
  * **Java Standard Edition (Java SE):** What is known to be the Java programming language.
  * **Java Enterprise Edition (Java EE):** Java SE + Enterprise-specific API; most notably transaction, and persistence.
  * **Java Mobile Edition (Java ME):** A lighter Java SE + API for mobile applications.

## Jave EE ##

A Java platform suited for developing enterprise software. The Java EE platform provides a rich set of APIs. The set of programs that implement these APIs is called an application server.

## Enterprise Software ##

Enterprise software is software that is distributed, multi-tiered, fault-tolerant. Web applications that separate concerns are examples of enterprise software. Salesmen is an enterprise software in the sense that it's a multi-tiered Web application.

## Web Container ##

In Java EE, applications are multi-tiered. One tier is the client (e.g., Web browser), another tier is the actual application that implements the business logic. These two tiers are connected using the Web tier, which is implemented by the Web container. A Web container connects other tiers by exchanging _discrete_ messages --transactions-- between them.

## EJB Container ##

The EJB container is the interface between enterprise beans, which provide the business logic in a Java EE application, and the Java EE server. The EJB container runs on the Java EE server and manages the execution of an application's enterprise beans.

Source: [1](1.md)

## Application Server ##

A software platform for deploying complex Web application. A Web container is included in an application server in order to serve final pages to the user and run servlets. In addition, an application server provides an EJB container and a number of APIs such as The Java Transaction API, The Java Persistence API, and Java Message Service API.

## Servlet ##

A program that receives a request (e.g. HTTP request) and returns a response (e.g. HTML). Servlets are used by Web containers, which are also called servlet containers to serve content and functionality on the Web client.

## JSP ##

JavaServer Pages is a Java Technology that helps incorporate dynamic content into static text-based document types returned as HTTP response by the server, such as HTML, XML. Servlets and JSP are two different approaches to generating dynamic Web pages. JSP lets developers first specify the content of Web pages in XML, or HTML and use JSP place holders in order to perform server-side operations. Please note that JSP-based Web pages are compiled into Servlets by the Web container.

## JVM ##

A Java Virtual Machine is a program that interprets Java bytecode, which is the result of compiling Java source code. This means that the Java programming language is both compiled and interpreted. A particular JVM only works on a particular platform as it generates machine code, which depends on a particular processor.

## Scala ##

Scala is a general purpose programming language designed to express common programming patterns in a concise, elegant, and type-safe way. It smoothly integrates features of object-oriented and functional languages, enabling Java and other programmers to be more productive. Code sizes are typically reduced by a factor of two to three when compared to an equivalent Java application.

## Groovy ##

Groovy is an object-oriented programming language for the Java platform as an alternative to the Java programming language. It is a dynamic language with features similar to those of Python, Ruby, Perl, and Smalltalk. It can be used as a scripting language for the Java Platform. Groovy uses a Java-like bracket syntax. It is dynamically compiled to Java Virtual Machine bytecode and works seamlessly with other Java code and libraries. Most Java code is also syntactically valid Groovy.

## Javascript ##



## GWT ##

The Google Web Toolkit (GWT) makes it even easier to design an AJAX application using just the Java programming language. It is an open-source Java development framework and its best feature is that we don't have to worry too much about incompatibilities between web browsers and platforms. In GWT, we write the code in Java and then GWT converts it into browser-compliant JavaScript and HTML. This helps a lot, because we can stop worrying about modular programming. It provides a programming framework that is similar to that used by developers building Java applications using one of the GUI toolkits such as Swing, AWT, or SWT. GWT provides all the common user-interface widgets, listeners to react to events happening in the widgets, and ways to combine them into more complex widgets to do things that the GWT team may never have envisioned! Moreover,
it makes reusing chunks of program easy. This greatly reduces the number of different technologies that you will need to master. If you know Java, then you can use your favorite IDE (we use Eclipse in this book) to write and debug an AJAX GWT application in Java. Yes, that means you can actually put breakpoints in your code and debug seamlessly from the client side to the server side. You can deploy your applications in any servlet container, create and run unit tests, and essentially develop GWT applications like any Java application.

## Spring ##

## Hibernate ##

## Persistence ##

## Web Services ##

## WSDL ##

## SOAP ##

## REST ##

## XML ##

## Maven ##

## Ant ##

## SilkPage ##

## TimeTrack ##

## jQuery ##
(Bart) jQuery is a fast and concise JavaScript Library that simplifies HTML document traversing, event handling, animating, and Ajax interactions for rapid web development.
Info & examples: http://jquery.com/

# References #

[1](1.md) http://docs.sun.com/app/docs/doc/820-7759